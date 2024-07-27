package com.backend_main.Entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="producttable")
public class BillingEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Productid;
	private String Productname;
	private String Producttype;
	private String Productcategory;
	private String color;
	private int Productmarkedcost;
	private int Productsoldcost;
	private LocalDate Productcomedate;
	private LocalDate Productsolddate;
	private String Customername;
	private int Billno;
	
	//getter and setter methods
	public int getProductid() {
		return Productid;
	}
	public void setProductid(int productid) {
		Productid = productid;
	}
	public String getProductname() {
		return Productname;
	}
	public void setProductname(String productname) {
		Productname = productname;
	}
	public String getProducttype() {
		return Producttype;
	}
	public void setProducttype(String producttype) {
		Producttype = producttype;
	}
	public String getProductcategory() {
		return Productcategory;
	}
	public void setProductcategory(String productcategory) {
		Productcategory = productcategory;
	}
	public int getProductmarkedcost() {
		return Productmarkedcost;
	}
	public void setProductmarkedcost(int productmarkedcost) {
		Productmarkedcost = productmarkedcost;
	}
	public int getProductsoldcost() {
		return Productsoldcost;
	}
	public void setProductsoldcost(int productsoldcost) {
		Productsoldcost = productsoldcost;
	}
	public LocalDate getProductcomedate() {
		return Productcomedate;
	}
	public void setProductcomedate(LocalDate productcomedate) {
		Productcomedate = productcomedate;
	}
	public LocalDate getProductsolddate() {
		return Productsolddate;
	}
	public void setProductsolddate(LocalDate productsolddate) {
		Productsolddate = productsolddate;
	}
	public String getCustomername() {
		return Customername;
	}
	public void setCustomername(String customerphno) {
		Customername = customerphno;
	}
	public int getBillno() {
		return Billno;
	}
	public void setBillno(int billno) {
		Billno = billno;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	


}