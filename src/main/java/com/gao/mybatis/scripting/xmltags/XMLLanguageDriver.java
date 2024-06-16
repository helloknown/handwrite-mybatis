package com.gao.mybatis.scripting.xmltags;

import com.gao.mybatis.mapping.SqlSource;
import com.gao.mybatis.scripting.LanguageDriver;
import com.gao.mybatis.session.Configuration;
import org.dom4j.Element;

public class XMLLanguageDriver implements LanguageDriver {
    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }
}
