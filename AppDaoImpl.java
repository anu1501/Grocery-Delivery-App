package org.ncu.GroceryDeliveryApp.dao;

import java.util.List;

import org.ncu.GroceryDeliveryApp.entity.Account;
import org.ncu.GroceryDeliveryApp.entity.Grocery;
import org.ncu.GroceryDeliveryApp.rowmapper.AdminRowMapper;
import org.ncu.GroceryDeliveryApp.rowmapper.AmountRowMapper;
import org.ncu.GroceryDeliveryApp.rowmapper.BillRowMapper;
import org.ncu.GroceryDeliveryApp.rowmapper.BuyerDataRowMapper;
import org.ncu.GroceryDeliveryApp.rowmapper.BuyerLoginRowMapper;
import org.ncu.GroceryDeliveryApp.rowmapper.CartRowMapper;
import org.ncu.GroceryDeliveryApp.rowmapper.GroceryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppDaoImpl implements AppDao {

	  @Autowired 
	  private JdbcTemplate jdbcTemplate;
	 
	//Fetch All items from Grocery List Table
	@Override
	public List<Grocery> fetchAll() {
		String sql ="select * from groceries";
		List<Grocery> groceryItemList = jdbcTemplate.query(sql, new GroceryRowMapper());
		return groceryItemList;
	}
	  
	@Override
	public List<Grocery> fetchAllcItems() {
		String sql ="select * from cart";
		List<Grocery> cartItemList = jdbcTemplate.query(sql, new CartRowMapper());
		return cartItemList;
	}

	
	//---------------------------------------------------------------------
	//                                ADMIN
	//---------------------------------------------------------------------
	
	// Method for verifying admin  in database (check from Admin Table)
	@Override
	public Boolean checkAdmin(String user, String pass) {
		String sql ="select * from admin where adminUser = ? and adminPass = ?";
		Object[] args = {user,pass};
		List<Account> res = jdbcTemplate.query(sql, new AdminRowMapper(), args);
		if(res.isEmpty())	
			return false;
		else
			return true;
	}

	// Method for inserting a record into the grocery table (Admin)
	@Override
	public int createRecord(Grocery item) {
		String sql="insert into groceries values(?,?,?,?,?)";
		Object[] args= {item.getItemCode(), item.getItemName(), item.getItemQuantity(),item.getItemPrice(), item.getItemDiscount()};
		int rowInserted =jdbcTemplate.update(sql, args);
		return rowInserted;
	}

	// method to delete a record from the database
	@Override
	public void deleteRecord(int ic) {
		String sql = "delete from groceries where itemCode = ?";
		jdbcTemplate.update(sql, ic);
	}

	// Method for updating records of the database table
	@Override
	public int updateRecord(Grocery item) {
		String sql = "update groceries set itemName = ?, itemQuantity= ?, itemPrice = ?, itemDiscount = ? where itemCode = ?";
		Object[] args = {item.getItemName(), item.getItemQuantity(),item.getItemPrice(), item.getItemDiscount(), item.getItemCode()};
		int itemsUpdated = jdbcTemplate.update(sql, args);
		return itemsUpdated;
	}
	

	@Override
	public Grocery getRecord(int ic) {
		String sql ="select * from groceries where itemCode=?";
		Grocery fetchedRecord= jdbcTemplate.queryForObject(sql, new GroceryRowMapper(),ic);
		return fetchedRecord;
	}

	//----------------------------------------------------------------------
	//                                Buyer
	//----------------------------------------------------------------------

	//=======================Buyer login page================================
	
	// Method for verifying BuyerAccount in database (check from Buyer Table)
	@Override
	public Boolean checkBuyer(String buyerUser, String buyerPass) {
		// TODO Auto-generated method stub
		String sql ="select * from buyer where buyerUser = ? and buyerPass = ?";
		Object[] args = {buyerUser, buyerPass};
		List<Account> res = jdbcTemplate.query(sql, new BuyerLoginRowMapper(), args);
		
		if(res.isEmpty())
			return false;
		else
			return true;
	}

	// Method for creating BuyerAccount in database (Buyer Table)
	@Override
	public int createAccount(Account binfo) {
		// TODO Auto-generated method stub
		String sql = "insert into buyer values(?, ?, ?, ?)";
		Object[] args = {binfo.getBuyerUser(), binfo.getBuyerPass(), binfo.getBuyerAddress(), binfo.getBuyerPhone()};
		int res = jdbcTemplate.update(sql, args);
		return res;
	}

	// Method for updating Profile of Buyer in Buyer table
	@Override
	public int updateAccount(Account bdata) {
		String sql = "update buyer set buyerAddress = ?, buyerPhone= ? where buyerUser = ?";
		Object[] args = {bdata.getBuyerAddress(),bdata.getBuyerPass(), bdata.getBuyerUser()};
		int itemsUpdated = jdbcTemplate.update(sql, args);
		return itemsUpdated;
	}
	
	//Buyer info for Thankyou page
	@Override
	public Account getBuyerInfo(Account buyerUser) {
		String sql ="select * from buyer where buyerUser=?";
		Account fetchedRecord= jdbcTemplate.queryForObject(sql, new BuyerDataRowMapper(),buyerUser);
		return fetchedRecord;
	}
	
	//=======================Buyer Shopping page================================
	
	// Method for searching item from Groceries table of selected item
	@Override
	public List<Grocery> searchItem(String itemName) {
		// TODO Auto-generated method stub
		String sql = "select * from groceries where itemName = ?";
		List<Grocery> searchedItem = jdbcTemplate.query(sql, new GroceryRowMapper(), itemName);
		return searchedItem;
	}
	
	@Override
	public List<Grocery> orderbyASC() {
		String sql = "select * from groceries order by itemPrice";
		List<Grocery> ordered = jdbcTemplate.query(sql, new GroceryRowMapper());
		return ordered;
	}

	@Override
	public List<Grocery> orderbyDESC() {
		String sql = "select * from groceries order by itemPrice desc";
		List<Grocery> ordered = jdbcTemplate.query(sql, new GroceryRowMapper());
		return ordered;
	}

	// method to select an item from Groceries table and add to cart table
	@Override
	public Grocery selectCartItem(int ic) {
		String sql ="select * from groceries where itemCode=?";
		Grocery fetchedRecord= jdbcTemplate.queryForObject(sql, new GroceryRowMapper(),ic);
		return fetchedRecord;
	}
	
	@Override
	public List<Grocery> addCartItem(Grocery Record) {
		String query1="insert into cart values(?,?,?)";
		Object[] args= {Record.getItemName(), Record.getItemQuantity(),Record.getItemPrice()};
		jdbcTemplate.update(query1, args);
		String query2="select * from cart";
		List<Grocery> cartItemList = jdbcTemplate.query(query2, new CartRowMapper());
		return cartItemList;
	}
	
	// delete based on random inserted int
	
	// method to delete an item from cart table
	@Override
	public void removeCartItem(String itemName) {
		String sql = "delete from cart where itemName = ?";
		jdbcTemplate.update(sql, itemName);
	}


	//=======================Buyer Bill Page================================
	
	// Method for fetching all items of the cart table for billing table
	
	//take needed data items from cart table
	@Override
	public List<Grocery> selectBillItems() {
		String sql ="select itemName , count(itemName) as itemQuantity, sum(itemPrice) as itemPrice from cart group by itemName";
		List<Grocery> fetchedRecord= jdbcTemplate.query(sql, new CartRowMapper());
		return fetchedRecord;
	}
	
	//add the needed data items to bill table
	@Override
	public int addToBillList(Grocery Record ) {
		String sql="insert into bill values(?,?,?)";
		Object[] args={Record.getItemName(), Record.getItemQuantity(),Record.getItemPrice()};
		int res = jdbcTemplate.update(sql, args);
		return res;				 
	}
	
	//print bill table
	@Override
	public List<Grocery> getFinalBill() {
		String sql ="select * from bill";
		List<Grocery> billItemList = jdbcTemplate.query(sql, new BillRowMapper());
		return billItemList;
	}
	
	
	// method to get prices from cart table and get final amount
	
	//take needed qty and amount from cart table
	@Override
	public List<Grocery> selectBillPrice() {
		String sql ="select itemName , count(itemName) as itemQuantity, sum(itemPrice) as itemPrice from cart;";
		List<Grocery> fetchedRecord= jdbcTemplate.query(sql, new CartRowMapper());
		return fetchedRecord;
	}
		
	//add the needed qty and amount to amount table
	@Override
	public int addToAmount(Grocery Record ) {
		String sql="insert into amount values(?,?)";
		Object[] args= {Record.getItemQuantity(), Record.getItemPrice()};
		int res = jdbcTemplate.update(sql, args);
		return res;				 
	}
	
	//print amount table
	@Override
	public List<Grocery> getBillAmount() {
		String sql ="select * from amount";
		List<Grocery> billItemList = jdbcTemplate.query(sql, new AmountRowMapper());
		return billItemList;

	}
	
	
	//+++++++++++++++++++++++++Terminate methods+++++++++++++++

	//clear cart table (affect on buyer home page)
	@Override
	public int terminateBill() {
		String query ="delete from bill";
		int result= jdbcTemplate.update(query);
		return result;
	}
	
	//clear Bill item table (so no repetitions of items in bill table after editing cart)
	@Override
	public int terminateCart() {
		String query ="delete from cart";
		int result= jdbcTemplate.update(query);
		return result;
	}

	//clear Amount table (so no repeted adding amount in amount table after editing cart)
	@Override
	public int terminateAmount() {
		String query ="delete from amount";
		int result= jdbcTemplate.update(query);
		return result;
	}
	
}