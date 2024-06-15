package com.gao.mybatis.session.defaults;

import com.gao.mybatis.binding.MapperRegistry;
import com.gao.mybatis.executor.Executor;
import com.gao.mybatis.mapping.Environment;
import com.gao.mybatis.session.Configuration;
import com.gao.mybatis.session.SqlSession;
import com.gao.mybatis.session.SqlSessionFactory;
import com.gao.mybatis.session.TransactionIsolationLevel;
import com.gao.mybatis.transaction.Transaction;
import com.gao.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction transaction = null;
        try {
            Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
//            transaction = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource().getConnection());
            transaction = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMIT, false);
            // 创建执行器
            Executor executor = configuration.newExecutor(transaction);
            // 创建 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert transaction != null;
                transaction.close();
            } catch (SQLException ex) {

            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
