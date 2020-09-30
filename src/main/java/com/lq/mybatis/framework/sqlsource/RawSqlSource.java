package com.lq.mybatis.framework.sqlsource;

import com.lq.mybatis.framework.config.DynamicContext;
import com.lq.mybatis.framework.sqlnode.SqlNode;
import com.lq.mybatis.utils.GenericTokenParser;
import com.lq.mybatis.utils.ParameterMappingTokenHandler;

/**
 *  封装了不带有${}和动态标签的SQL信息，并提供他们的处理接口
 * @author liuqian293
 */
public class RawSqlSource implements SqlSource{

    //一个静态的sqlSource（Stat）
    private SqlSource sqlSource;

    public RawSqlSource(SqlNode mixedSqlNode) {
        //真正处理#{}的逻辑要放在改构造方法中

        //把处理之后的结果，封装成一个静态的sqlSource

        //1.处理所有的SQL节点，获取合并之后的完整的sql语句（可能还带有#{}）
        DynamicContext context = new DynamicContext(null);
        mixedSqlNode.apply(context);
        String sqlText = context.getSql();
        //2.处理SqlSource的处理逻辑，针对#{}
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}", tokenHandler);
        String sql = tokenParser.parse(sqlText);

        sqlSource = new StaticSqlSource(sql, tokenHandler.getParameterMappings());

    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return sqlSource.getBoundSql(param);
    }
}
