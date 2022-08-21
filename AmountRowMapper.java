package org.ncu.GroceryDeliveryApp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ncu.GroceryDeliveryApp.entity.Grocery;
import org.springframework.jdbc.core.RowMapper;

public class AmountRowMapper implements RowMapper<Grocery> {

	@Override
	public Grocery mapRow(ResultSet rs, int rowNum) throws SQLException {
		Grocery bamount = new Grocery();
		bamount.setQuantity(rs.getInt("qty"));
		bamount.setItemPrice(rs.getFloat("money"));
		return bamount;
	}

}
