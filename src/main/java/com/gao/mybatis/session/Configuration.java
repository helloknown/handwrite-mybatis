package com.gao.mybatis.session;

import com.gao.mybatis.binding.MapperRegistry;
import com.gao.mybatis.datasource.druid.DruidDataSourceFactory;
import com.gao.mybatis.mapping.Environment;
import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.transaction.jdbc.JdbcTransactionFactory;
import com.gao.mybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    protected Environment environment;

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
    }

    public <T> T getMapper(Class<T> mapperClass, SqlSession sqlSession) {
        return mapperRegistry.getMapper(mapperClass, sqlSession);
    }

    public <T> void addMapper(Class<T> mapperClass) {
        mapperRegistry.addMapper(mapperClass);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
