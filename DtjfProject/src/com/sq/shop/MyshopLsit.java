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

public class MyshopLsit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phonenumber = req.getParameter("phonenumber");
        System.out.println(phonenumber);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list =  new ArrayList();
        try{
            conn = JDBCClean.getConnection();
            String sql = "SELECT dd_address,dd_phonenumber,shop_id,dd_buynumber,shop_name,shop_price,shop_img FROM dd,shopping WHERE dd.dd_phonenumber='"+phonenumber+"' AND shopping.shop_id=dd.dd_shop_id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                list.add(new MyshopDao(rs.getInt("shop_id"),rs.getString("shop_name"),rs.getInt("shop_price"),rs.getString("shop_img"),rs.getInt("dd_buynumber"),rs.getString("dd_address"),rs.getString("dd_phonenumber")));
            }

            System.out.println(list);

            Gson gson = new Gson();
            String json = gson.toJson(list);
            System.out.println(json);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json");
            resp.getWriter().write(json);
        }catch(Exception e){
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
