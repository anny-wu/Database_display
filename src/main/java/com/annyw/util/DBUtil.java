package com.annyw.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    
    public static Connection con = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;
    static Properties p = new Properties();
    static{
        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("/jdbc.properties");
        try{
            p.load(is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Get Connection
    public static Connection getConnection()
        throws ClassNotFoundException, SQLException {
        String driver = p.getProperty("driver");
        String url = p.getProperty("url");
        String username = p.getProperty("username");
        String password = p.getProperty("password");
        Class.forName(driver);
        con = DriverManager.getConnection(url, username, password);
        return con;
    }
    
    //Prepare Statement
    public static PreparedStatement createPstmt(String sql, Object[] params)
        throws ClassNotFoundException, SQLException {
        pstmt = getConnection().prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }
    
    //Close resultset, statement, and connection
    public static void closeAll(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getCount(String table_name) {
        int count = -1;
        try {
            String sql = "select count(*) from " + table_name;
            pstmt = createPstmt(sql, null);
            rs = pstmt.executeQuery();
            if (rs.next())
                count = rs.getInt(1);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeAll(rs, pstmt, con);
        }
        return count;
    }
    
    //Execute update with prepared statement
    public static int executeUpdate(String sql, Object[] params) {
        try {
            pstmt = createPstmt(sql, params);
            return pstmt.executeUpdate();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    //Execute query with prepared statement
    public static ResultSet executeQuery(String sql, Object[] params) {
        try {
            pstmt = createPstmt(sql, params);
            return pstmt.executeQuery();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

