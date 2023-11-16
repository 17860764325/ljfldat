package com.sq.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BaseDAOClean {
    /**
     * 获取全部数据
     * @param c1
     * @return
     */
    public static ArrayList getList(Class c1) {
        ArrayList ar = new ArrayList();
        Connection conn = JDBCClean.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //获取类名
        String sql = "Select * from " + c1.getSimpleName();
        System.out.println(c1.getSimpleName());
        Field[] fi = c1.getDeclaredFields();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object ob = c1.newInstance();//实例化对象
                for (Field ff : fi) {
                    ff.setAccessible(true);
                    ff.set(ob, rs.getObject(ff.getName()));
                }
                ar.add(ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClean.closeRes(conn, ps, rs);
        }
        return ar;
    }

    /**
     * 获取ID主键的一条数据
     *
     * @param c1
     * @param id
     * @return
     */

    public static Object getByID(Class c1, int id) {
        Object ob = null;
        Connection conn = JDBCClean.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Field[] fi = c1.getDeclaredFields();
        String sql = "Select * from" + c1.getSimpleName() + "where" + fi[0].getName() + "=" + id;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ob = c1.newInstance();
                for (Field ff : fi) {
                    ff.setAccessible(true);
                    ff.set(ob, rs.getObject(ff.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClean.closeRes(conn, ps, rs);
        }
        return ob;
    }

    /**
     * 获取where后面的的数据
     *
     * @param c1
     * @param wheresql
     * @return
     */

    public static ArrayList getList(Class c1, String wheresql) {
        ArrayList ar = new ArrayList();
        Connection conn = JDBCClean.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select * from " + c1.getSimpleName() + "  where  " + wheresql;
        Field[] fi = c1.getDeclaredFields();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object ob = c1.newInstance();
                for (Field ff : fi) {
                    ff.setAccessible(true);
                    ff.set(ob, rs.getObject(ff.getName()));
                }
                ar.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClean.closeRes(conn, ps, rs);
        }
        return ar;
    }

    /**
     * 模糊查询方法
     * @param c
     * @param name
     * @return
     */
    public static ArrayList getListLike(Class c, String name){
        ArrayList ar =new ArrayList();
        Connection conn =JDBCClean.getConnection();
        PreparedStatement ps =null;
        ResultSet rs =null;
        Field[] fi = c.getDeclaredFields();
        String name1="";
        for (Field ff:fi){
            ff.setAccessible(true);
            System.out.println(ff.getName().toString());
            if (ff.getName().toString().contains("name")){
                name1 =ff.getName();
            }
        }
        String sql =" select * from "+c.getSimpleName()+" where "+name1+" like "+" '%"+name+"%' ";
        System.out.println(sql);
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Object o = c.newInstance();
                for (Field ff :fi){
                    ff.setAccessible(true);
                    ff.set(o,rs.getObject(ff.getName()));
                }
                ar.add(o);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCClean.closeRes(conn,ps,rs);
        }
        return  ar;
    }

    /**
     * 插入方法
     *
     * @param ob
     * @return
     */

    public static boolean Insert(Object ob) {
        boolean f = false;
        Connection conn = JDBCClean.getConnection();
        PreparedStatement ps = null;
        Class c1 = ob.getClass();
        Field[] fi = c1.getDeclaredFields();
        StringBuffer sql = new StringBuffer();
        StringBuffer sql1 = new StringBuffer();
        sql.append("insert into ").append(c1.getSimpleName()).append("(");
        for (int i = 0; i < fi.length; i++) {
            fi[i].setAccessible(true);
            sql.append(fi[i].getName());
            sql1.append("?");
            if (i != fi.length - 1) {
                sql.append(",");
                sql1.append(",");
            }
        }
        sql.append(")").append("values(").append(sql1).append(");");
        try {
            System.out.println(sql.toString());
            ps = conn.prepareStatement(sql.toString());
            for (int i = 1; i <= fi.length; i++) {
                fi[i-1].setAccessible(true);
                ps.setObject(i, fi[i-1].get(ob));
            }
            int a = ps.executeUpdate();
            if (a > 0) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClean.closeRes(conn, ps);
        }
        return f;
    }

    /**
     * 更新
     *
     * @param ob
     * @return
     */

    public static boolean update(Object ob) {
        boolean b = false;
        Connection conn = JDBCClean.getConnection();
        PreparedStatement ps = null;
        Class c1 = ob.getClass();
        Field[] fi = c1.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        sb.append("update ").append(c1.getSimpleName()).append(" set ");
        for (int i = 0; i < fi.length; i++) {
            sb.append(fi[i].getName());
            sb.append(" = ?");
            if (i != fi.length - 1) {
                sb.append(" , ");
            }
        }
        sb.append(" where ");
        sb.append(fi[0].getName()).append(" =?");
        try {
            System.out.println(sb.toString());
            ps = conn.prepareStatement(sb.toString());
            for (int i = 1; i <= fi.length; i++) {
                ps.setObject(i, fi[i-1].get(ob));
            }
            fi[0].setAccessible(true);
            ps.setObject(fi.length+1, fi[0].get(ob));
            int a = ps.executeUpdate();
            if (a > 0) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 删除
     *
     * @param c1
     * @param id
     * @return
     */

    public static boolean delete(Class c1, int id) {
        boolean b = false;
        Connection conn = JDBCClean.getConnection();
        PreparedStatement ps = null;
        Field[] fi = c1.getDeclaredFields();
        String sql = "Delete from " + c1.getSimpleName() + " Where  " + fi[0].getName() + "  =? ";
        try {

            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            int a = ps.executeUpdate();
            if (a > 0) {
                b = true;
            }
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClean.closeRes(conn, ps);
        }
        return b;
    }

    public static boolean delete(Class c1, String WhereSql) {
        boolean b = false;
        Connection conn = JDBCClean.getConnection();
        PreparedStatement ps = null;
        Field[] fi = c1.getDeclaredFields();
        String sql = "Delete from " + c1.getSimpleName() + "  where  " + WhereSql;
        System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            int a = ps.executeUpdate();
            if (a > 0) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClean.closeRes(conn, ps);
        }
        return b;
    }

}
