package com.sq.shop;

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
import java.util.List;

public class ShopBuy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String age = req.getParameter("age");
        String phone_number = req.getParameter("phone_number");
        System.out.println(age);
        System.out.println(phone_number);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList list = new ArrayList();
        try{
            conn = JDBCClean.getConnection();
            if ("0".equals(age)){
                 sql = "select * from user_student where phone_number="+phone_number;
            }else if ("1".equals(age)){
                 sql = "select * from user_adult where phone_number="+phone_number;
            }else{
                sql = null;
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                list.add(new UserBuyInfo(rs.getString("username"),rs.getInt("user_jfnumber_last")));
                System.out.println("aaaaa"+list.toString());
            }
            Gson gson = new Gson();
            String json = gson.toJson(list);
            System.out.println("json"+json);
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
