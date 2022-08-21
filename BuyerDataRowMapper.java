package org.ncu.GroceryDeliveryApp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ncu.GroceryDeliveryApp.entity.Account;
import org.springframework.jdbc.core.RowMapper;

public class BuyerDataRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account acc = new Account();
		acc.setBuyerAddress(rs.getString("buyerAddress"));
		acc.setBuyerPhone(rs.getInt("buyerPhone"));
		return acc;
		
	}

}
