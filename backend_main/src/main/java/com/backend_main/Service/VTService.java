package com.backend_main.Service;

import java.util.List;

import com.backend_main.Entity.BillEntity;
import com.backend_main.Entity.BillingEntity;
import com.backend_main.Model.Billing;
import com.backend_main.Model.login;

import jakarta.servlet.http.HttpServletResponse;

public interface VTService {
	public abstract login logintoDAO(login cred) throws Exception;

	public abstract BillEntity BillingtoDAO(String name, String discount, List<Billing> prods) throws Exception;

	public abstract Integer LoadproductstoDAO(List<Billing> prods) throws Exception;

	public abstract String Makenull(Integer prodid) throws Exception;

	public abstract List<Billing> Getbill(Integer billid) throws Exception;

	public abstract Billing GetProduct(Integer Productid) throws Exception;

	public abstract List<Billing> GetProductexcel(String date, String month, String year) throws Exception;

	public abstract void generateExcel(HttpServletResponse responce, String date, String month, String year)
			throws Exception;

	public String microservice() throws Exception;

	public abstract List<BillingEntity> getAll();

	public abstract Integer UpdateProduct(Billing prod) throws Exception;

}
