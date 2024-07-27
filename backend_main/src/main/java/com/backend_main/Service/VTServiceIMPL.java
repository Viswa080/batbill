package com.backend_main.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend_main.DAO.VTDAO;
import com.backend_main.Entity.BillEntity;
import com.backend_main.Entity.BillingEntity;
import com.backend_main.Entity.loginentity;
import com.backend_main.Model.Billing;
import com.backend_main.Model.login;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@Service(value = "VTService")
@Transactional 
public class VTServiceIMPL implements VTService {
	@Autowired
	private VTDAO vtd;

	@Override
	public login logintoDAO(login cred) throws Exception {
		loginentity responcefromDAO = vtd.login(cred);
		System.out.println(cred.getPassword());
		System.out.println(responcefromDAO.getPassword());
		if (cred.getPassword().equals(responcefromDAO.getPassword())) {
			cred.setPassword(" ");
			cred.setName(responcefromDAO.getName());
			cred.setPhoneno(responcefromDAO.getPhoneno());
			System.out.println("executed the true block");
			return cred;
		}
		System.out.println("escaped the true block");
		throw new Exception("SERVICE.INVALID_CREDENTIALS");
	}

	@Override
	public BillEntity BillingtoDAO(String name, String discount, List<Billing> prods) throws Exception {
		System.out.println("Triggered in Service " + name);
		BillEntity responcefromDao = vtd.Billing(name, discount, prods);
		if (responcefromDao == null) {
			throw new Exception("SERVICE.FACING_ISSUE");
		}
		System.out.println("Done in Service " + name);
		
		return responcefromDao;
	}

	@Override
	public Integer LoadproductstoDAO(List<Billing> prods) throws Exception {
		// System.out.println("Triggered in service");
		Integer responcefromDao = vtd.LoadProducts(prods);
		if (responcefromDao == null) {
			throw new Exception("SERVICE.FACING_ISSUE");
		}

		// System.out.println("Done in service");
		return responcefromDao;
	}
	public Integer UpdateProduct(Billing prod) throws Exception {
		Integer responcefromDao = vtd.UpdateProduct(prod);
		if (responcefromDao == null) {
			throw new Exception("SERVICE.FACING_ISSUE");
		}
		// System.out.println("Done in service");
		return responcefromDao;
		
	}

	@Override
	public String Makenull(Integer prodid) throws Exception {
		System.out.println("Triggered in service");
		Integer responcefromDao = vtd.Makenull(prodid);
		if (responcefromDao == null) {
			throw new Exception("SERVICE.FACING_ISSUE");
		}
		System.out.println("Done in service");
		return "Successfully made the changes in DB - ProductID:" + responcefromDao;
	}

	@Override
	public List<Billing> Getbill(Integer billid) throws Exception {
		System.out.println("Triggered in service");
		List<Billing> responcefromDao = vtd.Getbill(billid);
		if (responcefromDao.size() == 0) {
			throw new Exception("SERVICE.NO_DATA_FOUND");
		}
		System.out.println("Done in service");
		return responcefromDao;
	}

	@Override
	public Billing GetProduct(Integer Productid) throws Exception {
		System.out.println("Triggered in service");
		Billing responcefromDao = vtd.GetProduct(Productid);
		if (responcefromDao == null) {
			throw new Exception("SERVICE.FACING_ISSUE");
		}
		System.out.println("Done in service");
		return responcefromDao;
	}

	@Override
	public List<Billing> GetProductexcel(String date,String month, String year) throws Exception {
		System.out.println("Triggered in service");
		List<Billing> responcefromDao = vtd.GetExcelProducts(date,month, year);
		//List<BillingEntity> responcefromDao = jpa.findby
		if (responcefromDao == null) {
			throw new Exception("SERVICE.FACING_ISSUE");
		}
		System.out.println("Done in service");
		return responcefromDao;
		//return vtd.ProducttableEntitytoModel(responcefromDao);
	}

	@Override
	public void generateExcel(HttpServletResponse responce,String date, String month, String year) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Monthly Product Info");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("Product ID");
		row.createCell(1).setCellValue("Product Name");
		row.createCell(2).setCellValue("Type");
		row.createCell(3).setCellValue("Category");
		row.createCell(4).setCellValue("MRP");
		row.createCell(5).setCellValue("SOLD MRP");
		row.createCell(6).setCellValue("Loaded Date");
		row.createCell(7).setCellValue("Sold Date");
		row.createCell(8).setCellValue("Customer Name");
		row.createCell(9).setCellValue("Bill ID");

		int datarowindex = 1;
		System.out.println(month + "-" + year + "in Service class");
		List<Billing> exceldata = GetProductexcel(date,month, year);

		for (Billing sr : exceldata) {
			System.out.print(sr.getProductid());
			HSSFRow datarow = sheet.createRow(datarowindex);
			datarow.createCell(0).setCellValue(sr.getProductid());
			datarow.createCell(1).setCellValue(sr.getProductname());
			datarow.createCell(2).setCellValue(sr.getProducttype());
			datarow.createCell(3).setCellValue(sr.getProductcategory());
			datarow.createCell(4).setCellValue(sr.getProductmarkedcost());
			datarow.createCell(5).setCellValue(sr.getProductsoldcost());
			datarow.createCell(6).setCellValue(sr.getProductcomedate());
			datarow.createCell(7).setCellValue(sr.getProductsolddate());
			datarow.createCell(8).setCellValue(sr.getCustomername());
			datarow.createCell(9).setCellValue(sr.getBillNo());
			datarowindex++;
		}
		ServletOutputStream osobj = responce.getOutputStream();
		workbook.write(osobj);
		workbook.close();
		osobj.close();
	}

	public String microservice() throws Exception { // ProducttableEntitytosingleModel

		return "";
	}
	public List<BillingEntity> getAll(){
		System.out.println("Triggered in Service");
		List<BillingEntity> products = new ArrayList<BillingEntity>();
		//jpa.findAll().forEach(products::add);
		System.out.println("Done in Service");
		return products;
	}
}
