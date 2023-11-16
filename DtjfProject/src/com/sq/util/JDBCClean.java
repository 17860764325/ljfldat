package com.sq.util;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 增删改查语句：
 * insert into 表名 values(?,?,?,?,?)
 * delete  from 表名 where 条件
 * Update 表名 set 字段名=值 where 条件
 * select * from 表名 where 条件
 * 表名和类名类似，可以相互转换
 * 利用反射来的调用类进行加载驱动执行增删改查四个语句
 * 其中的每个屬性的值进行转换
 */
public class JDBCClean<T> {
    public static String driverClass = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://127.0.0.1:3306/ljflxcx?useSSL=false&serverTimezone=Asia/Shanghai";
    public static String username = "root";
    public static String password = "liuyinuo1314";

    /**
     *得到conn
     * 进行驱动的加载
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     *关闭sql注入以及conn的流
     * @param conn
     * @param ps
     */
    public static void closeRes(Connection conn, PreparedStatement ps) {
        try {
            if (conn != null) {
                conn.close();
            }

            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭conn，sql注入以及结果集的流
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeRes(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

