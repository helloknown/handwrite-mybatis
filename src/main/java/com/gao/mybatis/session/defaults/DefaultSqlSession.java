package com.gao.mybatis.session.defaults;

import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.session.Configuration;
import com.gao.mybatis.session.SqlSession;

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
        MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
        return (T) ("你被代理了！" + "\n方法：" + statementName + " \n入参：" + parameter + "\n待执行SQL:" + mappedStatement.getSql());
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
