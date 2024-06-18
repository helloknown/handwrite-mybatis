package com.gao.mybatis.scripting;

import com.gao.mybatis.executor.parameter.ParameterHandler;
import com.gao.mybatis.mapping.BoundSql;
import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.mapping.SqlSource;
import com.gao.mybatis.session.Configuration;
import org.dom4j.Element;

public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

    /**
     * 创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);
}
