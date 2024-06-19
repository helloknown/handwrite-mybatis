package com.gao.mybatis.builder.xml;

import com.gao.mybatis.builder.BaseBuilder;
import com.gao.mybatis.builder.MapperBuilderAssistant;
import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.mapping.SqlCommandType;
import com.gao.mybatis.mapping.SqlSource;
import com.gao.mybatis.scripting.LanguageDriver;
import com.gao.mybatis.session.Configuration;
import org.dom4j.Element;

import java.util.Locale;

public class XMLStatementBuilder extends BaseBuilder{

    private MapperBuilderAssistant builderAssistant;
    private Element element;

    public XMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, Element element) {
        super(configuration);
        this.element = element;
        this.builderAssistant = builderAssistant;
    }

    //解析语句(select|insert|update|delete)
    //<select
    //  id="selectPerson"
    //  parameterType="int"
    //  parameterMap="deprecated"
    //  resultType="hashmap"
    //  resultMap="personResultMap"
    //  flushCache="false"
    //  useCache="true"
    //  timeout="10000"
    //  fetchSize="256"
    //  statementType="PREPARED"
    //  resultSetType="FORWARD_ONLY">
    //  SELECT * FROM PERSON WHERE ID = #{id}
    //</select>
    public void parseStatementNode() {
        String id = element.attributeValue("id");
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveAlias(parameterType);
        // 外部应用 resultMap
        String resultMap = element.attributeValue("resultMap");
        // 结果类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        // 获取命令类型(select|insert|update|delete)
        String name = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(name.toUpperCase(Locale.ENGLISH));

        // 获取默认语言驱动器
        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);
        // 解析成SqlSource，DynamicSqlSource/RawSqlSource
        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        // 调用助手类【本节新添加，便于统一处理参数的包装】
        /*MappedStatement mappedStatement = new MappedStatement.Builder(configuration, currentNamespace + "." + id, sqlCommandType,
                sqlSource, resultTypeClass).build();*/

        // 添加解析 SQL
        // configuration.addMappedStatement(mappedStatement);
        builderAssistant.addMappedStatement(id, sqlSource, sqlCommandType, parameterTypeClass, resultMap, resultTypeClass, langDriver);
    }
}
