package com.gao.mybatis.session.defaults;

import com.gao.mybatis.mapping.BoundSql;
import com.gao.mybatis.mapping.Environment;
import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.session.Configuration;
import com.gao.mybatis.session.SqlSession;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T selectOne(String statementName) {
        return (T) ("你被代理了！" + statementName);
    }

    @Override
    public <T> T selectOne(String statementName, Object parameter) {
        try {
            MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
            Environment environment = configuration.getEnvironment();

            BoundSql boundSql = mappedStatement.getBoundSql();
            Connection connection = environment.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            preparedStatement.setLong(1, Long.parseLong(((Object[])parameter)[0].toString()));
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> objList = result2Obj(resultSet, Class.forName(boundSql.getResultType()));
            return objList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> result2Obj(ResultSet resultSet, Class<?> clazz) {
        ArrayList<T> arrayList = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            while (resultSet.next()) {
                T o = (T)clazz.newInstance();
                for (int i = 1; i <= count; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);

                    Method method;
                    if (value != null) {
                        if (value instanceof Timestamp) {
                            method = clazz.getMethod(setMethod, java.util.Date.class);
                        } else {
                            method = clazz.getMethod(setMethod, value.getClass());
                        }
                        method.invoke(o, value);
                    }
                }
                arrayList.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return configuration.getMapper(mapperClass, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
