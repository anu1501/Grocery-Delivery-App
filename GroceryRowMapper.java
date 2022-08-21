package org.ncu.GroceryDeliveryApp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ncu.GroceryDeliveryApp.entity.Grocery;
import org.springframework.jdbc.core.RowMapper;

public class GroceryRowMapper implements RowMapper<Grocery> {

	@Override
	public Grocery mapRow(ResultSet rs, int rowNum) throws SQLException {
		Grocery item = new Grocery();
		item.setItemCode(rs.getInt("itemCode"));
		item.setItemName(rs.getString("itemName"));
		item.setItemQuantity(rs.getInt("itemQuantity"));
		item.setItemPrice(rs.getFloat("itemPrice"));
		item.setItemDiscount(rs.getFloat("itemDiscount"));
		return item;
	}
}