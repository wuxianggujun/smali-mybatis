package org.wuxianggujun.samlimybatis.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.wuxianggujun.samlimybatis.test.dao.IUserDao;
import org.wuxianggujun.smalimybatis.binding.MapperRegistry;
import org.wuxianggujun.smalimybatis.session.SqlSession;
import org.wuxianggujun.smalimybatis.session.SqlSessionFactory;
import org.wuxianggujun.smalimybatis.session.defaults.DefaultSqlSessionFactory;

@Slf4j
public class ApiTest {


    @Test
    public void test_MapperProxyFactory() {
        //注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMapper("org.wuxianggujun.samlimybatis.test.dao");
        //从sqlsession 工厂获取 session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String result = userDao.queryUserName("1");
        log.info(result);
    }


    @Test
    public void test_proxy_class() {
        log.debug("hhhh");
    }

}
