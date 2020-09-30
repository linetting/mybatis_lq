package com.lq.mybatis.framework.sqlnode;

import com.lq.mybatis.framework.config.DynamicContext;

import java.util.List;

/**
 *
 * @author liuqian293
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    public void apply(DynamicContext context) {
        context.appendSql(sqlText);
    }
}
