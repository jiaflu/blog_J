package com.ljf.blog.mapper;

import com.ljf.blog.pojo.Attach;
import com.ljf.blog.pojo.AttachExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachMapper {
    long countByExample(AttachExample example);

    int deleteByExample(AttachExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attach record);

    int insertSelective(Attach record);

    List<Attach> selectByExample(AttachExample example);

    Attach selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attach record, @Param("example") AttachExample example);

    int updateByExample(@Param("record") Attach record, @Param("example") AttachExample example);

    int updateByPrimaryKeySelective(Attach record);

    int updateByPrimaryKey(Attach record);
}