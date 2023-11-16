package com.sq.dtlist;

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
import java.util.Arrays;

public class DtList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //随机数算法（不带有重复数字）

        int id1 = (int) (1 + Math.random() * 36);//判断，选择
        if (id1==15){
            int ab = (int) (16 + Math.random() * 19);
            id1 = ab;
        }
        int id2 = (int) (37 + Math.random() * 24);//选择
        int id3 = (int) (62 + Math.random() * 17);//多选
        int id4 = (int) (81 + Math.random() * 9);//判断
        int id5 = (int) (91 + Math.random() * 29);//选择
        int id6  = (int) (121 + Math.random() * 29);//选择
        int id7  = (int) (192 + Math.random() * 27);//选择
        int id8 = (int) (223 + Math.random() * 41);//多选
        int id9 = (int) (265 + Math.random() * 3);//单选
        int id10  = (int) (170 + Math.random() * 20);//选择



        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try{
            conn = JDBCClean.getConnection();
//                String sql = "SELECT questions,option_A,option_B,option_C,option_D,question_true,question_type FROM question WHERE id='"+id1+"' or id='"+id2+"' or id='"+id3+"'OR id='"+id4+"' OR id='"+id5+"' OR id='"+id6+"' OR  id='"+id7+"' OR  id='"+id8+"' OR id='"+id9+"'OR id='"+id10+"' order by id desc";
                String sql = "SELECT questions,option_A,option_B,option_C,option_D,question_true,question_type FROM question  order by id desc limit 10";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                list.add(new DtListDao(rs.getString("questions"),rs.getString("option_A"),rs.getString("option_B"),rs.getString("option_C"),rs.getString("option_D"),rs.getString("question_true"),rs.getInt("question_type")));
            }
            System.out.println(list);

            Gson gson =  new Gson();
            String json = gson.toJson(list);
            System.out.println(json);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json");
            resp.getWriter().write(json);

        }catch(Exception e ){
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
