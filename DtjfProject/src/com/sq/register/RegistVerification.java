package com.sq.register;

import com.sq.util.JDBCClean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegistVerification extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone_number");
        System.out.println(phone);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet  rs = null;
        try{
            conn = JDBCClean.getConnection();
            String sql = "select * from account_pwd where  phone_number='"+phone+"'";
            pstmt = conn.prepareStatement(sql);
            rs =  pstmt.executeQuery();
            boolean a = rs.next();
            System.out.println(a);
            if (a){
                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json");
                resp.getWriter().write("true");
                System.out.println("a");
            }else{
                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json");
                resp.getWriter().write("false");
                System.out.println("b");
            }
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
