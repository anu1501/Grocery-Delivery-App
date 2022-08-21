package org.ncu.GroceryDeliveryApp.entity;

public class Account {

	private String adminUser;
	private String adminPass;
	
	//private int buyerCode;
	private String buyerUser;
	private String buyerPass;
	private String buyerAddress;
	private int buyerPhone;
	
	@Override
	public String toString() {
		return "Account [buyerAddress=" + buyerAddress + ", buyerPhone=" + buyerPhone
				+ "]";
	}

	public String getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public String getBuyerUser() {
		return buyerUser;
	}

	public void setBuyerUser(String buyerUser) {
		this.buyerUser = buyerUser;
	}

	public String getBuyerPass() {
		return buyerPass;
	}

	public void setBuyerPass(String buyerPass) {
		this.buyerPass = buyerPass;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public int getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(int buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
}