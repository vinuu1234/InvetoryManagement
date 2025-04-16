package com.demo.aroha.InventoryManagement;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

public class InventoryRepo {
	public static void saveItems(HashMap<Integer, ItemMaster> itemHashMap) {

		String DB_URL = "jdbc:mysql://localhost:3306/ItemInventorydb";
		String DB_USER = "root";
		String DB_PASSWORD = "root";

		String insertSQL = "INSERT INTO itemMaster (itemId, ItemName, quantityOnHand, price) VALUES (?, ?, ?, ?)";

		try (java.sql.Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				java.sql.PreparedStatement ps = conn.prepareStatement(insertSQL)) {
			for (ItemMaster item : itemHashMap.values()) {

				ps.setInt(1, item.getItemId());
				ps.setString(2, item.getItemName());
				ps.setInt(3, item.getQuantityOnHand());
				ps.setDouble(4, item.getPrice());

				ps.addBatch(); // Add to batch for efficient insert
			}

			int[] result = ps.executeBatch(); // Execute all at once
			System.out.println("Inserted " + result.length + " Item Master into DB.");

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Duplicate item is Found !!!" + e.getMessage());
		}
	}

	public static void insertTransactions(HashMap<String, Transactions> transactionHashMap) {

		String DB_URL = "jdbc:mysql://localhost:3306/ItemInventorydb";
		String DB_USER = "root";
		String DB_PASSWORD = "root";

		// String insertSQL = "update Transactions set
		// transId=?,quantityPurchased=?,billAmount=?,dateOfTransaction=? where itemId=?
		// ";
		String insertTras = "insert into Transactions (transId,itemId,quantityPurchased,billAmount,dateOfTransaction) values(?,?,?,?,?)";
		try (java.sql.Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				java.sql.PreparedStatement ps = conn.prepareStatement(insertTras)) {

			for (Transactions trans : transactionHashMap.values()) {
				ps.setString(1, trans.getTransId());
				ps.setInt(3, trans.getQuantityPurchased());
				ps.setDouble(4, trans.getBillAmount());
				LocalDateTime now = LocalDateTime.now();
				ps.setTimestamp(5, Timestamp.valueOf(now));
				ps.setInt(2, trans.getItemId());

				ps.addBatch(); // Add to batch for efficient insert
			}

			int[] result = ps.executeBatch();// Execute all at once

			System.out.println("Inserted " + result.length + " Transaction Master into DB.");

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("inserting new transaction " + e.getMessage());
		}
	}

	public static void updateItems(HashMap<Integer, ItemMaster> itemHashMap) {

		String DB_URL = "jdbc:mysql://localhost:3306/ItemInventorydb";
		String DB_USER = "root";
		String DB_PASSWORD = "root";

		String updateItem = "update itemMaster set quantityOnHand=? where itemId=?";

		try (java.sql.Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				java.sql.PreparedStatement ps = conn.prepareStatement(updateItem)) {

			for (ItemMaster item : itemHashMap.values()) {

				ps.setInt(1, item.getQuantityOnHand());
				ps.setInt(2, item.getItemId());

				ps.addBatch(); // Add to batch for efficient insert
			}

			int[] result = ps.executeBatch(); // Execute all at once
			System.out.println("updated " + result.length + " Item Master into DB.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
