package com.annyw.util;

import java.sql.*;

public class DBUtil {
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/example?useSSl=true&amp;sueUnicode=true&amp;" +
        "characterEncoding=UTF-8&amp;serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    
    public static Connection con = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;
    
    //Get Connection
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
    
    //Prepare Statement
    public static PreparedStatement createPstmt(String sql,Object[] params) throws ClassNotFoundException, SQLException {
        pstmt = getConnection().prepareStatement(sql);
        if(params != null) {
            for(int i = 0; i < params.length; i++) {
                pstmt.setObject(i+1, params[i]);
            }
        }
        return pstmt;
    }
    
    //Close resultset, statement, and connection
    public static void closeAll(ResultSet rs, Statement stmt, Connection con) {
        try {
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            if(con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getCount(String table_name) {
        int count = -1;
        try {
            String sql = "select count(*) from " + table_name;
            pstmt = createPstmt(sql, null);
            rs = pstmt.executeQuery();
            if(rs.next())
                count = rs.getInt(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, con);
        }
        return count;
    }
    public static ResultSet executeQuery(String sql, Object[] params) {
        try {
            pstmt = createPstmt(sql,params);
            return pstmt.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

