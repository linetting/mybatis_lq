package com.lq.mybatis.framework.config;

import com.lq.mybatis.framework.sqlsource.SqlSource;

/**
 * 封装了statement标签中的信息
 * @author liuqian293
 */
public class MappedStatement {

    private String statementId;

    private String statementType;

    private String resultType;

    private Class resultTypeClass;

    private SqlSource sqlSource;

    public MappedStatement(String statementId, String statementType, String resultType, Class resultTypeClass, SqlSource sqlSource){
        this.statementId = statementId;
        this.statementType = statementType;
        this.resultType = resultType;
        this.resultTypeClass = resultTypeClass;
        this.sqlSource = sqlSource;

    }

    public String getStatementType() {
        return null;
    }

    public SqlSource getSqlSource() {
        return null;
    }
}
