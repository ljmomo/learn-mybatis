package com.junli.mybatis.v1;

import com.junli.mybatis.beans.Test;
import com.junli.mybatis.mapper.TestMapper;

/**
 * @author lijun
 * @since 2018-04-02 13:48
 */
public class BootStrap {
    public static void main(String[] args) {
        LJSqlSession ljSqlSession = new LJSqlSession(new LJConfiguration(), new LJSimpleExecutor());
        TestMapper mappper = ljSqlSession.getMappper(TestMapper.class);
        Test test = mappper.selectByPrimaryKey(1);
        System.out.println(test);

    }
}
