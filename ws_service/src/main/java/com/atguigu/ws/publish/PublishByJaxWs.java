package com.atguigu.ws.publish;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.ws.service.BookService;

public class PublishByJaxWs {

	protected static Logger logger = Logger.getLogger(PublishByJaxWs.class);
	
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		BookService bookService = (BookService)ioc.getBean("bookService");
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		
		factory.setAddress("http://pc201608140721:8080/ws_service/BookService_WS");
		factory.setServiceBean(bookService);
		
		Server server = factory.create();
		server.start();
		
		logger.info("http://pc201608140721:8080/ws_service/BookService_WS?wsdl  发布成功!");
	}
	
}
