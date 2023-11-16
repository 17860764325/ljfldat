package com.sq.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertUtil {
    public static boolean updatePHB(int lastnumber,int number,String sql){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = JDBCClean.getConnection();
            pstmt = conn.prepareStatement(sql);
              int rows= pstmt.executeUpdate();
            System.out.println(rows);
            int allnumber = lastnumber+number;
            pstmt.setInt(1,allnumber);
            boolean a = rs.next();
            if (a){
                return true;
            }else {
                return  false;
            }

        }catch(Exception e){
                e.printStackTrace();
    }finally {
            JDBCClean.closeRes(conn,pstmt,null);
        }
        return false;

        }


    public static int selectPHBnumber(String sql){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int a=0;
        try{
            conn = JDBCClean.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs  = pstmt.executeQuery();
            while (rs.next()){
                 a = rs.getInt("number");
            }
            System.out.println(a);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCClean.closeRes(conn,pstmt,rs);
        }
        return a;

    }

}
