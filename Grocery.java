package org.ncu.GroceryDeliveryApp.entity;

public class Grocery 
{
	private int itemCode;
	private String itemName;
	private int itemQuantity;
	private int quantity;
	private Float itemPrice;
	private Float itemDiscount;
	
	@Override
	public String toString() {
		return "Grocery [itemCode=" + itemCode + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity
				+ ", quantity=" + quantity + ", itemPrice=" + itemPrice + ", itemDiscount=" + itemDiscount + "]";
	}

	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Float itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Float getItemDiscount() {
		return itemDiscount;
	}
	public void setItemDiscount(Float itemDiscount) {
		this.itemDiscount = itemDiscount;
	}
}