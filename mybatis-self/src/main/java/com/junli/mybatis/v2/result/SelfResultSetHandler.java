package com.junli.mybatis.v2.result;

import com.junli.mybatis.v2.config.SelfConfiguration;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lijun
 * @since 2018-04-03 13:33
 */
public class SelfResultSetHandler {

    private  SelfConfiguration configuration;

    public SelfResultSetHandler(SelfConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     *
     * 查询结果
     * @param resultSet
     * @param type
     * @param <T>
     * @return
     * @throws SQLException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public <T> T handle(ResultSet resultSet, Class type) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object resultObj = new DefaultObjectFactory().create(type);
        if (resultSet.next()){
            int i = 0;
            Field[] declaredFields = resultObj.getClass().getDeclaredFields();
            for ( Field field: declaredFields) {
                setValue(resultObj, field, resultSet ,i);
            }
            
        }
        return (T) resultObj;
    }

    /**
     * 反色调用set 方法
     * @param resultObj
     * @param field
     * @param resultSet
     * @param i
     * @throws NoSuchMethodException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setValue(Object resultObj, Field field, ResultSet resultSet, int i) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method method = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        method.invoke(resultObj,getResult(field,resultSet));
    }

    /**
     * 实现数据库字段和实体字段映射 这里只是简单实现需要采用更加精准的匹配
     * @param field
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Object getResult(Field field, ResultSet resultSet) throws SQLException {
        //TODO type handles
        //bean属性的名字必须要和数据库column的名字一样
        Class<?> type = field.getType();
        if(Integer.class == type){
            return resultSet.getInt(field.getName());
        }
        if(String.class == type){
            return resultSet.getString(field.getName());
        }
        return resultSet.getString(field.getName());
    }

    private String upperCapital(String name) {
        char[] charArray = name.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }
}
