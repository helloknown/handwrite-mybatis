package com.gao.mybatis.binding;

import com.gao.mybatis.mapping.MappedStatement;
import com.gao.mybatis.mapping.SqlCommandType;
import com.gao.mybatis.session.Configuration;
import com.gao.mybatis.session.SqlSession;

import java.lang.reflect.Method;

public class MapperMethod {

    private SqlCommand sqlCommand;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
         this.sqlCommand = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (sqlCommand.getSqlCommandType()) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(sqlCommand.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + sqlCommand.getName());
        }
        return result;
    }

    public static class SqlCommand {
        private final String name;
        private final SqlCommandType sqlCommandType;


        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
            name = mappedStatement.getId();
            sqlCommandType = mappedStatement.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getSqlCommandType() {
            return sqlCommandType;
        }
    }
}
