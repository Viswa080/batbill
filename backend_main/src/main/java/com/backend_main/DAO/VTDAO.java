package com.backend_main.DAO;

import java.util.List;

import com.backend_main.Entity.BillEntity;
import com.backend_main.Entity.BillingEntity;
import com.backend_main.Entity.loginentity;
import com.backend_main.Model.Billing;
import com.backend_main.Model.login;

public abstract interface VTDAO {

	public abstract loginentity login(login cred) throws Exception;

	public abstract BillEntity Billing(String name, String discount, List<Billing> prods) throws Exception;

	public abstract Integer LoadProducts(List<Billing> prods) throws Exception;

	public abstract Integer Makenull(Integer prodid) throws Exception;

	public abstract List<Billing> Getbill(Integer billid) throws Exception;

	public abstract Billing GetProduct(Integer Productid) throws Exception;

	public abstract List<Billing> ProducttableEntitytoModel(List<BillingEntity> be) throws Exception;

	public abstract Billing ProducttableEntitytosingleModel(BillingEntity prod) throws Exception;

	public abstract List<Billing> GetExcelProducts(String date, String month, String year) throws Exception;

	public abstract Integer UpdateProduct(Billing prod) throws Exception;
}
