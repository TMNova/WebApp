package ru.lanit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

public class ContextListener implements ServletContextListener, HttpSessionListener {
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("init context");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("destroy context");
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Session is created");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Session is destroyed");
	}
}