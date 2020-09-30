package com.lq.mybatis.framework.sqlnode;

import com.lq.mybatis.framework.config.DynamicContext;

/**
 * @author liuqian293
 */
public interface SqlNode {
    /**
     *
     * @param dynamicContext
     */
    void apply(DynamicContext dynamicContext);
}
