package com.gao.mybatis.reflection.factory;

import java.util.List;
import java.util.Properties;

public interface ObjectFactory {

    void setProperties(Properties properties);

    <T> T create(Class<T> type);

    <T> T create(Class<T> type, List<Class<?>> constructArgTypes, List<Object> constructsArgs);

    <T> boolean isCollection(Class<T> type);
}
