package com.gao.mybatis.mapping;

public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);
}
