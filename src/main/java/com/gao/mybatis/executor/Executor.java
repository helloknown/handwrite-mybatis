package com.gao.mybatis.executor;

import com.gao.mybatis.mapping.BoundSql;
import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.session.ResultHandler;
import com.gao.mybatis.session.RowBounds;
import com.gao.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException;

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
}
