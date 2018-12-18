package com.uv.services;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ForeverService {
    private final static Logger logger = LoggerFactory.getLogger(ForeverService.class);
    private static List<String>  sqlList = new ArrayList<>();
    //直接插入
    public static void insertDirect(String sql) {
        if(sqlList.size() < 100)
        {
            sqlList.add(sql);
        }
        else
        {
            // 获取SqlSession对象
            SqlSession session = MyBatisUtil.getBatchSqlSession();
            try {
                for (String uc : sqlList) {
                    session.insert("insertDirect", sql);
                }
                session.commit();
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
            // 关闭SqlSession对象
            MyBatisUtil.closeSqlSession(session);
            sqlList.clear();
        }
    }
}
