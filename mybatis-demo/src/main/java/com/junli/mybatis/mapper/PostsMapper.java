package com.junli.mybatis.mapper;

import com.junli.mybatis.beans.Posts;
import com.junli.mybatis.beans.PostsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostsMapper {
    long countByExample(PostsExample example);

    int deleteByExample(PostsExample example);

    int insert(Posts record);

    int insertSelective(Posts record);

    List<Posts> selectByExample(PostsExample example);

    int updateByExampleSelective(@Param("record") Posts record, @Param("example") PostsExample example);

    int updateByExample(@Param("record") Posts record, @Param("example") PostsExample example);

    Posts selectByPrimaryKey(int id);
}