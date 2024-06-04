package com.gao.mybatis.session.defaults;

import com.gao.mybatis.binding.MapperRegistry;
import com.gao.mybatis.session.Configuration;
import com.gao.mybatis.session.SqlSession;
import com.gao.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
