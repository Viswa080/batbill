package com.backend_main.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.backend_main.Entity.BillEntity;
import com.backend_main.Entity.BillingEntity;
import com.backend_main.Entity.loginentity;
import com.backend_main.Model.Billing;
import com.backend_main.Model.login;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository(value = "VTDAO")
public class VTDAOIMPL implements VTDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public loginentity login(login cred) throws Exception {
		loginentity responce = null;
		System.out.println(cred.getUserid());
		responce = em.find(loginentity.class, cred.getUserid());
		return responce;
	}
	@Override
	public BillEntity Billing(String name,String discount,List<Billing> prods) throws Exception {
		Integer totalbill = 0;
		Integer markedbill=0;
		int billno;
		Integer discountpercent = 0;
		if(discount.equals("HO")) { discountpercent = 20; }else if(discount.equals("IT")){ discountpercent = 15; 
		}else if(discount.equals("PD")){
			discountpercent = 10;
		}
		Query q1 = em.createQuery("Select Max(Billno) from BillingEntity");
		System.out.println("getQuery executed");
		billno= ((int)q1.getSingleResult())+1;
		for (Billing prod : prods) {
			
			
			Query q = em.createQuery("UPDATE BillingEntity p SET p.Productsoldcost=?1,p.Customername=?2,"
					+ "p.Billno=?3,p.Productsolddate=?4 where p.Productid=?5");
			System.out.println("Query executed");
			System.out.println(discountpercent);
			System.out.println(prod.getProductmarkedcost());
			System.out.println(prod.getProductmarkedcost()-((double)(discountpercent*prod.getProductmarkedcost()/100)));
			q.setParameter(1, (int)(prod.getProductmarkedcost()-((double)(discountpercent*prod.getProductmarkedcost()/100))));
			q.setParameter(2, name);
			q.setParameter(3, billno);
			q.setParameter(4, LocalDate.now());
			q.setParameter(5, prod.getProductid());
			q.executeUpdate();
			markedbill+=(int)(prod.getProductmarkedcost());
			totalbill += ((int)(prod.getProductmarkedcost()-((double)(discountpercent*prod.getProductmarkedcost()/100))));
		}
		BillEntity bill = new BillEntity();
		bill.setBillno(billno);
		bill.setCustomername(name);
		bill.setBillmarkedamount(markedbill);
		bill.setBillpaidamount(totalbill);
		return bill;
	}

	@Override
	public Integer LoadProducts(List<Billing> prods) throws Exception {
		// System.out.println("Triggered in DAO");
		Integer responce = 0;

		for (Billing prod : prods) {
			BillingEntity prodentity = null;
			prodentity = new BillingEntity();
			// prodentity.setProductid(prod.getProductid());
			prodentity.setProductname(prod.getProductname());
			prodentity.setProducttype(prod.getProducttype());
			prodentity.setProductcategory(prod.getProductcategory());
			prodentity.setProductmarkedcost(prod.getProductmarkedcost());
			prodentity.setColor(prod.getColor());
			// prodentity.setProductsoldcost(prod.getProductsoldcost());
			// prodentity.setProductsolddate(LocalDate.now());
			prodentity.setProductcomedate(LocalDate.now());
			// prodentity.setCustomerphno(phno);
			// prodentity.setBillno(0);
			System.out.println(prodentity.getProductname() + " " + prodentity.getProducttype() + " "
					+ prodentity.getProductcategory() + " " + prodentity.getProductmarkedcost());
			em.persist(prodentity);

			responce += 1;
		}
		// System.out.println("Done in DAO");
		return responce;
	}

	@Override
	public Integer Makenull(Integer prodid) throws Exception {
		System.out.println("Triggered in DAO");
		Query q = em.createQuery("UPDATE BillingEntity p SET p.Productsoldcost=?1,p.Customername=?2,"
				+ "p.Billno=?3,p.Productsolddate=?4 where p.Productid=?5 ");
		System.out.println("Triggered Query");
		q.setParameter(1, 0);
		q.setParameter(2, null);
		q.setParameter(3, 0);
		q.setParameter(4, null);
		q.setParameter(5, prodid);
		q.executeUpdate();
		System.out.println("Done in DAO");
		return prodid;
	}

	@Override
	public List<Billing> Getbill(Integer billid) throws Exception {
		// System.out.println("Triggered in DAO");
		Query q = em.createQuery("Select a from BillingEntity a where a.Billno=?1");
		// System.out.println("Triggered Query");
		q.setParameter(1, billid);
		List<BillingEntity> responce = null;
		responce = new ArrayList<BillingEntity>();
		responce = q.getResultList();
		if (responce == null) {
			throw new Exception("DAO.NO_DATA_FOUND");
		}
		List<Billing> output = ProducttableEntitytoModel(responce);
		// System.out.println("Done in DAO");
		return output;
	}

	@Override
	public Billing GetProduct(Integer productid) throws Exception {
		try {
			// System.out.println("Triggered in DAO");
			Query q = em.createQuery("Select a from BillingEntity a where a.Productid=:prodid");
			q.setParameter("prodid", productid);
			BillingEntity DBresponce = new BillingEntity();
			DBresponce = (BillingEntity) q.getSingleResult();
			Billing responce = ProducttableEntitytosingleModel(DBresponce);
			// System.out.println("Done in DAO");
			return responce;
		} catch (Exception e) {
			throw new Exception("DAO.NO_DATA_FOUND");
		}
	}
	@Override
	public Integer UpdateProduct(Billing prod) throws Exception {
		try {
			BillingEntity prodentity = new BillingEntity();
			prodentity = new BillingEntity();
			prodentity= em.find(BillingEntity.class, prod.getProductid());
			// prodentity.setProductid(prod.getProductid());
			prodentity.setProductname(prod.getProductname());
			prodentity.setProducttype(prod.getProducttype());
			prodentity.setProductcategory(prod.getProductcategory());
			prodentity.setProductmarkedcost(prod.getProductmarkedcost());
			prodentity.setColor(prod.getColor());
			prodentity.setProductsoldcost(prod.getProductsoldcost());
			prodentity.setProductsolddate(LocalDate.now());
			prodentity.setProductcomedate(LocalDate.now());
			return prod.getProductid();
		} catch (Exception e) {
			throw new Exception("SERVICE.FACING_ISSUE");
		}
	}
	@Override
	public List<Billing> GetExcelProducts(String date,String month, String year) throws Exception 
	{
		try {
			System.out.println("Triggered in DAO");
			System.out.println(date+"-"+month+"-"+year);
			Query q =null;
			if(date.equals("00") && month.equals("00") ) { 
				q = em.createQuery("Select a from BillingEntity a where Year(a.Productsolddate) = :year");
				//System.out.println("Triggered first");
				q.setParameter("year", year);
				}
			else if(date.equals("00") && !month.equals("00")){
				q = em.createQuery("Select a from BillingEntity a where Year(a.Productsolddate) = :year and Month(a.Productsolddate)= :month");
				//System.out.println("Triggered second");
				q.setParameter("year", year);
				q.setParameter("month",month);
				}
			else {
				q = em.createQuery("Select a from BillingEntity a where Year(a.Productsolddate) = :year and Month(a.Productsolddate)= :month and Day(a.Productsolddate)= :day");
				//System.out.println("Triggered third");
				q.setParameter("day", date);
				q.setParameter("year", year);
				q.setParameter("month",month);
			}
			
			//	q.setParameter("datePattern", "2024-01-15");
			List<BillingEntity> DBresponce = new ArrayList<BillingEntity>();
			DBresponce = q.getResultList();
			System.out.println(DBresponce);
			if (DBresponce == null) {
				throw new Exception("DAO.NO_DATA_FOUND");
			}
			List<Billing> responce = ProducttableEntitytoModel(DBresponce);
			System.out.println("Done in DAO");
			return responce;
		} catch (Exception e) {
			throw new Exception("DAO.NO_DATA_FOUND");
		}
	}

	public List<Billing> ProducttableEntitytoModel(List<BillingEntity> be) throws Exception { // ProducttableEntitytoModel
		List<Billing> responce = null;
		responce = new ArrayList<Billing>();
		for (BillingEntity prod : be) {
			Billing prodentity = null;
			prodentity = new Billing();
			// System.out.println(prod.getProductname()); //used to check whether we have
			// the OP
			prodentity.setProductid(prod.getProductid());
			prodentity.setProductname(prod.getProductname());
			prodentity.setProducttype(prod.getProducttype());
			prodentity.setProductcategory(prod.getProductcategory());
			prodentity.setProductmarkedcost(prod.getProductmarkedcost());
			prodentity.setProductsoldcost(prod.getProductsoldcost());
			prodentity.setProductsolddate(prod.getProductsolddate());
			prodentity.setProductcomedate(prod.getProductcomedate());
			prodentity.setCustomername(prod.getCustomername());
			prodentity.setColor(prod.getColor());
			prodentity.setBillNo(prod.getBillno());
			responce.add(prodentity);
			
			
		}
		return responce;
	}

	@Override
	public Billing ProducttableEntitytosingleModel(BillingEntity prod) throws Exception { // ProducttableEntitytosingleModel
		Billing prodentity = null;
		prodentity = new Billing();
		System.out.println("Triggered the conversion method" + prod.getProductname()); // used to check whether we have
																						// the OP
		prodentity.setProductid(prod.getProductid());
		prodentity.setProductname(prod.getProductname());
		prodentity.setProducttype(prod.getProducttype());
		prodentity.setProductcategory(prod.getProductcategory());
		prodentity.setProductmarkedcost(prod.getProductmarkedcost());
		prodentity.setProductsoldcost(prod.getProductsoldcost());
		prodentity.setProductsolddate(prod.getProductsolddate());
		prodentity.setProductcomedate(prod.getProductcomedate());
		prodentity.setCustomername(prod.getCustomername());
		prodentity.setColor(prod.getColor());
		prodentity.setBillNo(prod.getBillno());
		return prodentity;
	}
	
	
}
