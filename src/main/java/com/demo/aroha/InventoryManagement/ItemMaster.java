package com.demo.aroha.InventoryManagement;

public class ItemMaster {
	private int itemId;
	private String itemName;
	private int quantityOnHand;
	private double price;
	
	public ItemMaster(int itemId, String itemName, int quantityOnHand, double price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantityOnHand = quantityOnHand;
		this.price = price;
	}
	
	public ItemMaster() {
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantityOnHand() {
		return quantityOnHand;
	}
	public void setQuantityOnHand(int quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "ItemMaster [itemId=" + itemId + ", itemName=" + itemName + ", quantityOnHand=" + quantityOnHand
				+ ", price=" + price + "]";
	}
	
	
	

}
