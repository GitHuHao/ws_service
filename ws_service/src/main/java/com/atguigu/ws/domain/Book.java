package com.atguigu.ws.domain;

import java.io.Serializable;

public class Book implements Serializable{

	private int id;
	private String title;
	private double price;
	
	public Book(String title, double price) {
		super();
		this.title = title;
		this.price = price;
	}

	public Book() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price + "]";
	}
}
