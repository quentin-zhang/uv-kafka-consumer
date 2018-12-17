package com.uv.services;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    //创建SqlSessionFactory对象
    private static SqlSessionFactory factory;

    /**
     * 获取SqlSession对象
     */
    public static SqlSession getSqlSession(){
        return factory.openSession();
    }

    static{
        try {
            //获取配置文件资源
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //获取SqlSessionFactory对象
            factory=new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取BatchSqlSession对象
     */
    public static SqlSession getBatchSqlSession(){
        return factory.openSession(ExecutorType.BATCH);
    }

    public static SqlSession getSimpleSqlSession(){
        return factory.openSession(ExecutorType.SIMPLE);
    }

    /**
     * 关闭SqlSession对象
     */
    public static void closeSqlSession(SqlSession session){
        if(null!=session){
            //关闭Sqlsession对象
            session.close();
        }
        session=null;
    }

}
