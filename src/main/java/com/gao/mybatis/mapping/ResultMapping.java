package com.gao.mybatis.mapping;

import com.gao.mybatis.session.Configuration;
import com.gao.mybatis.type.JdbcType;
import com.gao.mybatis.type.TypeHandler;

public class ResultMapping {

    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> type;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    public ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();


    }
}
