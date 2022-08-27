package org.wuxianggujun.smalimybatis.binding;

import org.wuxianggujun.smalimybatis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * {@code @description} 映射器代理工厂
 */
public class MapperProxyFactory<T> {

    //需要代理的接口
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }


}
