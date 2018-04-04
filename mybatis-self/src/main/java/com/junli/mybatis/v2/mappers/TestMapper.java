package com.junli.mybatis.v2.mappers;


import com.junli.mybatis.v2.beans.Test;

/**
 *
 */
public interface TestMapper {
    Test selectByPrimaryKey(Integer userId);
}
