package org.ncu.GroceryDeliveryApp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ncu.GroceryDeliveryApp.entity.Grocery;
import org.springframework.jdbc.core.RowMapper;

public class BillRowMapper implements RowMapper<Grocery> {

	@Override
	public Grocery mapRow(ResultSet rs, int rowNum) throws SQLException {
		Grocery bitem = new Grocery();
		bitem.setItemName(rs.getString("itemName"));
		bitem.setQuantity(rs.getInt("quantity"));
		bitem.setItemPrice(rs.getFloat("itemPrice"));
		return bitem;
	}

}
