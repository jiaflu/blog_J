package com.ljf.blog.service.impl;

import com.ljf.blog.mapper.MetaMapper;
import com.ljf.blog.pojo.Meta;
import com.ljf.blog.pojo.MetaExample;
import com.ljf.blog.service.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lujiafeng on 2018/9/18.
 */

@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    MetaMapper metaMapper;

    @Override
    public Meta getMeta(String type, String name) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
            MetaExample metaExample = new MetaExample();
            metaExample.createCriteria().andTypeEqualTo(type).andNameEqualTo(name);
            List<Meta> metas = metaMapper.selectByExample(metaExample);
            if (!metas.isEmpty()) {
                return metas.get(0);
            }
        }
        return null;
    }

    @Override
    public List<Meta> getMetas(String type) {
        if (StringUtils.isNotBlank(type)) {
            MetaExample metaExample = new MetaExample();
            metaExample.setOrderByClause("sort desc, mid desc");
            metaExample.createCriteria().andTypeEqualTo(type);
            List<Meta> metas = metaMapper.selectByExample(metaExample);
            return metas;
        }
        return null;
    }
}
