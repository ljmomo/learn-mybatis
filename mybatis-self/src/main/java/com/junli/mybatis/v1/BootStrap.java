package com.junli.mybatis.v1;

/**
 * @author lijun
 * @since 2018-04-02 10:00
 */
public class BootStrap {
    public static void main(String[] args) {
        JunliSqlSession junliSqlSession = new JunliSqlSession(new JunliConfiguration(), new JunliSimpleExecutor());
        TestMapper testMapper = junliSqlSession.getMapper(TestMapper.class);
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);

    }
}
