package com.lq.mybatis.framework.sqlnode;

import com.lq.mybatis.framework.config.DynamicContext;
import com.lq.mybatis.framework.sqlnode.SqlNode;

import java.util.List;

/**
 *
 * @author liuqian293
 */
public class MixedSqlNode implements SqlNode {

    private List<SqlNode> sqlNodeList;

    public MixedSqlNode(List<SqlNode> sqlNodeList) {
        this.sqlNodeList = sqlNodeList;
    }

    public void apply(DynamicContext context) {
        for (SqlNode sqlNode: sqlNodeList){
            sqlNode.apply(context);
        }
    }
}
