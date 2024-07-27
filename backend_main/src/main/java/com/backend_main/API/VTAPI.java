package com.backend_main.API;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.backend_main.Entity.BillEntity;
import com.backend_main.Entity.BillingEntity;
import com.backend_main.Model.Billing;
import com.backend_main.Model.Product;
import com.backend_main.Model.login;
import com.backend_main.Service.VTService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/Bat")
@Validated
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:58678"})
public class VTAPI {
	@Autowired
	private Environment env;
	
	@Autowired
	private VTService vts;
	
	@Autowired
	private RestTemplate temp;
	
	Logger log = LogManager.getLogger(VTAPI.class);
	
	// and /* */ to comments
	@PostMapping(value = "/login")
	public ResponseEntity<login> login(@RequestBody login cred) throws Exception { // to check the login credentials
		try {
			System.out.println("Triggered in API");
			ResponseEntity<login> response = null;
			login responcefromservice = vts.logintoDAO(cred);
			response = new ResponseEntity<login>(responcefromservice, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
	}

	@PostMapping(value = "/uploadbills")
	public ResponseEntity<Integer> billing(@RequestBody String phno, @RequestBody String Billn,
			@RequestBody String Billnoo, @RequestBody List<Billing> prods) throws Exception { // not in use
		try {
			System.out.println("Triggered in API");
			ResponseEntity<Integer> response = null;
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
	}
	// for making a bill and mark products as sold
	@PostMapping(value = "/makebilling/{name}/{discount}")
	public ResponseEntity<Integer> billing(@PathVariable String name, @PathVariable String discount,
			@RequestBody List<Billing> prods) 
			throws Exception {
		//try {
		System.out.println("Triggered in API " + name);
		ResponseEntity<Integer> response = null;
		BillEntity responcefromservice = vts.BillingtoDAO(name, discount, prods);
		BillEntity billid=temp.postForObject("http://BILLMS"+"/Bill/addbill", responcefromservice, BillEntity.class);
		response = new ResponseEntity<Integer>(billid.getBillpaidamount(), HttpStatus.OK);
		System.out.println("Done in API " + name);
		return response;
//		} 
//		catch(Exception e){ throw new ResponseStatusException(HttpStatus.BAD_REQUEST,env.getProperty(e.getMessage() ),e); 
//		}
	}
	@PostMapping(value = "/updateproduct")
	public ResponseEntity<Integer> updateProducts(@RequestBody Billing prod) throws Exception { // to Update product
																										// to DB
		try {
			 System.out.println("Triggered in API" );
			ResponseEntity<Integer> response = null;
			Integer responcefromservice = vts.UpdateProduct(prod);
			response = new ResponseEntity<Integer>(responcefromservice, HttpStatus.OK);
			 System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
	}
	@PostMapping(value = "/loadproducts")
	public ResponseEntity<Integer> loadproducts(@RequestBody List<Billing> prods) throws Exception { // to add products
																										// to DB
		try {
			// System.out.println("Triggered in API" );
			ResponseEntity<Integer> response = null;
			Integer responcefromservice = vts.LoadproductstoDAO(prods);
			response = new ResponseEntity<Integer>(responcefromservice, HttpStatus.OK);
			// System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
	}

	@PostMapping(value = "/returnproduct/{prodid}")
	public ResponseEntity<String> Makenull(@PathVariable int prodid) throws Exception { // to note product as returned
		try {
			System.out.println("Triggered in API");
			ResponseEntity<String> response = null;
			String responcefromservice = vts.Makenull(prodid);
			response = new ResponseEntity<String>(responcefromservice, HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
	}

	@GetMapping(value = "/getbill/{billid}")
	public ResponseEntity<List<Billing>> Getbill(@PathVariable int billid) throws Exception { // to get the bill details
		try {
			System.out.println("Triggered in API");
			ResponseEntity<List<Billing>> response = null;
			List<Billing> responcefromservice = vts.Getbill(billid);
			response = new ResponseEntity<List<Billing>>(responcefromservice, HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
	}

	@GetMapping(value = "/getproduct/{Productid}")
	public ResponseEntity<Billing> GetProduct(@PathVariable Integer Productid) throws Exception { // to get the
																									// productdetails
		try {
			log.info("Triggered in API and logged in log file");
			System.out.println("Triggered in API");
			ResponseEntity<Billing> response = null;
			Billing responcefromservice = vts.GetProduct(Productid);
			response = new ResponseEntity<Billing>(responcefromservice, HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}

	}

	@GetMapping(value = "/product")
	public ResponseEntity<String> Products(@RequestBody Product product) throws Exception { // not in use
		try {
			System.out.println("Triggered in API");
			ResponseEntity<String> response = null;
			System.out.println(product.getProductid());
			System.out.println(product.getProductcost());
			// response=new ResponseEntity<Billing>(responcefromservice,HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}

	}

	@GetMapping(value = "/productslist/{day}/{month}/{year}") // gives the list as object
	public ResponseEntity<List<Billing>> GetMonthlyProduct(@PathVariable String month, @PathVariable String day,@PathVariable String year)
			throws Exception {
		try {
			System.out.println("Triggered in API");
			ResponseEntity<List<Billing>> response = null;
			List<Billing> responcefromservice = vts.GetProductexcel(day,month, year);
			response = new ResponseEntity<List<Billing>>(responcefromservice, HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}

	}

	//exceldownload
	@GetMapping(value = "/productdetailsexcel/{day}/{month}/{year}")					
	public void generateExcel(HttpServletResponse responce,@PathVariable String day,@PathVariable String month, @PathVariable String year)throws Exception {
		try {
			responce.setContentType("application/octet-stream");
			String headerkey="Content-Disposition";
			String headervalue="attachment;filename=data.xls";
			responce.setHeader(headerkey, headervalue);
			vts.generateExcel(responce, day,month,year);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e); 
		}
	}

	@GetMapping(value = "/microcheck")		
	public ResponseEntity<String> microservice()
			throws Exception {
		try {
			System.out.println("Triggered in API");
			ResponseEntity<String> response = null;
//			String responcefromservice = new RestTemplate().getForObject("http://localhost:4403/Practice/microcheck",String.class);
//			response = new ResponseEntity<String>(responcefromservice, HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}

	}
	@GetMapping(value = "/microcheck2")		
	public String microserviceasstring() throws Exception {
		try {
			System.out.println("Triggered in API");
			String response = "Triggered in API of BatService";
//			String responcefromservice = new RestTemplate().getForObject("http://localhost:4403/Practice/microcheck",String.class);
//			response = new ResponseEntity<String>(responcefromservice, HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	@GetMapping(value = "/requestparamcheck")		
	public ResponseEntity<String> requestparams(@RequestParam("data")String item)
			throws Exception {
		try {
			System.out.println(item);
			ResponseEntity<String> response;
			String responcefromservice = item;
			response = new ResponseEntity<String>(responcefromservice, HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}

	}
	@GetMapping(value = "/getall")		
	public ResponseEntity<List<BillingEntity>> getall()
			throws Exception {
		try {
			ResponseEntity<List<BillingEntity>> response;
			System.out.println("Triggered in API");
			List<BillingEntity> responcefromservice = vts.getAll();
			response = new ResponseEntity<List<BillingEntity>>(responcefromservice, HttpStatus.OK);
			System.out.println("Done in API");
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}

	}
	@GetMapping(value="/check")
	public ResponseEntity<String> check() throws Exception{
		try {
			ResponseEntity<String> response;
			System.out.println("Triggered the service");
			String responsefromservice="Triggered in BatApplication MS";
			response = new ResponseEntity<String>(responsefromservice, HttpStatus.OK);
			return response;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
		
	}
	/*
	 * @Scheduled(fixedRate=6000) //few more parameters are initialDelay=5000 public
	 * ResponseEntity<String> Scheduledrun(){ // cron= * * * * * * is used to
	 * represent days or months ResponseEntity<String> response= null; //sample for
	 * the scheduler System.out.println("Running the scheduler"); return response; }
	 */
	}
	


