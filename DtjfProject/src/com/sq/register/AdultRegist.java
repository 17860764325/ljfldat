package com.sq.register;

import com.google.gson.Gson;
import com.sq.dto.ResultDto;
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

public class AdultRegist extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("name");
        System.out.println("姓名：" + username);
        String phonenumber = req.getParameter("phone_number");
        System.out.println("电话号：" + phonenumber);
        String qiyexb = req.getParameter("idx");
        System.out.println("企业：" + qiyexb);
        String jiedaoxb = req.getParameter("idx2");
        System.out.println("街道：" + jiedaoxb);
        String age = req.getParameter("age");
        System.out.println("成年人：" + age);

        if ("6".equals(jiedaoxb)){
            jiedaoxb="0";
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();// 获取当前时间
        String dd = df.format(date);
        System.out.println("现在时间：" + df.format(date)); // 输出已经格式化的现在时间（24小时制）

        String[] school = new String[]{"无", "金家岭街道", "中韩街道","沙子口街道",  "王哥庄街道", "北宅街道"};
        String[] sclass = new String[]{"无", "崂山区志愿服务协会","崂山区住房和城乡建设局",
                "崂山区城市管理局",
                "崂山区商务局",
                "崂山区机关物业管理中心",
                "崂山区政府办公室",
                "崂山区电子政务中心",
                "崂山区统计局",
                "崂山区民政局",
                "崂山区工业和信息化局",
                "崂山区发展和改革局",
                "崂山区人力资源和社区保障局",
                "纪委巡查组",
                "崂山区卫生服务中心",
                "崂山区信访局",
                "崂山湾国际生态健康城开发建设办公室",
                "崂山区自然资源局",
                "崂山区农业农村局",
                "崂山区老干部局",
                "崂山区审计局",
                "崂山区工会",
                "崂山区财政局",
                "崂山区红十字会",
                "崂山区卫生健康局",
                "崂山区司法局",
                "崂山区机关工委",
                "崂山区教育和体育局",
                "崂山区机关事务服务中心",
                "崂山区纪委",
                "崂山区政协",
                "崂山区政法委",
                "崂山区委办公室",
                "崂山区委宣传部",
                "崂山委组织部",
                "崂山区人大",
                "崂山区工商业联合会",
                "崂山统战部",
                "崂山区残联",
                "崂山区贸促支会",
                "崂山区安全局",
                "崂山区团委",
                "崂山区应急管理局",
                "崂山区法律援助中心",
                "崂山区城市更新推进中心",
                "崂山区妇联",
                "崂山区地震局",
                "崂山区科创委",
                "崂山区文化新闻出版局",
                "崂山区委巡察工作领导小组办公室",
                "法制文教委",
                "崂山区城乡建设环境资源保护委员会",
                "崂山区财政经济委员会",
                "崂山区预算工作委员会",
                "崂山区人事代表委员会",
                "党风政府监督",
                "崂山区信访室",
                "崂山区案件监督管理办公室",
                "崂山区纪检监察办公室",
                "崂山区广播电视中心",
                "农业银行",
                "崂山区气象局",
                "崂山区老年大学",
                "崂山区老年人体育协会",
                "崂山区税务局",
                "崂山区婚姻登记处",
                "崂山区档案局",
                "崂山区交通局",
                "崂山区消杀办公室",
                "崂山区委组织部",
                "崂山区慈善总会",
                "崂山区文联",
                "崂山区行政审批服务局",
                "崂山区退伍军人事务局",
                "崂山区图书馆",
                "崂山区文化馆",
                "崂山综合行政执法局",
                "崂山区建设工程管理中心",
                "生态环境崂山分局",
                "崂山区城建档案馆",
                "崂山区招投标办公室",
                "崂山区机关物业管理办公室",
                "崂山市车辆管理办",
                "金家岭街道办事处"};
        Integer idx = new Integer(qiyexb);
        String user_qiye = sclass[idx];
        if (user_qiye == "无") {
            user_qiye = null;
        }

        Integer idx2 = new Integer(jiedaoxb);
        String user_jiedao = school[idx2];
        if (user_jiedao == "无") {
            user_jiedao = null
            ;
        }
        Connection conn1 = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {
            conn1 = JDBCClean.getConnection();
            String sql1 = "select * from account_pwd where  phone_number='" + phonenumber + "'";
            pstmt1 = conn1.prepareStatement(sql1);
            rs = pstmt1.executeQuery();
            boolean a = rs.next();
            if (a) {
                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json");
                resp.getWriter().write("true");
            } else {
                String sql = "insert into user_adult (username,phone_number,user_qiye,user_jiedao,user_age,time ) values(?,?,?,?,?,?)";
                Connection conn = null;
                PreparedStatement pstmt = null;
                try {
                    conn = JDBCClean.getConnection();
                    pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, username);
                    pstmt.setString(2, phonenumber);
                    pstmt.setString(3, user_qiye);
                    pstmt.setString(4, user_jiedao);
                    pstmt.setString(5, age);
                    pstmt.setString(6, dd);
                    int rows = pstmt.executeUpdate();
                    System.out.println(rows);

                    resp.setCharacterEncoding("utf-8");
                    resp.setContentType("application/json");
                    resp.getWriter().write("false");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    JDBCClean.closeRes(conn, pstmt, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClean.closeRes(conn1, pstmt1, rs);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
