package org.wuxianggujun.smalimybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import org.wuxianggujun.smalimybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, MapperProxyFactory<?>>();


    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to this MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance .Cause: " + e, e);
        }

    }


    public <T> void addMapper(Class<T> type) {
        //mapper 必须是接口才会注册
        if (type.isInterface()) {
            if (hasMapper(type)) {
                //如果重复添加，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            //注册映射器工厂
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }


    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMapper(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> aClass : mapperSet) {
            addMapper(aClass);
        }
    }
}
