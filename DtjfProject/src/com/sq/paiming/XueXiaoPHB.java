package com.sq.paiming;

import com.google.gson.Gson;
import com.sq.PHB.PHB;
import com.sq.PHB.PHBfactory;
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

public class XueXiaoPHB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //        //设置响应头允许ajax跨域访问
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String PHB_number = req.getParameter("index");
        System.out.println(PHB_number);
        String json = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<PHB> phb = new ArrayList<PHB>();
        conn = JDBCClean.getConnection();
        try {
            if ("1".equals(PHB_number)) {
                String sql = "select * from phb_school order by number desc";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    PHBfactory pf = new PHBfactory();
                    PHB ps = pf.getPHB("school", rs.getString("School_Name"), rs.getInt("number"));
                    phb.add(ps);
                    System.out.println(ps.toString());
                }
                System.out.println(phb.toString());
                Gson gson = new Gson();
                json = gson.toJson(phb);
                System.out.println(json);
                resp.getWriter().write(json);
            } else if ("3".equals(PHB_number)) {
                String sql = "select * from phb_nianji order by number desc";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    PHBfactory pf = new PHBfactory();
                    PHB ps = pf.getPHB("nianji", rs.getString("Nianji_Name"), rs.getInt("number"));
                    phb.add(ps);
                }
                Gson gson = new Gson();
                json = gson.toJson(phb);
                System.out.println(json);
                resp.getWriter().write(json);

            } else if ("2".equals(PHB_number)) {
                String sql = "select * from user_student order by user_jfnumber_all desc limit 500";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    PHBfactory pf = new PHBfactory(); 
                    PHB ps = pf.getPHB("people", rs.getString("username"), rs.getInt("user_jfnumber_all"));
                    phb.add(ps);
                    System.out.println(ps.toString());
                }
                Gson gson = new Gson();
                json = gson.toJson(phb);
                System.out.println(json);
                resp.getWriter().write(json);

            } else if ("4".equals(PHB_number)) {
                String sql = "select * from phb_jiedao order by number desc";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    PHBfactory pf = new PHBfactory();
                    PHB ps = pf.getPHB("jiedao", rs.getString("Jiedao_Name"), rs.getInt("number"));
                    phb.add(ps);
                    System.out.println(ps.toString());
                }
                Gson gson = new Gson();
                json = gson.toJson(phb);
                System.out.println(json);
                resp.getWriter().write(json);

            } else if ("5".equals(PHB_number)) {
                String sql = "select * from phb_qiye order by number desc limit 100";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    PHBfactory pf = new PHBfactory();
                    PHB ps = pf.getPHB("qiye", rs.getString("Qiye_Name"), rs.getInt("number"));
                    phb.add(ps);
                    System.out.println(ps.toString());
                }
                Gson gson = new Gson();
                json = gson.toJson(phb);
                System.out.println(json);
                resp.getWriter().write(json);

            } else if ("6".equals(PHB_number)) {
                String sql = "select * from user_adult order by user_jfnumber_all desc limit 500 ";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    PHBfactory pf = new PHBfactory();
                    PHB ps = pf.getPHB("qypeople", rs.getString("username"), rs.getInt("user_jfnumber_all"));
                    phb.add(ps);
                    System.out.println(ps.toString());
                }
                Gson gson = new Gson();
                json = gson.toJson(phb);
                System.out.println(json);
                resp.getWriter().write(json);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCClean.closeRes(conn,pstmt,rs);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
