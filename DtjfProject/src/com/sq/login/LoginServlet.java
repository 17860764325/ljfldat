package com.sq.login;

import com.google.gson.Gson;
import com.sq.util.JDBCClean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone_number = req.getParameter("phone_number");
        System.out.println(phone_number);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = JDBCClean.getConnection();
            String sql  = "select * from  account_pwd  where phone_number=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,phone_number);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String age = rs.getString("age");
                System.out.println("成年人/学生:"+age);
                ArrayList list = new ArrayList();
                Gson gson = new Gson();
                     list.add("true");
                     list.add(age);
                     String json = gson.toJson(list);
                     System.out.println(list.toString() );
                     resp.getWriter().write(json );
                     System.out.println(json);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCClean.closeRes(conn,pstmt,rs);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
