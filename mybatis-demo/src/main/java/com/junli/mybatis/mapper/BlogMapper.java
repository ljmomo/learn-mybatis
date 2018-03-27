package com.junli.mybatis.mapper;

import com.junli.mybatis.beans.Blog;
import com.junli.mybatis.beans.BlogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectById(Long id);

    Blog selectBlogById(Long id);

    Blog  selectBlogAndPosts(Long id);

    Blog selectBlogAndPostsResultQuery(Long id);

    List<Blog> selectByExample(BlogExample example);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);


}