package com.junli.mybatis.v1;

import com.junli.mybatis.beans.Test;

import java.sql.*;

/**
 * @author lijun
 * @since 2018-04-02 13:42
 */
public class LJSimpleExecutor implements LJExecutor {

    @Override
    public <T> T query(String sql, String paramers) {
        return get(sql,paramers);
    }


    private <T> T get(String sql, String paramers){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Test test = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/junli?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "123456");
            preparedStatement = connection.prepareStatement(String.format(sql,Integer.parseInt(paramers)));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                test = new Test();
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T) test;
    }
}
