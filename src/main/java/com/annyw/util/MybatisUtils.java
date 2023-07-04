package com.annyw.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    
    //Create sqlSessionFactory
    public static SqlSessionFactory createFactory()
        throws IOException {
        if (sqlSessionFactory == null) {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        }
        return sqlSessionFactory;
    }
    
    //Create sqlSession
    public static SqlSession getSqlSession()
        throws IOException {
        return createFactory().openSession();
    }
    
}

