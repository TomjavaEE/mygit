package com.zmkj.springmvcday3.test;

import com.zmkj.springmvcday3.entity.User;
import com.zmkj.springmvcday3.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test {
    public static void main(String[] args) {
//        Thread[] threads=new Thread[4];
//        for (int i=0;i<threads.length;i++){
//            threads[i]=new MyThread();
//        }
//
//        for(int i=0;i<threads.length;i++){
//            threads[i].start();
//        }
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userService=(UserServiceImpl) context.getBean("userService");
        User user=new User();
        user.setUserCode("adminhg");
        user.setUserName("小明");
        user.setUserPassword("0000000");
        user.setGender(1);
        user.setBirthday( java.sql.Date.valueOf("2002-12-04"));
        user.setPhone("18238821300");
        user.setAddress("juihui");
        user.setUserRole(1);
        user.setCreatedBy(1);
        user.setCreationDate(java.sql.Date.valueOf("2002-12-04"));
        user.setModifyBy(1);
        boolean add = userService.add(user);

    }
}
