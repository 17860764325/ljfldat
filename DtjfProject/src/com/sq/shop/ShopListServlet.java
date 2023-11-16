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

public class ShopListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs =null;
        ArrayList list = new ArrayList();
        try{
            conn = JDBCClean.getConnection();
            String sql = "select * from shopping";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){

                list.add(new ShoppingDao(rs.getInt("shop_id"),rs.getString("shop_name"),rs.getDouble("shop_price"),rs.getString("shop_img")));
            }
            System.out.println(list);

            Gson gson = new Gson();
            String json = gson.toJson(list);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json");
            resp.getWriter().write(json);

        }catch (Exception e ){
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
