<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.net.URL" %><%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2020/11/11
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public static String GetURLstr(String strUrl){
        InputStream  in = null;
        OutputStream out = null;
        String strdata = "";
        try{
            URL url = new URL(strUrl);
            in = url.openStream();
            out = System.out;
            byte[] buffer = new byte[4096];
            int bytes_read;
            while((bytes_read = in.read(buffer)) != -1){
                String reads = new String(buffer,0,bytes_read,"UTF-8");
                strdata = strdata + reads;
            }
            in.close();
            out.close();
            return strdata;
        }catch (Exception e){
            e.printStackTrace();
            return strdata;
        }
    }
%>
<%//解决乱码问题
 request.setCharacterEncoding("UTF-8");
 String str_code = "";
 str_code = request.getParameter("code");
//    out.print(str_code);
    //https://api.weixin.qq.com/sns/jscode2session?appid=&secret=&js_code=' + res.code + '&grant_type=authorization_code
String str_token = "";
str_token = str_token+"https://api.weixin.qq.com/sns/jscode2session";
str_token = str_token+"?appid=wxded9d9be75522832";
str_token = str_token+"&secret=472e856b8c856b2da5940dee13f5d134";
str_token = str_token+"&js_code="+str_code;
str_token = str_token+"&grant_type=authorization_code";

String neirong_token = "";
neirong_token = GetURLstr(str_token);
System.out.println(str_token);
out.print(neirong_token);
%>