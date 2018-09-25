package com.ljf.blog.mapper;

import com.ljf.blog.pojo.Meta;
import com.ljf.blog.pojo.MetaExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MetaMapper {
    long countByExample(MetaExample example);

    int deleteByExample(MetaExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(Meta record);

    int insertSelective(Meta record);

    List<Meta> selectByExample(MetaExample example);

    Meta selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") Meta record, @Param("example") MetaExample example);

    int updateByExample(@Param("record") Meta record, @Param("example") MetaExample example);

    int updateByPrimaryKeySelective(Meta record);

    int updateByPrimaryKey(Meta record);

}