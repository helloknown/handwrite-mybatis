package com.gao.mybatis.session.defaults;

import com.gao.mybatis.executor.Executor;
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

    private  Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;

    }

    public <T> T selectOne(String statementName) {
        return (T) ("你被代理了！" + statementName);
    }

    @Override
    public <T> T selectOne(String statementName, Object parameter) {
        try {
            MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
            /*Environment environment = configuration.getEnvironment();

            BoundSql boundSql = mappedStatement.getBoundSql();
            Connection connection = environment.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            preparedStatement.setLong(1, Long.parseLong(((Object[])parameter)[0].toString()));
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> objList = result2Obj(resultSet, Class.forName(boundSql.getResultType()));
            return objList.get(0);*/
            List<T> objList = executor.query(mappedStatement, parameter, Executor.NO_RESULT_HANDLER, mappedStatement.getBoundSql());
            return objList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
