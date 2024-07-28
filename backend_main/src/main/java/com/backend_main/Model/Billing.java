package com.backend_main.Model;

import java.time.LocalDate;

public class Billing {

	private int productid;
	private String productname;
	private String producttype;
	private String productcategory;
	private String color;
	private int productmarkedcost;
	private int productsoldcost;
	private LocalDate productcomedate;
	private LocalDate productsolddate;
	private String customername;
	private int billNo;

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}

	public int getProductmarkedcost() {
		return productmarkedcost;
	}

	public void setProductmarkedcost(int productmarkedcost) {
		this.productmarkedcost = productmarkedcost;
	}

	public int getProductsoldcost() {
		return productsoldcost;
	}

	public void setProductsoldcost(int productsoldcost) {
		this.productsoldcost = productsoldcost;
	}

	public LocalDate getProductcomedate() {
		return productcomedate;
	}

	public void setProductcomedate(LocalDate productcomedate) {
		this.productcomedate = productcomedate;
	}

	public LocalDate getProductsolddate() {
		return productsolddate;
	}

	public void setProductsolddate(LocalDate productsolddate) {
		this.productsolddate = productsolddate;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customerphno) {
		this.customername = customerphno;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
