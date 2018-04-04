package com.junli.mybatis.v2.statement;

import com.junli.mybatis.v2.config.MapperData;
import com.junli.mybatis.v2.config.SelfConfiguration;
import com.junli.mybatis.v2.result.SelfResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author lijun
 * @since 2018-04-03 13:22
 */
public class SelfStatementHandler {


    private final SelfConfiguration configuration;

    private final SelfResultSetHandler resultSetHandler;

    public SelfStatementHandler(SelfConfiguration configuration) {
        this.configuration = configuration;
        resultSetHandler = new SelfResultSetHandler(configuration);
    }


    public <T> T query(MapperData mapperData, Object paramters) {
        try {
            //获取连接
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format(mapperData.getSql(),
                    Integer.parseInt(String.valueOf(paramters))));
            statement.execute();
            return resultSetHandler.handle(statement.getResultSet(),mapperData.getType());

        }catch (Exception e){

        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/junli?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
