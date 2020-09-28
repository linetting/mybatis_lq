package com.lq.mybatis.test;

import com.lq.mybatis.framework.config.Configuration;
import com.lq.mybatis.framework.config.MappedStatement;
import com.lq.mybatis.framework.sqlsource.SqlSource;
import com.lq.mybatis.po.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import javax.security.auth.login.CredentialNotFoundException;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

public class MybatisV2 {

    private Configuration configuration;

    //根据用户参数，不只一个，查询用户信息
    @Test
    public void test() {
        //加载XML文件（全局配置文件和映射文件）
        loadXML("mybatis-config.xml");

        //执行查询
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("username", "test");
        param.put("age", 1);
        List<User> userList = selectList("test.queryUserByParams", param);
    }

    private void loadXML(String location) {
        configuration = new Configuration();
        //TODO 解析XML文件，最终将信息封装到Configuration对象中
        //获取全局配置文件对应的流对象
        InputStream is = getResourceAsStream(location);
        //获取Document对象
        Document document = getDocument(is);
        //根据xml语义进行解析
        parseConfiguration(document.getRootElement());
    }

    //这里的入参为Objtct，Object包含对象和Map的任意封装
    private <T> List<T> selectList(String statementId, Map<String, Object> param) {
        List<T> results = new ArrayList<T>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            //获取
            MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);

            //连接的获取
            connection = getConnection();
            //SQL的获取
            String sql = getSql(mappedStatement);
            //创建statement
            statement = createStatement(mappedStatement, sql, connection);
            //设置参数
            setParameters(param, statement, mappedStatement);
            //执行statement
            rs = executeQuery(statement);
            //处理结果
            handleResult(rs, mappedStatement, results);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if ( rs != null ) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if ( rs != null ){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }




        return null;
    }

    private ResultSet executeQuery(Statement statement) {
        return null;
    }

    private <T> void handleResult(ResultSet rs, MappedStatement mappedStatement, List<T> results) {

    }

    private void setParameters(Object param, Statement statement, MappedStatement mappedStatement) throws SQLException {
        if ( statement instanceof PreparedStatement) {
            PreparedStatement preparedStatement = null;

            //设置参数，第一个参数
            //preparedStatement.setObject(1, param)
            //如果入参是简单类型，那么我们不关心参数名称
            if (param instanceof Integer || param instanceof String) {
                preparedStatement.setObject(1, param);
            }else if (param instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) param;
                //TODO 需要解析#{}之后封装的参数集合List<ParameterMapping>
                /*String columnnames = properties.getProperty("db.sql"+statementId+".columuname");
                String[] nameArray = columnnames.split(",");
                if ( nameArray !=null && nameArray.length > 0) {
                    for (int i=0; i<nameArray.length; i++) {
                        String name = nameArray[i];
                        Object value = map.get(name);
                        //给map集合中的参数赋值

                    }
                }*/
            }
        }
    }

    private Statement createStatement(MappedStatement mappedStatement, String sql, Connection connection) {
        String statementType = mappedStatement.getStatementType();
        if ("preparedStatement".equals(statementType)){

        }
        return null;
    }

    /**
     * 获取sql
     * @param mappedStatement
     * @return
     */
    private String getSql(MappedStatement mappedStatement) {
        return null;
    }

    private Connection getConnection() throws SQLException {
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
    }

    private void parseConfiguration(Element rootElement) {
        Element environments = rootElement.element("environment");
        parseEnvironments(environments);
        Element mappers = rootElement.element("mappers");
        parseMappers(mappers);
    }

    private void parseMappers(Element mappers) {
    }

    private void parseEnvironments(Element environments) {
    }

    private Document getDocument(InputStream is) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
