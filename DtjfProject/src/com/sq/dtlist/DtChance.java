package com.sq.dtlist;

import com.sq.util.JDBCClean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DtChance extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateFormat  df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();// 获取当前时间
        String dd = df.format(date);
        System.out.println("现在时间：" + df.format(date)); // 输出已经格式化的现在时间（24小时制）

        String age =  req.getParameter("age");
         System.out.println(age);
        String phonenumber = req.getParameter("phonenumber");
        System.out.println(phonenumber);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try{
            Date d1 = df.parse(dd);
            if ("0".equals(age)){
                sql = "select user_answer_number,time,userdan from user_student where phone_number='"+phonenumber+"'";
                conn = JDBCClean.getConnection();
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                String time = "";
                int answer_number = 0;
                int userdan = 0;
                while(rs.next()){
                     time = rs.getString("time");
                    System.out.println("数据库中的时间"+time);
                    answer_number = rs.getInt("user_answer_number");
                    System.out.println("上一次还剩多少机会："+answer_number);
                    userdan = rs.getInt("userdan");
                    System.out.println("今天已经答题"+userdan+"了");
                }

                Date d2 = df.parse(time);

                long diff = d1.getTime() - d2.getTime();
                long days = diff/(1000*60*60*24);
                long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);

                    if(answer_number==0){
                        resp.setCharacterEncoding("utf-8");
                        resp.setContentType("application/json");
                        resp.getWriter().write("false");
                    }else if (answer_number>0)
                    {
                        int a= answer_number-1;
                        int b = userdan + 1;
                        String sql1 = "update user_student set user_answer_number='"+a+"',time='"+dd+"',userdan='"+b+"' where phone_number='"+phonenumber+"'";
                        Connection conn1 = null;
                        PreparedStatement pstmt1 = null;
                        try{
                            conn1 = JDBCClean.getConnection();
                            pstmt1 = conn1.prepareStatement(sql1);
                            int rows = pstmt1.executeUpdate();
                            if (rows>0){
                                resp.setCharacterEncoding("utf-8");
                                resp.setContentType("application/json");
                                resp.getWriter().write("true");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            JDBCClean.closeRes(conn1,pstmt1,null);
                        }
                    }


            }else if ("1".equals(age)){
                sql = "select  user_answer_number,time,userdan from user_adult where phone_number='"+phonenumber+"'";
                conn = JDBCClean.getConnection();
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                String time = " ";
                int answer_number = 0;
                int userdan = 0;
                while(rs.next()){
                    time = rs.getString("time");
                    System.out.println("数据库中的时间"+time);
                    answer_number = rs.getInt("user_answer_number");
                    System.out.println("上一次还剩多少机会："+answer_number);
                    userdan  = rs.getInt("userdan");
                    System.out.println("今天看已经答题"+userdan+"了");
                }

                Date d2 = df.parse(time);

                long diff = d1.getTime() - d2.getTime();
                long days = diff/(1000*60*60*24);
                long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                System.out.println("差的天数"+days);
                    if(answer_number==0){
                        resp.setCharacterEncoding("utf-8");
                        resp.setContentType("application/json");
                        resp.getWriter().write("false");
                    }else if (answer_number>0)
                    {
                        int a= answer_number-1;
                        int b = userdan+1;
                        String sql1 = "update user_adult set user_answer_number='"+a+"',time='"+dd+"',userdan='"+b+"' where phone_number='"+phonenumber+"'";
                        Connection conn1 = null;
                        PreparedStatement pstmt1 = null;
                        try{
                            conn1 = JDBCClean.getConnection();
                            pstmt1 = conn1.prepareStatement(sql1);
                            int rows = pstmt1.executeUpdate();
                            if (rows>0){
                                resp.setCharacterEncoding("utf-8");
                                resp.setContentType("application/json");
                                resp.getWriter().write("true");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            JDBCClean.closeRes(conn1,pstmt1,null);
                        }
                    }

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
