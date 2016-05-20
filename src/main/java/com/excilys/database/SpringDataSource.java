package com.excilys.database;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class SpringDataSource implements VirtualConnectTool {

    public static ApplicationContext getContext() {
        return new ClassPathXmlApplicationContext("Beans.xml");
    }

}
