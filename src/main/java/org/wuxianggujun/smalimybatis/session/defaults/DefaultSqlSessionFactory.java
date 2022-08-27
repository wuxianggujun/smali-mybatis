package org.wuxianggujun.smalimybatis.session.defaults;

import org.wuxianggujun.smalimybatis.binding.MapperRegistry;
import org.wuxianggujun.smalimybatis.session.SqlSession;
import org.wuxianggujun.smalimybatis.session.SqlSessionFactory;

/**
 * 默认的 DefaultSqlSessionFactory
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
