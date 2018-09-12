package com.ljf.blog.mapper;

import com.ljf.blog.pojo.RelationshipExample;
import com.ljf.blog.pojo.RelationshipKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationshipMapper {
    long countByExample(RelationshipExample example);

    int deleteByExample(RelationshipExample example);

    int deleteByPrimaryKey(RelationshipKey key);

    int insert(RelationshipKey record);

    int insertSelective(RelationshipKey record);

    List<RelationshipKey> selectByExample(RelationshipExample example);

    int updateByExampleSelective(@Param("record") RelationshipKey record, @Param("example") RelationshipExample example);

    int updateByExample(@Param("record") RelationshipKey record, @Param("example") RelationshipExample example);
}