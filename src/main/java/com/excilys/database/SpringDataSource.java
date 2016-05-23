package com.excilys.database;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class SpringDataSource implements VirtualConnectTool {
    private static ApplicationContext app;
    public static ApplicationContext getContext() {
        if (app == null) {
            app = new ClassPathXmlApplicationContext("Beans.xml");
        }
        return app;
    }

}
