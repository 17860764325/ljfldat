package com.sq.dtlist;

import com.sq.util.InsertUtil;
import com.sq.util.JDBCClean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DtRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String age = req.getParameter("age");
        System.out.println(age);
        String phonenumber = req.getParameter("phonenumber");
        System.out.println(phonenumber);
        String n = req.getParameter("number");
        System.out.println(n);

        Integer number = new Integer(n);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if ("0".equals(age)) {
                sql = "select user_school,user_class,user_jfnumber_all,user_jfnumber_last  from user_student where phone_number='" + phonenumber + "'";
                conn = JDBCClean.getConnection();
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String user_school = rs.getString("user_school");
                    String user_class = rs.getString("user_class");
                    int number_all = rs.getInt("user_jfnumber_all");
                    int number_last = rs.getInt("user_jfnumber_last");

                    int school_lastnumber = 0;
                    int ffnumber = 0;
                    Connection conn1 = null;
                    PreparedStatement pstmt1 = null;
                    ResultSet rs1 = null;
                    try {
                        String sql1 = "select number from phb_school where School_Name='" + user_school + "'";
                        conn1 = JDBCClean.getConnection();
                        pstmt1 = conn1.prepareStatement(sql1);
                        rs1 = pstmt1.executeQuery();
                        while (rs1.next()) {
                            school_lastnumber = rs1.getInt("number");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        JDBCClean.closeRes(conn1, pstmt1, rs1);
                    }
                    ffnumber = number + school_lastnumber;
                    Connection conn2 = null;
                    PreparedStatement pstmt2 = null;
                    try {
                        String sql2 = "update phb_school set number='" + (ffnumber) + "' where School_Name='" + user_school + "'";
                        conn2 = JDBCClean.getConnection();
                        pstmt2 = conn2.prepareStatement(sql2);
                        int rows = pstmt2.executeUpdate();
                        System.out.println("学校积分增加变更行数" + rows);
                        if (rows > 0) {
                            resp.setCharacterEncoding("utf-8");
                            resp.setContentType("application/json");
                            resp.getWriter().write("true");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        JDBCClean.closeRes(conn2, pstmt2, null);
                    }
//////////////////////////////////////////////////////////////////
                    int class_lastnumber = 0;
                    int ffnumber1 = 0;
                    Connection conn3 = null;
                    PreparedStatement pstmt3 = null;
                    ResultSet rs3 = null;
                    try {
                        String sql3 = "select number from phb_nianji where Nianji_Name='" + user_class + "'";
                        conn3 = JDBCClean.getConnection();
                        pstmt3 = conn3.prepareStatement(sql3);
                        rs3 = pstmt3.executeQuery();
                        while (rs3.next()) {
                            class_lastnumber = rs3.getInt("number");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        JDBCClean.closeRes(conn3, pstmt3, rs3);
                    }
                    ffnumber1 = number + class_lastnumber;
                    Connection conn4 = null;
                    PreparedStatement pstmt4 = null;
                    try {
                        String sql4 = "update phb_nianji set number='" + (ffnumber1) + "' where Nianji_Name='" + user_class + "'";
                        conn4 = JDBCClean.getConnection();
                        pstmt4 = conn4.prepareStatement(sql4);
                        int rows4 = pstmt4.executeUpdate();
                        System.out.println("年级积分增加变更行数" + rows4);
                        if (rows4 > 0) {
                            resp.setCharacterEncoding("utf-8");
                            resp.setContentType("application/json");
                            resp.getWriter().write("true");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        JDBCClean.closeRes(conn4, pstmt4, null);
                    }
                    int ffnumber2 = number + number_all;
                    int ffnumber3 = number_last + number;
                    Connection conn5 = null;
                    PreparedStatement pstmt5 = null;
                    try {
                        String sql5 = "update user_student set user_jfnumber_all='" + (ffnumber2) + "',user_jfnumber_last='" + (ffnumber3) + "' where phone_number='" + phonenumber + "'";
                        conn5 = JDBCClean.getConnection();
                        pstmt5 = conn5.prepareStatement(sql5);
                        int rows5 = pstmt5.executeUpdate();
                        System.out.println("学生表格本人分数更改行数" + rows5);
                        if (rows5 > 0) {
                            resp.setCharacterEncoding("utf-8");
                            resp.setContentType("application/json");
                            resp.getWriter().write("true");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        JDBCClean.closeRes(conn5, pstmt5, null);
                    }
                }
            } else if ("1".equals(age)) {
                sql = "select user_qiye,user_jiedao,user_jfnumber_all,user_jfnumber_last from user_adult where phone_number='" + phonenumber + "'";
                conn = JDBCClean.getConnection();
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String user_qiye = rs.getString("user_qiye");
                    String user_jiedao = rs.getString("user_jiedao");
                    int number_all = rs.getInt("user_jfnumber_all");
                    int number_last = rs.getInt("user_jfnumber_last");

                    if (user_qiye == null) {
                        int jiedao_lastnumber = 0;
                        int ffnumber = 0;
                        Connection conn1 = null;
                        PreparedStatement pstmt1 = null;
                        ResultSet rs1 = null;
                        try {
                            String sql1 = "select number from phb_jiedao where Jiedao_Name='" + user_jiedao + "'";
                            conn1 = JDBCClean.getConnection();
                            pstmt1 = conn1.prepareStatement(sql1);
                            rs1 = pstmt1.executeQuery();
                            while (rs1.next()) {
                                jiedao_lastnumber = rs1.getInt("number");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            JDBCClean.closeRes(conn1, pstmt1, rs1);
                        }
                        ffnumber = jiedao_lastnumber + number;
                        Connection conn2 = null;
                        PreparedStatement pstmt2 = null;
                        try {
                            String sql2 = "update phb_jiedao set number='" + ffnumber + "' where Jiedao_Name='" + user_jiedao + "'";
                            conn2 = JDBCClean.getConnection();
                            pstmt2 = conn2.prepareStatement(sql2);
                            int rows = pstmt2.executeUpdate();
                            System.out.println("街道影响行数" + rows);
                            if (rows > 0) {
                                resp.setCharacterEncoding("utf-8");
                                resp.setContentType("application/json");
                                resp.getWriter().write("true");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            JDBCClean.closeRes(conn2, pstmt2, null);
                        }

                    } else if (user_jiedao == null) {
                        int qiye_lastnumber = 0;
                        int ffnumber = 0;
                        Connection conn1 = null;
                        PreparedStatement pstmt1 = null;
                        ResultSet rs1 = null;
                        try {
                            String sql1 = "select number from phb_qiye where Qiye_Name='" + user_qiye + "'";
                            conn1 = JDBCClean.getConnection();
                            pstmt1 = conn1.prepareStatement(sql1);
                            rs1 = pstmt1.executeQuery();
                            while (rs1.next()) {
                                qiye_lastnumber = rs1.getInt("number");
                                System.out.println("企业原本积分" + qiye_lastnumber);
                            }
                            System.out.println("企业原本积分" + qiye_lastnumber);
                            ffnumber = qiye_lastnumber + number;
                            System.out.println("将要加入的积分" + number);
                            System.out.println("总积分" + ffnumber);
                            Connection conn2 = null;
                            PreparedStatement pstmt2 = null;
                            try {
                                String sql2 = "update phb_qiye set number='" + ffnumber + "' where Qiye_Name='" + user_qiye + "'";
                                conn2 = JDBCClean.getConnection();
                                pstmt2 = conn2.prepareStatement(sql2);
                                int rows = pstmt2.executeUpdate();
                                System.out.println("企业影响行数" + rows);
                                if (rows > 0) {
                                    resp.setCharacterEncoding("utf-8");
                                    resp.setContentType("application/json");
                                    resp.getWriter().write("true");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                JDBCClean.closeRes(conn2, pstmt2, null);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            JDBCClean.closeRes(conn1, pstmt1, rs1);
                        }


                        //成人街道和企业都不是空的时候，那就加到企业里面<-----？？？？？？我当时脑子是傻了吗？？？？
                        //现在改成了，如果又有单位又有街道那么久，两项都进行加分
                    } else if (user_jiedao != null && user_qiye != null) {
                        int qiye_lastnumber = 0;
                        int ffnumber = 0;
                        Connection conn1 = null;
                        PreparedStatement pstmt1 = null;
                        ResultSet rs1 = null;
                        try {
                            String sql1 = "select number from phb_qiye where Qiye_Name='" + user_qiye + "'";
                            conn1 = JDBCClean.getConnection();
                            pstmt1 = conn1.prepareStatement(sql1);
                            rs1 = pstmt1.executeQuery();
                            while (rs1.next()) {
                                qiye_lastnumber = rs1.getInt("number");
                                System.out.println("企业原本积分" + qiye_lastnumber);
                            }
                            System.out.println("企业原本积分" + qiye_lastnumber);
                            ffnumber = qiye_lastnumber + number;
                            System.out.println("将要加入的积分" + number);
                            System.out.println("总积分" + ffnumber);
                            Connection conn2 = null;
                            PreparedStatement pstmt2 = null;
                            try {
                                String sql2 = "update phb_qiye set number='" + ffnumber + "' where Qiye_Name='" + user_qiye + "'";
                                conn2 = JDBCClean.getConnection();
                                pstmt2 = conn2.prepareStatement(sql2);
                                int rows = pstmt2.executeUpdate();
                                System.out.println("企业影响行数" + rows);

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                JDBCClean.closeRes(conn2, pstmt2, null);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            JDBCClean.closeRes(conn1, pstmt1, rs1);
                        }
/////////////////////////////////////////////////////////
                        int jiedao_lastnumber = 0;
                        int ffnumber3 = 0;
                        Connection conn3 = null;
                        PreparedStatement pstmt3 = null;
                        ResultSet rs3 = null;
                        try {
                            String sql3 = "select number from phb_jiedao where Jiedao_Name='" + user_jiedao + "'";
                            conn3 = JDBCClean.getConnection();
                            pstmt3 = conn3.prepareStatement(sql3);
                            rs3 = pstmt3.executeQuery();
                            while (rs3.next()) {
                                jiedao_lastnumber = rs3.getInt("number");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            JDBCClean.closeRes(conn3, pstmt3, rs3);
                        }
                        ffnumber3 = jiedao_lastnumber + number;
                        Connection conn4 = null;
                        PreparedStatement pstmt4 = null;
                        try {
                            String sql4 = "update phb_jiedao set number='" + ffnumber3 + "' where Jiedao_Name='" + user_jiedao + "'";
                            conn4 = JDBCClean.getConnection();
                            pstmt4 = conn4.prepareStatement(sql4);
                            int rows4 = pstmt4.executeUpdate();
                            System.out.println("街道影响行数" + rows4);
                            if (rows4 > 0) {
                                resp.setCharacterEncoding("utf-8");
                                resp.setContentType("application/json");
                                resp.getWriter().write("true");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            JDBCClean.closeRes(conn4, pstmt4, null);
                        }
                        //若是既没有企业，也没有街道那么就不用执行
                    } else if (user_jiedao == null && user_qiye == null) {
                        resp.setCharacterEncoding("utf-8");
                        resp.setContentType("application/json");
                        resp.getWriter().write("null");
                    }
                    int ffnumber2 = number + number_all;
                    int ffnumber3 = number_last + number;
                    Connection conn5 = null;
                    PreparedStatement pstmt5 = null;
                    try {
                        String sql5 = "update user_adult set user_jfnumber_all='" + (ffnumber2) + "',user_jfnumber_last='" + (ffnumber3) + "' where phone_number='" + phonenumber + "'";
                        conn5 = JDBCClean.getConnection();
                        pstmt5 = conn5.prepareStatement(sql5);
                        int rows5 = pstmt5.executeUpdate();
                        System.out.println("成人表格本人分数更改行数" + rows5);
                        if (rows5 > 0) {
                            resp.setCharacterEncoding("utf-8");
                            resp.setContentType("application/json");
                            resp.getWriter().write("true");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        JDBCClean.closeRes(conn5, pstmt5, null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClean.closeRes(conn, pstmt, rs);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
