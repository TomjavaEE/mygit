package com.zmkj.springmvcday3.test;

import com.zmkj.springmvcday3.dao.BaseDao;

import java.sql.Connection;

public class MyThread extends  Thread {
    @Override
    public void run() {
        Connection connection = BaseDao.getConnection();
        Connection connection2 = BaseDao.getConnection();
        Connection connection3 = BaseDao.getConnection();
        Connection connection4 = BaseDao.getConnection();
    }
}
