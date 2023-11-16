package com.sq.util;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCNotClean {
    public  void JDBC(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
            try {
                FileInputStream fi = new FileInputStream("D:\\java\\DtjfProject\\src\\com\\sq\\util\\MyJDBC.properties");
                Properties p = new Properties();
                p.load(fi);
                fi.close();
                String url = p.getProperty("url");
                String user = p.getProperty("username");
                String password = p.getProperty("password");
                //初始化驱动
            //驱动类com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动加载成功");
            conn = DriverManager.getConnection(url, user, password);
            //注意：使用的是java.sql.Statement
            //不要不小心使用到com.mysql.jdbc.Statement
            //获取数据库对象
            stmt = conn.createStatement();
            //向数据库进行增删改查语句
                if (sql.contains("select")){
                     rs =stmt.executeQuery(sql);
                     while(rs.next()){
//                         System.out.println(rs.getString("s_name"));
//                         System.out.println(rs.getString("c_id"));
//                         System.out.println(rs.getString("s_score"));

                     }

                }else{
                    stmt.execute(sql);
                }

            System.out.println("语句成功");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
