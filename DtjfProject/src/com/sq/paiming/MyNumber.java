package com.sq.paiming;

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

public class MyNumber extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String age = req.getParameter("age");
        System.out.println("成年人"+age);
        String phonenumber = req.getParameter("phonenumber");
        System.out.println("电话号"+phonenumber);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list  = new ArrayList();
        String sql ="";
        if ("0".equals(age)){
            sql = "select user_jfnumber_last,user_jfnumber_all,user_answer_number from user_student where phone_number='"+phonenumber+"'";
        }else if("1".equals(age)){
            sql = "select user_jfnumber_last,user_jfnumber_all,user_answer_number from user_adult where phone_number='"+phonenumber+"'";
        }
        try{
            conn = JDBCClean.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                list.add(new Number(rs.getInt("user_jfnumber_all"),rs.getInt("user_answer_number"),rs.getInt("user_jfnumber_last")));
            }
            System.out.println(list);
            Gson gson = new Gson();
            String json = gson.toJson(list);
            System.out.println(json);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json");
            resp.getWriter().write(json);

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
