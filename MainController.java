package org.ncu.GroceryDeliveryApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ncu.GroceryDeliveryApp.dao.AppDao;
import org.ncu.GroceryDeliveryApp.entity.Account;
import org.ncu.GroceryDeliveryApp.entity.Grocery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@Autowired
	private AppDao appDao;

	//---------------------------------------------------------------------
	//                                ADMIN
	//---------------------------------------------------------------------
	
	//================================Admin login code===========================
	
	
	@GetMapping("/adminLoginForm")
	public String adminloginForm(Model model)
	{
		return "adminLogin";
	}
	
	@PostMapping("/adminloginProcess")
	public String adminloginProcess(@RequestParam("username")String user, @RequestParam("password")String pass, Model model)
	{
		if (appDao.checkAdmin(user, pass))
		{
			return "redirect:/adminHomePage";
		}
		else
		{
			return "redirect:/adminLoginForm";
		}
	}
	
	
	//================================Admin Workspace code===========================
	
	//List display page to admin
	@GetMapping("/adminHomePage")
	public String adminPage(Model model)
	{
		List<Grocery> grocerylist = appDao.fetchAll();	
		model.addAttribute("groceries", grocerylist);
		return "adminHome";
	}
	
	//Update
	
	// item List to update form
	@GetMapping("/adminFormUpdate")
	public String showUpdateForm(@RequestParam("itemId")int ic, @ModelAttribute("grocery") Grocery saman, Model model)
	{
		//sending the book obj to view
		saman = appDao.getRecord(ic);
		model.addAttribute(saman);
		return "adminUpdateForm";
	}
	
	//update for to updated item list
	@PostMapping("/itemUpdateProcess")
	public String processUpdateForm(@ModelAttribute("grocery") Grocery saman) {
		
		if(appDao.updateRecord(saman) == 1) {
			System.out.println("Record update successfully!!");
		}
		else {
			System.out.println("Error!!!");
		}
		return "redirect:/adminHomePage";
	}
	
	//Delete
	
	@GetMapping("/adminDeleteItem")
	public String deleteForm(@RequestParam("itemId")int ic) {
		appDao.deleteRecord(ic);
		return "redirect:/adminHomePage";
	}
	
	//Insert Item
	
	@GetMapping("/adminFormInsert")
	public String inputInsertItem()
	{
		System.out.println("Something");
		return "adminInsertForm";
	}

	@PostMapping("itemInsertProcess")
	public String insertProcessForm(@ModelAttribute("grocery")Grocery grocery) {
		
		if(appDao.createRecord(grocery)==1)
		{
			System.out.println("Record Updated Succesfuly");
		}
		else
		{
			System.out.println("Error!");			
		}
		return "confirmation";
	}
	
	//---------------------------------------------------------------------
	//                                BUYER
	//---------------------------------------------------------------------
		
	//================================Buyer login code===========================

	@GetMapping("/buyerLoginForm")
	public String buyerloginForm(Model model)
	{
		return "buyerLogin";
	}
	
	@PostMapping("/buyerloginProcess")
	public String buyerloginProcess(@RequestParam("username") String user, @RequestParam("password") String pass, Model model)
	{
		if(appDao.checkBuyer(user, pass))
		{
			return "redirect:/buyerHomePage";
		}
		else
		{
			return "confirmation";
		}
	}
	
	//================================Buyer registration code===========================

	@GetMapping("/buyerRegistrationForm")
	public String buyerregForm()
	{
		return "buyerRegistration";
	}
	
	@PostMapping("/buyerRegistrationProcess")
	public String buyerregProcess(Model model, HttpServletRequest request, @RequestParam("phone") int phone)
	{
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String address = request.getParameter("address");
		
		Account account = new Account();
		
		account.setBuyerUser(user);
		account.setBuyerPass(pass);
		account.setBuyerAddress(address);
		account.setBuyerPhone(phone);
		
		if(appDao.createAccount(account)==1)
		{
			return "redirect:/buyerLoginForm";
		}
		else
		{
			return "redirect:/buyerRegistrationForm";
		}
	}
	
	//================================Buyer profile update===========================
	
	//Update
	
		// item List to update form
		@GetMapping("/buyerFormUpdate")
		public String buyerUpdateForm(@RequestParam("itemId")int ic, @ModelAttribute("grocery") Grocery saman, Model model)
		{
			//sending the book obj to view
			saman = appDao.getRecord(ic);
			model.addAttribute(saman);
			return "adminUpdateForm";
		}
		
		//update for to updated item list
		@PostMapping("/accountUpdateProcess")
		public String buyerProcessUpdate(@ModelAttribute("grocery") Grocery saman) {
			
			if(appDao.updateRecord(saman) == 1) {
				System.out.println("Record update successfully!!");
			}
			else {
				System.out.println("Error!!!");
			}
			return "redirect:/adminHomePage";
		}
	
	//================================Buyer Shopping Space===========================
	
	//List display page to admin
	@RequestMapping("/buyerHomePage")
	public String buyerPage(Model model, HttpServletRequest request)
	{
		String orderby = request.getParameter("order");
		String item = request.getParameter("sitem");
		String comparisonasc = "asc";
		String comparisondesc = "desc";
		if(orderby!=null)
		{
			if(orderby.equals(comparisonasc))
			{
			System.out.println(orderby);
			List<Grocery> glist = appDao.orderbyASC();
			model.addAttribute("groceries", glist);
			}
			else if(orderby.equals(comparisondesc))
			{
			System.out.println(orderby);
			List<Grocery> glist = appDao.orderbyDESC();
			model.addAttribute("groceries", glist);
			} 	
		}
		else if(item!=null)
		{
			List<Grocery> grocerylist = appDao.searchItem(item);
			model.addAttribute("groceries", grocerylist);
		}
		else
		{
			List<Grocery> grocerylist = appDao.fetchAll();
			model.addAttribute("groceries", grocerylist);
		}
		List<Grocery> cartlist = appDao.fetchAllcItems();
		model.addAttribute("cItems",cartlist);
		return "buyerHome";
	}
	
	@GetMapping("/buyerAddtoCart")
	public String addToCart(@RequestParam("cItemId")int ci) {
		Grocery variable = appDao.selectCartItem(ci);
		appDao.addCartItem(variable);
		return "redirect:/buyerHomePage";
	}
	
	@GetMapping("/buyerDeletefromCart")
	public String deleteFromCart(@RequestParam("cItemName")String ci) {
		appDao.removeCartItem(ci);
		return "redirect:/buyerHomePage";
	}
	
	//================================Final Billing===========================
	
	// Goes from Buyer main page to Bill Page
	@RequestMapping("checkOut")
	public String goToBill(Model model)
	{
		// For Bill Items
		List<Grocery> x = appDao.selectBillItems();	
		for(Grocery temp1 : x) {
			appDao.addToBillList(temp1);
		}
		List<Grocery> y = appDao.getFinalBill();	
		model.addAttribute("FinalBillItems", y);
		
		//For Billing Amount
		List<Grocery> a = appDao.selectBillPrice();	
		for(Grocery temp2 : a) {
			appDao.addToAmount(temp2);
		}
		List<Grocery> b = appDao.getBillAmount();	
		model.addAttribute("FinalBillAmount", b);
				
		return "billPage";
	}
	
	// terminate all temporary table data for the current buyer and log out
		@RequestMapping("buyerPayment")
		public String buyerPayment(Model model,Account currentBuyer)
		{
			appDao.terminateCart();
			appDao.terminateBill();
			appDao.terminateAmount();
			//Account bi = appDao.getBuyerInfo(currentBuyer);	
			//model.addAttribute("buyerInfo", bi);
			return "thankYou";
		}
	
	//================================Returning Buttons (LogOut?)===========================
	
	// Go back Buyer shop page from bill page to edit cart items
	@RequestMapping("backToShop")
	public String backToShop()
	{
		appDao.terminateBill();
		appDao.terminateAmount();
		return "redirect:/buyerHomePage";
	}
	
	@RequestMapping("logOut")
	public String logOut()
	{
		return "home";
	}
	
	// Go back to index
	@RequestMapping("goBack")
	public String goToIndex()
	{
		return "home";
	}
	
}