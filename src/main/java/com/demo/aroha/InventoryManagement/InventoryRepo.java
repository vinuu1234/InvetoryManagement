package com.demo.aroha.InventoryManagement;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

public class InventoryRepo {

	static String DB_URL = "jdbc:mysql://localhost:3306/ItemInventorydb";
	static String DB_USER = "root";
	static String DB_PASSWORD = "root";

	public static boolean testConnection() {
	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	        System.out.println(" Database connection successful !!!!!");
	        return true;
	    } catch (SQLException e) {
	    	System.out.println("Oops !!! Something went wrong !!!");
	      //  System.out.println(" Failed to connect to the database. Please check your credentials or database status.");
	       // System.out.println("Error: " + e.getMessage());
	        return false;
	    }
	}

	public static void saveItems(HashMap<Integer, ItemMaster> itemHashMap) {

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
			
		}
	}

	public static void insertTransactions(HashMap<String, Transactions> transactionHashMap) {

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

				ps.addBatch(); 
			}

			int[] result = ps.executeBatch();//executing all trasactions present in batch
			System.out.println("Inserted " + result.length + " Transaction Master into DB.");

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("inserting new transaction ");
		}
	}

	public static void updateItems(HashMap<Integer, ItemMaster> itemHashMap) {

		String updateItem = "update itemMaster set quantityOnHand=? where itemId=?";

		try (java.sql.Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				java.sql.PreparedStatement ps = conn.prepareStatement(updateItem)) {

			for (ItemMaster item : itemHashMap.values()) {

				ps.setInt(1, item.getQuantityOnHand());
				ps.setInt(2, item.getItemId());

				ps.addBatch(); 
			}

			int[] result = ps.executeBatch(); // Execute all at once
			System.out.println("updated " + result.length + " Item Master into DB.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static HashMap<Integer, ItemMaster> getItemsFromDatabase() {
	    HashMap<Integer, ItemMaster> items = new HashMap<>();
	    String selectSQL = "select * from itemmaster";  // Select all items 

	    try (java.sql.Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	         java.sql.Statement stmt = conn.createStatement();
	         java.sql.ResultSet rs = stmt.executeQuery(selectSQL)) {

	        while (rs.next()) {
	            int itemId = rs.getInt("itemId");
	            String itemName = rs.getString("ItemName");
	            int quantityOnHand = rs.getInt("quantityOnHand");
	            double price = rs.getDouble("price");

	            ItemMaster item = new ItemMaster(itemId, itemName, quantityOnHand, price);
	            items.put(itemId, item);  // Add item to HashMap
	        }

	    } catch (SQLException e) {
	        System.out.println("Error retrieving items from database: " + e.getMessage());
	    }
	    return items;
	}

	public static HashMap<String, Transactions> getTransactionsFromDatabase() {
	    HashMap<String, Transactions> transactions = new HashMap<>();
	    String selectSQL = "select *from transactions";  // Select all transactions
	    try (java.sql.Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	         java.sql.Statement stmt = conn.createStatement();
	         java.sql.ResultSet rs = stmt.executeQuery(selectSQL)) {

	        while (rs.next()) {
	            String transId = rs.getString("transId");
	            int itemId = rs.getInt("itemId");
	            int quantityPurchased = rs.getInt("quantityPurchased");
	            double billAmount = rs.getDouble("billAmount");
	            LocalDateTime dateOfTransaction = rs.getTimestamp("dateOfTransaction").toLocalDateTime();

	            Transactions transaction = new Transactions(transId, itemId, quantityPurchased, billAmount, dateOfTransaction);
	            transactions.put(transId, transaction);  // Add transaction to HashMap
	        }

	    } catch (SQLException e) {
	        System.out.println("Error retrieving transactions from database: " + e.getMessage());
	    }
	    return transactions;
	}


	

}
