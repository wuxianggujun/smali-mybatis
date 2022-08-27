package org.wuxianggujun.smalimybatis.binding;

import lombok.extern.slf4j.Slf4j;
import org.wuxianggujun.smalimybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * {@code @description} 映射器代理类
 */
@Slf4j
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final Long serialVersionUID = -2223878349372496020L;

    private SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            log.info(mapperInterface.getName());
            return sqlSession.selectOne(method.getName(), args);
        }
    }
}
