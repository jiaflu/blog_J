package com.ljf.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljf.blog.dto.Types;
import com.ljf.blog.exception.TipException;
import com.ljf.blog.mapper.ContentMapper;
import com.ljf.blog.mapper.MetaMapper;
import com.ljf.blog.pojo.Content;
import com.ljf.blog.pojo.ContentExample;
import com.ljf.blog.service.ContentService;
import com.ljf.blog.service.MetaService;
import com.ljf.blog.util.DateKit;
import com.ljf.blog.util.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lujiafeng on 2018/9/13.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    MetaService metaService;

    @Override
    public void publish(Content content) {
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

        //metaService.saveMeta(cid,);
    }

    @Override
    public PageInfo<Content> getContents(int page, int limit) {
        ContentExample contentExample = new ContentExample();
        //contentExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType());
        contentExample.setOrderByClause("created desc");
        PageHelper.startPage(page, limit);
        List<Content> contentList = contentMapper.selectByExample(contentExample);
        PageInfo<Content> pageInfo = new PageInfo<>(contentList);
        return pageInfo;
    }

    @Override
    public Content getContent(String id) {
        //后期需添加Redis，先从redis中读取文章，若无，则从数据库中读
        /*

         */

        Content content = null;
        if (StringUtils.isNotBlank(id)) {
            if (Tools.isNumber(id)) {
                content = contentMapper.selectByPrimaryKey(Integer.valueOf(id));
                if (null != content) {
                    content.setHits(content.getHits() + 1);
                    contentMapper.updateByPrimaryKey(content);
                }
            }
        }
        return content;
    }

    @Override
    public void update(Content content) {
        //按主键更新不为null的字段
        contentMapper.updateByPrimaryKeySelective(content);
    }


    @Override
    public void delete(int cid) {
        contentMapper.deleteByPrimaryKey(cid);
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
