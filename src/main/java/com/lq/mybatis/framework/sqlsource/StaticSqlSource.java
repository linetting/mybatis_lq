package com.lq.mybatis.framework.sqlsource;

import com.lq.mybatis.framework.config.ParameterMapping;
import com.lq.mybatis.framework.sqlnode.SqlNode;

import java.util.List;

/**
 * @author liuqian293
 */
public class StaticSqlSource implements SqlSource {


    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {

    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
