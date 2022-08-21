package org.ncu.GroceryDeliveryApp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ncu.GroceryDeliveryApp.entity.Account;
import org.springframework.jdbc.core.RowMapper;

public class AdminRowMapper implements RowMapper<Account> {
	
	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account acc = new Account();
		acc.setAdminUser(rs.getString("adminUser"));
		acc.setAdminPass(rs.getString("adminPass"));
		return acc;
	}
}