package com.backend_main.Model;

import java.time.LocalDate;

public class Bill {
	/*
	 Billno INT(10) Primary key,
	Billdate Date,
	Customername Varchar(30),
	Billmarkedamount INT(10),
	Billpaidamount INT(10)
	 */
	private int billno;
	private LocalDate billdate;
	private String  Customername;
	private int billmarkedamount;
	private int billpaidamount;
	public int getBillno() {
		return billno;
	}
	public void setBillno(int billno) {
		this.billno = billno;
	}
	public LocalDate getBilldate() {
		return billdate;
	}
	public void setBilldate(LocalDate billdate) {
		this.billdate = billdate;
	}
	public String getCustomername() {
		return Customername;
	}
	public void setCustomername(String customername) {
		Customername = customername;
	}
	public int getBillmarkedamount() {
		return billmarkedamount;
	}
	public void setBillmarkedamount(int billmarkedamount) {
		this.billmarkedamount = billmarkedamount;
	}
	public int getBillpaidamount() {
		return billpaidamount;
	}
	public void setBillpaidamount(int billpaidamount) {
		this.billpaidamount = billpaidamount;
	}

}
