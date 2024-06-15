package com.gao.mybatis.reflection.wrapper;

import com.gao.mybatis.reflection.MetaObject;

public interface ObjectWrapperFactory {

    /**
     * 判断有无包装器
     * @param object
     * @return
     */
    boolean hasWrapperFor(Object object);

    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);
}
