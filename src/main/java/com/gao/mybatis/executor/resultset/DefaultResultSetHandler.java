package com.gao.mybatis.executor.resultset;

import com.gao.mybatis.executor.Executor;
import com.gao.mybatis.mapping.BoundSql;
import com.gao.mybatis.mapping.MappedStatement;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultResultSetHandler implements ResultSetHandler{

    private BoundSql boundSql;

    public DefaultResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    @Override
    public <E> List<E> handleResultSet(Statement statement) throws SQLException {
        ResultSet resultSet = statement.getResultSet();
        try {
            return result2Obj(resultSet, Class.forName(boundSql.getResultType()));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public <T> List<T> result2Obj(ResultSet resultSet, Class<?> clazz) {
        ArrayList<T> arrayList = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            while (resultSet.next()) {
                T o = (T)clazz.newInstance();
                for (int i = 1; i <= count; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);

                    Method method;
                    if (value != null) {
                        if (value instanceof Timestamp) {
                            method = clazz.getMethod(setMethod, java.util.Date.class);
                        } else {
                            method = clazz.getMethod(setMethod, value.getClass());
                        }
                        method.invoke(o, value);
                    }
                }
                arrayList.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
