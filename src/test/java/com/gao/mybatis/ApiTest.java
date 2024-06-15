package com.gao.mybatis;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gao.mybatis.binding.MapperProxyFactory;
import com.gao.mybatis.binding.MapperRegistry;
import com.gao.mybatis.datasource.pooled.PooledDataSource;
import com.gao.mybatis.io.Resources;
import com.gao.mybatis.session.SqlSessionFactory;
import com.gao.mybatis.session.SqlSessionFactoryBuilder;
import com.gao.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.gao.mybatis.session.SqlSession;
import com.gao.mybatis.test.dao.IUserDao;
import com.gao.mybatis.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ApiTest {
    Logger logger = LoggerFactory.getLogger(ApiTest.class);

    /*@Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> mapperProxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<String, String>();

        sqlSession.put("com.gao.mybatis.test.dao.IUserDao.queryUserName", "123142");
        IUserDao iUserDao = mapperProxyFactory.newInstance(sqlSession);
        String userName = iUserDao.queryUserName("001");
        logger.info(userName);
    }*/

    /*@Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> mapperProxyFactory = new MapperProxyFactory<>(IUserDao.class);

        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("com.gao.mybatis.test.dao.IUserDao");
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
//        IUserDao iUserDao = mapperProxyFactory.newInstance(sqlSession);
        String userName = iUserDao.queryUserName("001");
        logger.info("测试结果:{}", userName);
    }*/

    /*@Test
    public void test_MapperProxyFactory3() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        String userName = userDao.queryUserInfoById("001");
        logger.info("测试结果:{}", userName);
    }*/

    /*
    * 无池化测试
    * */
    /*@Test
    public void test_MapperProxyFactory4() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById("1");
        logger.info("测试结果:{}", JSONUtil.toJsonStr(user));
    }*/

    /*
     * 有池化测试
     * */
    /*@Test
    public void test_MapperProxyFactory5() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        for (int i = 0; i < 50; i++) {
            User user = userDao.queryUserInfoById("1");
            logger.info("测试结果：{}", JSONUtil.toJsonStr(user));
        }
    }

    @Test
    public void test_pooled() throws SQLException, InterruptedException {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mytest?useUnicode=true");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("123456");
        // 持续获得链接
        while (true){
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            connection.close();
        }
    }*/

    @Test
    public void test_MapperProxyFactory6() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById("1");
        logger.info("测试结果：{}", JSONUtil.toJsonStr(user));
    }
}
