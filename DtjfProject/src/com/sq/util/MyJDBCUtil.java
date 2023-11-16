/**
 * 
 */
package com.sq.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author ���Ȼ
 *
 */
public class MyJDBCUtil {
    public static String driverClass = "";
    public static String url = "";
    public static String username = "";
    public static String password = "";
    static {
        Statement stmt = null;
        try {
            //从文件中读取输入流
            FileInputStream fis = new FileInputStream("D:\\java\\DtjfProject\\src\\com\\sq\\util\\MyJDBC.properties");
            //创建Properties对象
            Properties pro = new Properties();
            //从流中加载数据
            pro.load(fis);
            //关闭流
            fis.close();
            //从Properties中读取信息
            driverClass = pro.getProperty("driverClass");
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	public static Connection getConnection() throws Exception {
        Class.forName(driverClass);
		return DriverManager.getConnection(url, username, password);
	}
	public static void releaseResource(Connection conn,Statement stmt,ResultSet rs) throws Exception {
		if(rs!=null) {
			rs.close();
		}
		if(stmt!=null) {
			stmt.close();
		}
		if(conn!=null) {
			conn.close();
		}
	  }
	
}

