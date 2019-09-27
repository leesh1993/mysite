package kr.co.itcen.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

public class ContextLoadListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletetContextEvent)  { 
    	
    	String contextConfigLocation = servletetContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    	
    	System.out.println("MySite2 Application Starts with " + contextConfigLocation);
   }


    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("MySite2 Application Starts....");
    }



	
}
