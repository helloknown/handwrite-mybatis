package com.gao.mybatis.reflection.factory;

import java.util.List;
import java.util.Properties;

public class DefaultObjectFactory implements ObjectFactory{
    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public <T> T create(Class<T> type) {
        return null;
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructArgTypes, List<Object> constructsArgs) {
        return null;
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return false;
    }
}
