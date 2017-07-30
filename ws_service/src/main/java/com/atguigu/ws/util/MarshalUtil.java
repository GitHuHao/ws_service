package com.atguigu.ws.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.io.XMLResult;
/*
 * 自动主键soap消息请求报文
 */
public class MarshalUtil {
	
	/*
	 *  传入xml 片段转换为entity (注:Entity上必须注明为@XmlRootElement)
	 */
	public static <clazz> clazz getEntity(String xmlString,Class clazz) throws JAXBException {
		JAXBContext jaxBContext = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jaxBContext.createUnmarshaller();
		return (clazz) unmarshaller.unmarshal(new StringReader(xmlString));
	}
	/*
	 * 传入entity 打印输出为xml片段
	 */
	public static <T> void printXml(T t, boolean format) throws JAXBException {
		JAXBContext jaxBContext = JAXBContext.newInstance(t.getClass());
		Marshaller marshaller = jaxBContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");

		marshaller.marshal(t, System.out);
	}
	/*
	 * 传入entity对象,返回xml片段
	 */
	public static <T> XMLResult getDom(T t, boolean format) throws JAXBException {
		JAXBContext jaxBContext = JAXBContext.newInstance(t.getClass());
		Marshaller marshaller = jaxBContext.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		
		XMLResult result = new XMLResult();
		marshaller.marshal(t, result);
		return result;
	}
	
//	public static void main(String[] args) {
//		try {
//			XMLResult result = getDom(new Book(1,"tom",12.3), true);
//			System.out.println(result);
//			
//			String xmlString = "<_book_><id>1</id><price>12.3</price><title>tom</title></_book_>";
//			
//			Book entity = getEntity(xmlString,Book.class);
//			System.out.println(entity);
//		} catch (Exception e) {
//		}
//	}

}
