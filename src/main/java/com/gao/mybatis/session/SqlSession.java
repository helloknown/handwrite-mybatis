package com.gao.mybatis.session;

public interface SqlSession {

    <T> T selectOne(String statementName);

    <T> T selectOne(String statementName, Object parameter);

    <T> T getMapper(Class<T> mapperClass);

    Configuration getConfiguration();
}
