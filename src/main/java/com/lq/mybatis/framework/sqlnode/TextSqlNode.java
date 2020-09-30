package com.lq.mybatis.framework.sqlnode;

import com.lq.mybatis.framework.config.DynamicContext;
import com.lq.mybatis.utils.GenericTokenParser;
import com.lq.mybatis.utils.OgnlUtils;
import com.lq.mybatis.utils.SimpleTypeRegistry;
import com.lq.mybatis.utils.TokenHandler;

import java.util.List;

/**
 *
 * @author liuqian293
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    public void apply(DynamicContext context) {
        GenericTokenParser tokenPrasers = new GenericTokenParser("${","}", new BindingTokenHandler(context));
        String sql = tokenPrasers.parse(sqlText);
        //把${}解析完毕之后，把sql放入
        context.appendSql(sqlText);
    }

    /**
     * 内部类为了处理${}中的内容
     */
    private class BindingTokenHandler implements TokenHandler {

        //为了获取入参对象
        private DynamicContext context;

        public BindingTokenHandler(DynamicContext context) {
            this.context = context;
        }

        /**
         *
         * @param content 这就是${}中的参数名称
         * @return
         */
        @Override
        public String handleToken(String content) {
            Object parameter = context.getBindings().get("_parameter");
            if (SimpleTypeRegistry.isSimpleType(parameter.getClass())){
                return parameter.toString();
            }
            //否则使用OGNL表达式获取值
            Object value = OgnlUtils.getValue(content, parameter);
            return value == null ? "" : value.toString();
        }
    }
}
