package org.ncu.GroceryDeliveryApp.dao;

import java.util.List;

import org.ncu.GroceryDeliveryApp.entity.Account;
import org.ncu.GroceryDeliveryApp.entity.Grocery;

public interface AppDao {	
	
	//Fetch all Items from grocery list
	public List<Grocery> fetchAll();
	
	//Fetch all Items from cart list
	public List<Grocery> fetchAllcItems();
	
	//---------------------------------------------------------------------
	//                                ADMIN
	//---------------------------------------------------------------------
	
	// Method for verifying admin  in database (check from Admin Table)
	public Boolean checkAdmin(String adminUser, String adminPass);
	
	// Method for inserting a record into the grocery table (Admin)
	public int createRecord(Grocery data);
	
	// method to delete a record from the database
	public void deleteRecord(int itemCode);
	
	// Method for updating records of the database table
	public int updateRecord(Grocery data);
	
	// Method for getting records for specific itemCode of grocery list
	public Grocery getRecord(int itemCode);
	
	//----------------------------------------------------------------------
	//                                Buyer
	//---------------------------------------------------------------------

	//Login
	// Method for verifying BuyerAccount in database (check from Buyer Table)
	public Boolean checkBuyer(String buyerUser, String buyerPass);
	
	//Register
	// Method for creating BuyerAccount in database (Buyer Table)
	public int createAccount(Account binfo);
	
	//Update profile
	// Method for updating Profile of Buyer in Buyer table
	public int updateAccount(Account bdata);
	
	//Get Buyer Address and phone no.
	public Account getBuyerInfo(Account buyerUser);
	
	//========================Buyer Shop==============================
	
	// Method for searching item from Groceries table of selected item
	public List<Grocery> searchItem(String itemName);
	
	//------------------------Filter ------------------------------------
	
	// method to select a item from Groceries table and add to cart table
	public Grocery selectCartItem(int itemCode);
	
	//Price Ascending order filter
	public List<Grocery> orderbyASC();
	
	//Price Ascending order filter
	public List<Grocery> orderbyDESC();

	
	//----------------------------------------------------------
	
	// method to select a item from Groceries table and add to cart table
	public List<Grocery> addCartItem(Grocery Record);
	
	// method to select a item from Groceries table and add to cart table
	public void removeCartItem(String itemName);
	
	//=======================================================
	
	//After CHECKOUT 
	
	// Method for fetching all items of the cart table for billing

	//take needed data items from cart table
	public List<Grocery> selectBillItems();
	//add the needed data items to bill table
	public int addToBillList(Grocery rec);	
	//print bill table
	public List<Grocery> getFinalBill();
	
	
	//Bill amount
	
	//take needed qty and amount from cart table
	public List<Grocery> selectBillPrice();
	//add the needed qty and amount to amount table
	public int addToAmount(Grocery Record);
	//print amount table
	public List<Grocery> getBillAmount();

	
	//Logout/exit methods
	
	//clear cart table (affect on buyer home page)
	public int terminateCart();
	//clear Bill item table (so no repetitions of items in bill table after editing cart) 
	public int terminateBill();
	//clear Amount table (so no repeted adding amount in amount table after editing cart)
	public int terminateAmount();

	//----------------------------------------------------------------------
	//                         Extras
	/*	
	
	// Method for updating records of the database table
	public int updateBook(Grocery kitab);
	
	*/
}