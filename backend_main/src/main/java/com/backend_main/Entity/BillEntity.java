package com.backend_main.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "billtable")
public class BillEntity {
	@Id
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Billno;
	private LocalDate Billdate;
	private String Customername;
	private int Billmarkedamount;
	private int Billpaidamount;

	public int getBillno() {
		return Billno;
	}

	public void setBillno(int billno) {
		this.Billno = billno;
	}

	public LocalDate getBilldate() {
		return Billdate;
	}

	public void setBilldate(LocalDate billdate) {
		this.Billdate = billdate;
	}

	public String getCustomername() {
		return Customername;
	}

	public void setCustomername(String customername) {
		Customername = customername;
	}

	public int getBillmarkedamount() {
		return Billmarkedamount;
	}

	public void setBillmarkedamount(int billmarkedamount) {
		this.Billmarkedamount = billmarkedamount;
	}

	public int getBillpaidamount() {
		return Billpaidamount;
	}

	public void setBillpaidamount(int billpaidamount) {
		this.Billpaidamount = billpaidamount;
	}

}
