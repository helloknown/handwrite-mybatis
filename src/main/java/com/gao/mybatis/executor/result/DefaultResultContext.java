package com.gao.mybatis.executor.result;

import com.gao.mybatis.session.ResultContext;

public class DefaultResultContext implements ResultContext {

    private Object resultObject;
    private int resultCount;

    public DefaultResultContext() {
        this.resultObject = null;
        this.resultCount = 0;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void nextResultObject(Object resultObject) {
        resultCount++;
        this.resultObject = resultObject;
    }
}
