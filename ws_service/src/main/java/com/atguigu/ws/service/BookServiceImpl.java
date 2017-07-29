package com.atguigu.ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ws.domain.Book;
import com.atguigu.ws.mapper.BookMapper;

@WebService
@Service("bookService")@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper bookMapper;
	
	@WebMethod
	public boolean save(@WebParam(partName="book") Book book) {
		try{
			bookMapper.save(book);
			System.out.println("ws-server: save()");
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@WebMethod
	public boolean delete(int id) {
		try{
			bookMapper.delete(id);
			System.out.println("ws-server: delete()");
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@WebMethod
	public boolean update(@WebParam Book book) {
		try{
			bookMapper.update(book);
			System.out.println("ws-server: update()");
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@WebMethod
	public Book getById(int id) {
		System.out.println("ws-server: getById()");
		return bookMapper.getById(id);
	}

	@WebMethod
	public List<Book> getAll() {
		return bookMapper.getAll();
	}

	@WebMethod
	public Map<Integer, Book> getMap() {
		Map<Integer,Book> map = new HashMap<>();
		List<Book> books = bookMapper.getAll();
		for(Book book:books){
			map.put(book.getId(), book);
		}
		System.out.println("ws-server: getMap()");
		return map;
	}
//	@WebMethod(exclude=true) //如果使用setter注入,必须使用前面的注解,即,将次方法排除在发布之外
//	public void setBookMapper(BookMapper bookMapper) {
//		this.bookMapper = bookMapper;
//	}
}
