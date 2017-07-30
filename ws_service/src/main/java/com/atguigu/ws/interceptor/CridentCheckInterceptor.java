package com.atguigu.ws.interceptor;

import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class CridentCheckInterceptor extends
		AbstractPhaseInterceptor<SoapMessage> {

	protected static Logger logger = Logger
			.getLogger(CridentCheckInterceptor.class);

	public CridentCheckInterceptor() {
		super(Phase.PRE_PROTOCOL); // 默认设置预协议化时,创建拦截器
		logger.info("ws-service CridentCheckInterceptor created.");
	}

	/*
	 * <Envelop> <head> <atguigu> <username>tom</username>
	 * <password>cat</password> </atguigu> </head> <soap:body> <ns2:getMap
	 * xmlns:ns2="http://127.0.0.1:8080/getMap"/> </soap:body> </Envelop>
	 */
	public void handleMessage(SoapMessage message) throws Fault {
		try {
			// 1.提取指定请求头,并获取需要的元素信息
			Header header = message.getHeader(new QName("crident"));
			Element cridentElem = (Element) header.getObject();

			// 2. 解析Element节点
			String username = cridentElem.getElementsByTagName("username").item(0).getTextContent();
			String password = cridentElem.getElementsByTagName("password").item(0).getTextContent();

			// 3. 身份校验
			if ("tom".equals(username) && "cat".equals(password)) {
				logger.info("ws-service CridentCheckInterceptor done.");
			} else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			logger.error("ws-service CridentCheckInterceptor failed.");
			throw new Fault(new RuntimeException("需要正确的用户名和密码"));
		}
	}

}
