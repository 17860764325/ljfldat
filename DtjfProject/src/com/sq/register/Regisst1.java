package com.sq.register;

import com.sq.util.JDBCClean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Regisst1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  phonenumber = req.getParameter("phone_number");
        System.out.println("电话号："+phonenumber);
        String password =  req.getParameter("password");
        System.out.println("密码："+password);
        String age = req.getParameter("age");
        System.out.println("成年人："+age);

        String sql1 = "insert into account_pwd(phone_number,age) values(?,?)";
        Connection conn1 = null;
        PreparedStatement pstmt1 = null;
        try{
            conn1 = JDBCClean.getConnection();
            pstmt1 = conn1.prepareStatement(sql1);
            System.out.println("aaaaaaaaaa");
            pstmt1.setString(1,phonenumber);
            pstmt1.setString(2,age);
            System.out.println("bbbbbbbbbbbb");
            int rows = pstmt1.executeUpdate();
            System.out.println(rows);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCClean.closeRes(conn1,pstmt1,null);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
