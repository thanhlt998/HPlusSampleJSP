package com.test.beans;

public class Order {
	private String orderDate;
	private String orderId;
	private String productImgPath;
	private String productName;
	private String username;
	
	
	
	public Order(String orderDate, String productImgPath, String productName, String username) {
		this.orderDate = orderDate;
		this.productImgPath = productImgPath;
		this.productName = productName;
		this.username = username;
	}
	
	public Order() {
		
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductImgPath() {
		return productImgPath;
	}
	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
