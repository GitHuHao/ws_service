package com.atguigu.ws.publish;

import javax.xml.ws.Endpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.atguigu.ws.service.BookService;

public class PublishHandly {
	
	// 服务发布需要阻塞监听,因此不得使用@Test测试类
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		
		String address = "http://127.0.0.1:8080/ws_service/ws";
		
		BookService bookService =(BookService) ioc.getBean(BookService.class);
		// 1.服务端在服务发布后才能往生效的终端装配拦截器,客户端则需要在调用前就装配拦截器
		Endpoint endpoint = Endpoint.publish(address,bookService);
		
		System.out.println("http://127.0.0.1:8080/ws_service/ws?wsdl 发布成功");
		
	}
	
}
