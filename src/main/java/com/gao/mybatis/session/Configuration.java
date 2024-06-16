package com.gao.mybatis.session;

import com.gao.mybatis.binding.MapperRegistry;
import com.gao.mybatis.datasource.druid.DruidDataSourceFactory;
import com.gao.mybatis.datasource.pooled.PooledDataSourceFactory;
import com.gao.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.gao.mybatis.executor.Executor;
import com.gao.mybatis.executor.SimpleExecutor;
import com.gao.mybatis.executor.resultset.DefaultResultSetHandler;
import com.gao.mybatis.executor.resultset.ResultSetHandler;
import com.gao.mybatis.executor.statement.PreparedStatementHandler;
import com.gao.mybatis.executor.statement.StatementHandler;
import com.gao.mybatis.mapping.BoundSql;
import com.gao.mybatis.mapping.Environment;
import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.reflection.MetaObject;
import com.gao.mybatis.reflection.factory.DefaultObjectFactory;
import com.gao.mybatis.reflection.factory.ObjectFactory;
import com.gao.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.gao.mybatis.reflection.wrapper.ObjectWrapperFactory;
import com.gao.mybatis.scripting.LanguageDriverRegistry;
import com.gao.mybatis.scripting.xmltags.XMLLanguageDriver;
import com.gao.mybatis.transaction.Transaction;
import com.gao.mybatis.transaction.jdbc.JdbcTransactionFactory;
import com.gao.mybatis.type.TypeAliasRegistry;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Configuration {

    protected Environment environment;

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
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

    public String getDatabaseId() {
        return databaseId;
    }

    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    public StatementHandler newStatementHandler(Executor executor, MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, ms, parameter, resultHandler, boundSql);
    }

    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    // 创建元对象
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }
}
