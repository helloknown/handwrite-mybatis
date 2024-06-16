package com.gao.mybatis.scripting;

import com.gao.mybatis.mapping.SqlSource;
import com.gao.mybatis.session.Configuration;
import org.dom4j.Element;

public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);
}
