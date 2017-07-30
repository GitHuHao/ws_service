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
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext4HandlyPublish.xml");
		
		BookService bookService = (BookService)ioc.getBean("bookService");
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		String address ="http://pc201608140721:8080/ws_service/BookService_WS" ;
		factory.setAddress(address);
		factory.setServiceBean(bookService);
		
		Server server = factory.create();
		server.start();
		
		logger.info(address+"?wsdl  发布成功!");
	}
	
}
