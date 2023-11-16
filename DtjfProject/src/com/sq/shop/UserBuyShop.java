package com.sq.shop;

import com.sq.util.JDBCClean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserBuyShop extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //购买着名字
        String buyername = req.getParameter("shop_buyername");
        System.out.println(buyername);

        //购买商品id
        String shop_id = req.getParameter("shop_id");
        System.out.println(shop_id);

        //购买成年人/学生
        String shop_age = req.getParameter("shop_age");
        System.out.println(shop_age);

        //购买有多少积分
        String shop_jfnumber = req.getParameter("shop_jfnumber");
        System.out.println(shop_jfnumber);

        //购买者电话
        String shop_phonenumber = req.getParameter("shop_phonenumber");
        System.out.println(shop_phonenumber);

        //购买者地址
        String address = req.getParameter("address");
        System.out.println(address);

        //购买者购买数量
        String buynumber = req.getParameter("buynumber");
        System.out.println(buynumber);

        //购买商品单价
        String shop_price = req.getParameter("shop_price");
        System.out.println(shop_price);

        Integer price = new Integer(shop_price);
        System.out.println(price);
        Integer buy_number = new Integer(buynumber);
        System.out.println(buy_number);
        Integer jfnumber = new Integer(shop_jfnumber);
        System.out.println(jfnumber);


        if (jfnumber>=price*buy_number){
            Connection conn = null;
            PreparedStatement pstmt = null;
            try{
                conn = JDBCClean.getConnection();
                String sql = " insert into dd(dd_name,dd_phonenumber,dd_shop_id,dd_buynumber,dd_age) values(?,?,?,?,?) ";
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1,buyername);
                pstmt.setString(2,shop_phonenumber);
                pstmt.setString(3,shop_id);
                pstmt.setInt(4,buy_number);
                pstmt.setString(5,shop_age);

                int rows = pstmt.executeUpdate();
                if (rows>0){
                    int lastjf = jfnumber-price*buy_number;
                    Connection conn1 = null;
                    PreparedStatement pstmt1 = null;
                    try{
                        conn1 = JDBCClean.getConnection();
                        System.out.println(lastjf);
                        String sql1 = null;
                        if ("0".equals(shop_age)){
                            sql1 = " update user_student set user_jfnumber_last='"+lastjf+"'   where phone_number='"+shop_phonenumber+"'";
                        }else if("1".equals(shop_age)){
                            sql1 = " update user_adult set user_jfnumber_last='"+lastjf+"'   where phone_number='"+shop_phonenumber+"'";
                        }else{
                            sql1 = null;
                        }

                        pstmt1 = conn1.prepareStatement(sql1);
                        int row = pstmt1.executeUpdate();
                        System.out.println("被影响行数"+row);
                        if (row>0){
                            resp.setCharacterEncoding("utf-8");
                            resp.setContentType("application/json");
                            resp.getWriter().write("true");
                        }else{
                            resp.setCharacterEncoding("utf-8");
                            resp.setContentType("application/json");
                            resp.getWriter().write("false");
                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        JDBCClean.closeRes(conn1,pstmt1,null);
                    }



                }else{
                    resp.setCharacterEncoding("utf-8");
                    resp.setContentType("application/json");
                    resp.getWriter().write("false");
                }

            }catch(Exception e){
                e.printStackTrace();
            }finally {
                JDBCClean.closeRes(conn,pstmt,null);
            }

        }else if (jfnumber<price*buy_number){
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json");
            System.out.println("积分不足");
            resp.getWriter().write("false");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
