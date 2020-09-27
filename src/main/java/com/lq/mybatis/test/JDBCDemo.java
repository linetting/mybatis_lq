package com.lq.mybatis.test;

import org.junit.Test;

import java.sql.*;
import java.util.concurrent.Callable;

/**
 * 原生的jdbc连接
 *
 * @author liuqian293
 */
public class JDBCDemo {

    @Test
    public void test() {
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //通过驱动管理类获取数据库连接conncetion = DriverManager
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&serverTimezone=UTC","root","123456");

            //定义sql语句？表示占位符
            String sql = " select * from users where id = ? ";

            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);

            //设置参数
            preparedStatement.setString(1,"1");

            //向数据库发出sql执行查询，获取查询结果集
            rs = preparedStatement.executeQuery();

            //循环查询结果集
            while (rs.next()) {
                System.out.println(rs.getString("username")+" "+rs.getString("password"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接，释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
