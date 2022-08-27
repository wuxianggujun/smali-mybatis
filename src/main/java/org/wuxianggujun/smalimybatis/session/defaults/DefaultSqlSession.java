package org.wuxianggujun.smalimybatis.session.defaults;

import org.wuxianggujun.smalimybatis.binding.MapperRegistry;
import org.wuxianggujun.smalimybatis.session.SqlSession;

/**
 * 默认 SqlSession实现类
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }


    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了! " + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了! " + statement + "入参: " + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
