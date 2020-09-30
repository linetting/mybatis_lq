package com.lq.mybatis.framework.sqlsource;

/**
 * @author liuqian293
 */
public interface SqlSource {

    /**
     * 针对封装的SQL信息，去进行解析，获取可以直接执行的SQL语句
     * @param param
     * @return
     */
    BoundSql getBoundSql(Object param);
}
