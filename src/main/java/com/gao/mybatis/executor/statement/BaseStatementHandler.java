package com.gao.mybatis.executor.statement;

import com.gao.mybatis.executor.Executor;
import com.gao.mybatis.executor.parameter.ParameterHandler;
import com.gao.mybatis.executor.resultset.ResultSetHandler;
import com.gao.mybatis.mapping.BoundSql;
import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.session.Configuration;
import com.gao.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public abstract class BaseStatementHandler implements StatementHandler{

    protected final Configuration configuration;
    protected final Executor executor;
    protected final MappedStatement mappedStatement;

    protected final Object parameterObject;
    protected final ResultSetHandler resultSetHandler;
    protected final ParameterHandler parameterHandler;

    protected BoundSql boundSql;

    public BaseStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject,
                                ResultHandler resultHandler, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguration();
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
        this.resultSetHandler = configuration.newResultSetHandler(executor, mappedStatement, boundSql);
        this.parameterHandler = configuration.newParameterHandler(mappedStatement, parameterObject, boundSql);
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        try {
            Statement statement = null;
            statement = instantiateStatement(connection);
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (Exception e) {
            throw new RuntimeException("Error preparing statement.  Cause: " + e, e);
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;
}
