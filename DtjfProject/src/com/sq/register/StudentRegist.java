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

public class StudentRegist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        System.out.println("姓名："+username);
        String  phonenumber = req.getParameter("phone_number");
        System.out.println("电话号："+phonenumber);
        String schoolxb = req.getParameter("idx");
        System.out.println("学校："+schoolxb);
        String classxb = "1";
        System.out.println("年级："+classxb);
        String age = req.getParameter("age");
        System.out.println("学生："+age);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();// 获取当前时间
        String dd = df.format(date);
        System.out.println("现在时间：" + df.format(date)); // 输出已经格式化的现在时间（24小时制）

        String[] school = new String[]{"请选择学校","崂山三中",
                "崂山七中",
                "育才学校",
                "实验初中",
                "麦岛中学",
                "崂山四中",
                "崂山五中",
                "崂山六中",
                "崂山八中",
                "崂山十中",
                "崂山十一中",
                "特教学校",
                "实验小学",
                "实验小学（中韩校区）",
                "实验二小",
                "松岭路小学",
                "实验三小",
                "麦岛小学",
                "辽阳东路小学",
                "合肥路小学",
                "浮山小学",
                "石老人小学",
                "朱家洼小学",
                "山东头小学",
                "东韩小学",
                "中韩小学",
                "西韩小学",
                "枯桃小学",
                "张村河小学",
                "登瀛小学",
                "沙子口小学",
                "姜哥庄小学",
                "南宅小学",
                "汉河小学",
                "青山小学",
                "晓望小学",
                "崂发小学",
                "宁真小学",
                "东泰小学",
                "惠特小学",
                "凤凰台小学",
                "华楼小学",
                "林蔚小学",
                "白珊学校",
                "金家岭学校",
                "新世纪学校",
                "区实验幼儿园（蓝岸园）",
                "区实验幼儿园（北村园）",
                "区实验幼儿园（新苑园）",
                "区实验幼儿园（左岸园）",
                "第二实验幼儿园",
                "区金钥匙幼儿园（一园）",
                "区金钥匙幼儿园（二园）",
                "橄榄城幼儿园",
                "中韩街道中心幼儿园",
                "61419部队幼儿园",
                "警备区红星幼儿园",
                "海洋大学后勤幼儿园",
                "金岭幼儿园",
                "午山幼儿园",
                "王家村幼儿园",
                "石老人幼儿园",
                "朱家洼幼儿园",
                "麦岛幼儿园",
                "新爱弥儿幼儿园",
                "智慧熊幼儿园",
                "智慧熊山水幼儿园",
                "禾乐幼儿园",
                "海怡名都幼儿园",
                "博雅幼儿园",
                "海山幼儿园",
                "爱之初幼儿园",
                "亲亲宝贝幼儿园",
                "保利和乐",
                "韵博幼儿园",
                "乐学岛幼儿园",
                "东城儿童之家",
                "爱莉斯小哈佛",
                "北大公学天玺",
                "蓝芽幼儿园",
                "红黄蓝中心幼儿园",
                "海蓓幼儿园",
                "牟家幼儿园",
                "郑张幼儿园",
                "新世纪幼儿园",
                "西韩幼儿园",
                "孙家幼儿园",
                "童心第一幼儿园",
                "佳佳幼儿园",
                "竹韵山色幼儿园",
                "小世界幼儿园",
                "大拇指幼儿园",
                "艺海宝贝幼儿园",
                "张家幼儿园",
                "枯桃幼儿园",
                "智慧熊含章幼儿园",
                "中韩幼儿园",
                "刘家幼儿园",
                "小童星幼儿园",
                "洋洋幼儿园",
                "张村幼儿园",
                "爱心幼儿园",
                "沙子口中心幼儿园",
                "小河东幼儿园",
                "南窑幼儿园",
                "西麦窑幼儿园",
                "小彩虹幼儿园",
                "岭西幼儿园",
                "段家埠幼儿园",
                "南崂幼儿园",
                "一休幼儿园",
                "我真棒幼儿园",
                "一级棒幼儿园",
                "扬帆幼儿园",
                "于哥庄幼儿园",
                "南宅幼儿园",
                "彭家庄幼儿园",
                "大风车幼儿园",
                "松山后幼儿园",
                "北龙口幼儿园",
                "南龙口幼儿园",
                "九水幼儿园",
                "姜哥庄幼儿园",
                "银帆幼儿园",
                "星晨东方幼儿园",
                "白珊幼儿园",
                "西华宝贝幼儿园",
                "青山幼儿园",
                "黄山幼儿园",
                "曲家庄幼儿园",
                "晓望幼儿园",
                "港东幼儿园",
                "港西幼儿园",
                "王哥庄中心幼儿园",
                "会场幼儿园",
                "宁真幼儿园",
                "浦里幼儿园",
                "江家土寨幼儿园",
                "东台幼儿园",
                "崂山区七彩贝幼儿园",
                "青岛市崂山区聪慧幼儿园",
                "青岛市崂山区金色阳光幼儿园",
                "峪夼幼儿园",
                "崂山区芝麻开门幼儿园",
                "青岛市崂山区北宅街道洪园幼儿园",
                "青岛市崂山区栋梁幼儿园",
                "崂山区北宅街道中心幼儿园",
                "青岛市崂山区北宅街道北宅幼儿园",
                "崂山区北宅街道毕家幼儿园",
                "乌衣巷幼儿园",
                "北宅街道沟崖幼儿园",
                "五龙涧幼儿园",
                "书院幼儿园",
                "中国海洋大学(崂山校区)",
                "青岛科技大学(崂山校区)",
                "山东头小学",
                "青岛高新职业学校",
                "崂山实验幼儿园北村园区"};
        String[] sclass = new String[]{"2019级", "2020级"};
        Integer idx = new Integer(schoolxb);
        String student_school = school[idx];
        Integer idx2 = new Integer(classxb);
        String student_class = sclass[idx2];

        if (student_school == "无") {
            student_school = null;
        }

        Connection conn1 = null;
        PreparedStatement pstmt1 =null;
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
                String sql = "insert into user_student (username,phone_number,user_school,user_class,user_age,time) values(?,?,?,?,?,?)";
                Connection conn = null;
                PreparedStatement pstmt = null;
                try {
                    conn = JDBCClean.getConnection();
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, username);
                    pstmt.setString(2, phonenumber);
                    pstmt.setString(3, student_school);
                    pstmt.setString(4, student_class);
                    pstmt.setString(5, age);
                    pstmt.setString(6,dd);
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
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCClean.closeRes(conn1,pstmt1,rs);
        }
        }
}
