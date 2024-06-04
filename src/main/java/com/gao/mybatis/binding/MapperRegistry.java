package com.gao.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import com.gao.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Object O) {
    }

    public <T> T getMapper(Class<T> mapper, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(mapper);

        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + mapper + " is not known to the MapperRegistry.");
        }

        return mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> void addMapper(Class<T> mapper) {
        if (mapper.isInterface()) {
            if (hashMapper(mapper)) {
                throw new RuntimeException("Type " + mapper + " is already known to the MapperRegistry.");
            }
            knownMappers.put(mapper, new MapperProxyFactory<>(mapper));
        }
    }

    public void addMappers(String packageName) {
        for (Class<?> aClass : ClassScanner.scanPackage(packageName)) {
            addMapper(aClass);
        }
    }

    public boolean hashMapper(Class<?> mapper) {
        return knownMappers.containsKey(mapper);
    }
}
