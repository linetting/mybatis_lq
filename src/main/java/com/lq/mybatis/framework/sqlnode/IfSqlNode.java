package com.lq.mybatis.framework.sqlnode;

import com.lq.mybatis.framework.config.DynamicContext;

import java.util.List;

/**
 *
 * @author liuqian293
 */
public class IfSqlNode implements SqlNode {

    private String test;

    private SqlNode mixedSqlNode;

    public IfSqlNode(String test, SqlNode mixedSqlNode) {
        this.test = test;
        this.mixedSqlNode = mixedSqlNode;
    }

    public void apply(DynamicContext context) {
        //使用OGNL对test中的内容进行判断（test属性值写的就是OGNL表达式的语法）
        if ( null ) {

        }
    }
}
