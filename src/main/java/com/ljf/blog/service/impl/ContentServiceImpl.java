package com.ljf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljf.blog.constant.ContentKey;
import com.ljf.blog.dto.Types;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.mapper.ContentMapper;
import com.ljf.blog.mapper.MetaMapper;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.ContentExample;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.service.MetaService;
import com.ljf.blog.util.DateKit;
import com.ljf.blog.util.RedisKeyUtil;
import com.ljf.blog.util.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lujiafeng on 2018/9/13.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    MetaService metaService;

    @Autowired
    private ValueOperations<String, Object> valueOperations;
    @Autowired
    private RedisService redisService;

    @Override
    public void add(Content content) {
        checkContent(content);
        if (StringUtils.isNotBlank(content.getSlug())) {
            if (content.getSlug().length() < 5) {
                throw new TipException("路径太短了");
            }
            //判断路径是否合法
            /*

             */
            ContentExample contentExample = new ContentExample();
            contentExample.createCriteria().andTypeEqualTo(content.getType()).andSlugEqualTo(content.getSlug());
            long count = contentMapper.countByExample(contentExample);
            if (count > 0) {
                throw new TipException("该路径已经存在，请重新输入");
            }
        } else {
            content.setSlug(null);
        }
        //去除表情
        /*

         */
        int time = DateKit.getCurrentUnixTime();
        content.setCreated(time);
        content.setModified(time);
        content.setHits(0);
        content.setCommentsNum(0);
        Integer cid = contentMapper.insert(content);

        String tags = content.getTags();
        String category = content.getCategories();

        metaService.updateCount(category, 1);
        metaService.saveMeta(cid, tags, Types.TAG.getType());
        metaService.saveMeta(cid, category, Types.CATEGORY.getType());
    }

    @Override
    public Content getArticle(String id) {
        //先从redis中读取文章信息
        String contentKey = RedisKeyUtil.getKey(ContentKey.TABLE_NAME, ContentKey.MAJOR_KEY, id);
        Content content = (Content) valueOperations.get(contentKey);
        if (content != null) {
            System.out.println("从redis读取数据");
        }
        if (content == null) {
            System.out.println("从mysql读取数据");
            if (StringUtils.isNotBlank(id)) {
                if (Tools.isNumber(id)) {
                    content = contentMapper.selectByPrimaryKey(Integer.valueOf(id));
                    if (null != content) {
                        content.setHits(content.getHits() + 1);
                        contentMapper.updateByPrimaryKey(content);
                    }
                    System.out.println("将查询数据插入redis");
                    valueOperations.set(contentKey, content);
                    redisService.expireKey(contentKey, ContentKey.LIVE_TIME, TimeUnit.HOURS);
                    return content;
                } else {
                    ContentExample example = new ContentExample();
                    example.createCriteria().andSlugEqualTo(id);
                    List<Content> contents = contentMapper.selectByExampleWithBLOBs(example);
                    if (contents.isEmpty()) {
                        throw new TipException("query content by id and return is not one");
                    }
                    content = contents.get(0);
                    System.out.println("将查询数据插入redis");
                    valueOperations.set(contentKey, content);
                    redisService.expireKey(contentKey, ContentKey.LIVE_TIME, TimeUnit.HOURS);
                }
            }
        }
        return content;
    }


    @Override
    public PageInfo<Content> getArticles(Integer page, Integer limit) {
        ContentExample contentExample = new ContentExample();
        //contentExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType());
        contentExample.setOrderByClause("created desc");
        PageHelper.startPage(page, limit);
        //Content类中content在数据库中为text类型的特殊字段，得使用selectByExampleWithBLOBs
        List<Content> contents = contentMapper.selectByExampleWithBLOBs(contentExample);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

//    @Override
//    public PageInfo<Content> getArticles(Integer mid, Integer page, Integer limit) {
//        ContentExample contentExample = new ContentExample();
//
//    }

    @Override
    public PageInfo<Content> getArticles(String keyword, Integer page, Integer limit) {
        ContentExample contentExample = new ContentExample();
        contentExample.createCriteria().andTitleLike("%" + keyword + "%");
        contentExample.setOrderByClause("created desc");
        PageHelper.startPage(page, limit);
        List<Content> contents = contentMapper.selectByExample(contentExample);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        return pageInfo;

    }

    @Override
    public PageInfo<Content> getArticles(ContentExample example, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Content> contents = contentMapper.selectByExample(example);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    public void update(Content content) {
        // 检查文章输入
        checkContent(content);
        if (StringUtils.isBlank(content.getSlug())) {
            content.setSlug(null);
        }
        int time = DateKit.getCurrentUnixTime();
        content.setModified(time);
        Integer cid = content.getCid();
        //contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        // 按主键更新不为null的字段
        contentMapper.updateByPrimaryKeySelective(content);
        // 更新缓存
        String contentKey = RedisKeyUtil.getKey(ContentKey.TABLE_NAME, ContentKey.MAJOR_KEY, content.getCid().toString());
        redisService.deleteKey(contentKey);

        metaService.saveMeta(cid, content.getTags(), Types.TAG.getType());
        metaService.saveMeta(cid, content.getCategories(), Types.CATEGORY.getType());
    }


    @Override
    public void delete(Integer cid) {
        Content content = contentMapper.selectByPrimaryKey(cid);
        if (null != content) {
            contentMapper.deleteByPrimaryKey(cid);
            metaService.updateCount(content.getCategories(), -1);
            //其他关联属性的删除,如Comment, RelationShip
        }

    }



    private void  checkContent(Content content) throws TipException {
        if (null == content) {
            throw new TipException("文章对象不能为空");
        }
        if (StringUtils.isBlank(content.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (StringUtils.isBlank(content.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        if (content.getTitle().length() > 200) {
            throw new TipException("文章标题过长");
        }
        if (content.getContent().length() > 65000) {
            throw new TipException("文章内容过长");
        }
        if (null == content.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }
    }
}
