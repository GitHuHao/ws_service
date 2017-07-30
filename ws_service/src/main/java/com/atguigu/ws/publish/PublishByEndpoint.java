package com.atguigu.ws.publish;

import java.util.List;
import javax.xml.ws.Endpoint;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.message.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.atguigu.ws.interceptor.CridentCheckInterceptor;
import com.atguigu.ws.service.BookService;

public class PublishByEndpoint {
	
	// 服务发布需要阻塞监听,因此不得使用@Test测试类
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath*:applicationContext4HandlyPublish.xml");
		
		String address = "http://127.0.0.1:8080/ws_service/BookService_WS";
		
		BookService bookService =(BookService) ioc.getBean(BookService.class);
		// 1.服务端在服务发布后才能往生效的终端装配拦截器,客户端则需要在调用前就装配拦截器
		Endpoint endpoint = Endpoint.publish(address,bookService);
		
		// 将终端强转为实现类可获取系统服务端系统拦截器容器(出/入拦截器)
		List<Interceptor<? extends Message>> inInterceptors = ((EndpointImpl)endpoint).getInInterceptors();
		List<Interceptor<? extends Message>> outInterceptors = ((EndpointImpl)endpoint).getOutInterceptors();
		
		// 往系统拦截器容器装配 系统日志拦截器
		inInterceptors.add(new LoggingInInterceptor());
		outInterceptors.add(new LoggingOutInterceptor());
		
		// 服务端在入口添加身份校验拦截器,客户端在出口添加追加身份认证信息的拦截器
		inInterceptors.add(new CridentCheckInterceptor());
		
		System.out.println(address+"?wsdl 发布成功");
		
	}
	
}
