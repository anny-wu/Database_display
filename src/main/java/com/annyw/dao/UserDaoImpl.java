package com.annyw.dao;

import com.annyw.pojo.User;
import com.annyw.util.DBUtil;
import com.annyw.util.Unboxed;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl{
    //Display student by page
    public static List<User> queryUserByPage(String table_name, int currentPage, int pageSize){
        String sql = "select * from " + table_name +  " limit ?,?";
        List<User> users = new ArrayList<>();
        Object[] params = {(currentPage-1)*pageSize,pageSize};
        ResultSet rs = DBUtil.executeQuery(sql, params);
        
       try {
            while (rs.next()) {
                //Get Metadata
                ResultSetMetaData rsmd = (ResultSetMetaData)rs.getMetaData();
                // Get column name and column class
                int numCol = rsmd.getColumnCount();
                String[] columns = new String[numCol];
                Class<?>[] types = new Class[numCol];
                for (int i = 0; i < numCol; i++) {
                    columns[i] = rsmd.getColumnName(i + 1);
                    types[i] = Class.forName(rsmd.getColumnClassName(i + 1));
                }
                // Create Instance
                Class<?> cls = Class.forName("com.annyw.pojo.User");
                Object obj = cls.getDeclaredConstructor().newInstance();
                Field[] fields = cls.getDeclaredFields();
                for (int i = 0; i < numCol; i++) {
                    Field f = fields[i];
                    f.setAccessible(true);
                    Object value = rs.getObject(columns[i]);
                    
                    //unboxed column data if necessary
                    if (Unboxed.isBoxed(types[i])) {
                        types[i] = Unboxed.toPrimitives(types[i]);
                    }
                    
                    //set instance value with column data
                    Method setm = cls.getMethod("set" + columns[i], types[i]);
                    setm.invoke(obj, new Object[] {value});
                }
                users.add((User)obj);
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.con);
        }
        return users;
    }
}

