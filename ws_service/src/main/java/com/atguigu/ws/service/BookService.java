package com.atguigu.ws.service;

import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import com.atguigu.ws.domain.Book;

@WebService
public interface BookService {

	boolean save(Book book);
	boolean delete(int id);
	boolean update(Book book);
	Book getById(int id);
	List<Book> getAll();
	Map<Integer,Book> getMap();
}
