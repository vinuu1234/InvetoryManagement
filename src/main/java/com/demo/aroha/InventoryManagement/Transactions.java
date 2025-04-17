package com.demo.aroha.InventoryManagement;

import java.time.LocalDateTime;

public class Transactions {

	private String transId;
	private int itemId;
	private int quantityPurchased;
	private double billAmount;
	private LocalDateTime DateOfTransaction;

	public Transactions(String transId, int itemId, int quantityPurchased, double billAmount,
			LocalDateTime dateOfTransaction) {
		super();
		this.transId = transId;
		this.itemId = itemId;
		this.quantityPurchased = quantityPurchased;
		this.billAmount = billAmount;
		DateOfTransaction = dateOfTransaction;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantityPurchased() {
		return quantityPurchased;
	}

	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public LocalDateTime getDateOfTransaction() {
		return DateOfTransaction;
	}

	public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
		DateOfTransaction = dateOfTransaction;
	}

	@Override
	public String toString() {
		
		return "transId=" + transId + ", itemId=" + itemId + ", quantityPurchased=" + quantityPurchased
				+ ", billAmount=" + billAmount + ", DateOfTransaction=" + DateOfTransaction ;
	}
	

}
