package com.gao.mybatis.session;

import java.util.List;

public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <E> List<E> selectList(String statement, Object parameter);

    int insert(String statement, Object parameter);

    int update(String statement, Object parameter);

    Object delete(String statement, Object parameter);

    /**
     * 以下是事务控制方法 commit,rollback
     * Flushes batch statements and commits database connection.
     * Note that database connection will not be committed if no updates/deletes/inserts were called.
     */
    void commit();

    /**
     * Retrieves a mapper.
     * 得到映射器，这个巧妙的使用了泛型，使得类型安全
     *
     * @param <T>  the mapper type
     * @param type Mapper interface class
     * @return a mapper bound to this SqlSession
     */
    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();
}
