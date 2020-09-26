package com.lq.mybatis.test;

import java.sql.*;
import java.util.concurrent.Callable;

/**
 * 原生的jdbc连接
 *
 */
public class JDBCDemo {

    public void test() {
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //
            connection = DriverManager.getConnection("","","");

            String sql = "select * from max where unit_code =?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,"ZHI");

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("unit_name")+" "+rs.getString("unit_code"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
