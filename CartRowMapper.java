package org.ncu.GroceryDeliveryApp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ncu.GroceryDeliveryApp.entity.Grocery;
import org.springframework.jdbc.core.RowMapper;

public class CartRowMapper implements RowMapper<Grocery> {

	@Override
	public Grocery mapRow(ResultSet rs, int rowNum) throws SQLException {
		Grocery citem = new Grocery();
		citem.setItemName(rs.getString("itemName"));
		citem.setItemQuantity(rs.getInt("itemQuantity"));
		citem.setItemPrice(rs.getFloat("itemPrice"));
		return citem;
	}
}