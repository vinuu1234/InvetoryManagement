package com.demo.aroha.InventoryManagement;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Scanner;

public class InventoryManagement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		HashMap<Integer, ItemMaster> hm = new HashMap<>();
		HashMap<String, Transactions> tm = new HashMap<>();
		ItemMaster itemMaster;

		System.out.println("============ Menu ===============");
		System.out.println("1. Add New Items");
		System.out.println("2. Do Transactions");
		System.out.println("3. View All Available Items in the Store");
		System.out.println("4. View All Transactions happened ");
		System.out.println("4. exit");

		while (true) {

			System.out.println("Enter Your Choice :");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1: {
				while (true) {
					System.out.println("Enter the ItemId:");
					int itemId = sc.nextInt();
					sc.nextLine();
					if (!hm.containsKey(itemId)) {

						System.out.println("Enter the Item Name :");
						String itemName = sc.nextLine();
						System.out.println("Enter Price of the Item :");
						double itemPrice = sc.nextDouble();
						sc.nextLine();
						System.out.println("Enter the Quantity of Items :");
						int quantity = sc.nextInt();
						ItemMaster item = new ItemMaster(itemId, itemName, quantity, itemPrice);

						hm.put(item.getItemId(), item);
						System.out.println("Inserting to database !!!!!");
						InventoryRepo.saveItems(hm);

						System.out.println("Item added Successufully !!!!! ");
						break;
					} else {
						System.out.println("Duplicate id please enter other id !!!");
					}
				}
				/*
				 * for (Entry<Integer, ItemMaster> entry : hm.entrySet()) {
				 * 
				 * ItemMaster i = entry.getValue(); System.out.println(i);
				 * 
				 * }
				 */

				break;
			}
			case 2: {
				while (true) {
					System.out.println("Enter the Item Id :");
					int itemId = sc.nextInt();
					sc.nextLine();

					if (hm.containsKey(itemId)) {
						double price = hm.get(itemId).getPrice();
						int totalQuantity = hm.get(itemId).getQuantityOnHand();
						String iname = hm.get(itemId).getItemName();
						System.out.println("price of each " + iname + " is " + price);
						int quantityNeeded = 0;
						double totalAmout = 0;
						while (true) {
							System.out.println("Available Stocks :" + totalQuantity);

							System.out.println("Enter Quantity want to purchase : ");
							quantityNeeded = sc.nextInt();
							sc.nextLine();
							if (quantityNeeded > totalQuantity) {
								System.out.println("No Stock found item has only " + totalQuantity + " Stocks");

							} else {
								totalAmout = quantityNeeded * price;
								totalQuantity = totalQuantity - quantityNeeded;
								System.out.println("Total Bill of " + quantityNeeded + " Items is " + totalAmout);

								break;
							}

						}
						// passing argument to item constructor it is updated to item master
						ItemMaster item = new ItemMaster(itemId, iname, totalQuantity, price);
						hm.put(item.getItemId(), item);

						// Transaction part
						LocalDateTime dateTime;
						String payment1 = "UPI";
						String payment2 = "Cash";

						while (true) {
							System.out.println("Enter the payment Mode :");
							String payment = sc.nextLine();

							if (payment.equalsIgnoreCase(payment1)) {
								Random random = new Random();
								String transId = payment1 + random.nextInt(9000000);
								dateTime = LocalDateTime.now();
								Transactions t1 = new Transactions(transId, itemId, quantityNeeded, totalAmout,
										dateTime);
								tm.put(t1.getTransId(), t1);
								InventoryRepo.insertTransactions(tm);
								InventoryRepo.updateItems(hm);
								System.out.println("Transation done successufully with " + payment1 + "!!!!");
								/*
								 * for (Transactions t : tm.values()) { System.out.println(t); }
								 */
								break;
							} else if (payment.equalsIgnoreCase(payment2)) {
								Random random = new Random();
								String transId = payment2 + random.nextInt(9000000);
								dateTime = LocalDateTime.now();
								Transactions t1 = new Transactions(transId, itemId, quantityNeeded, totalAmout,
										dateTime);

								tm.put(t1.getTransId(), t1);
								InventoryRepo.insertTransactions(tm);

								InventoryRepo.updateItems(hm);

								System.out.println("Transation done successufully with " + payment2 + "!!!!");

								/*
								 * for (Transactions t : tm.values()) { System.out.println(t); }
								 */
								break;
							} else {
								System.out.println("Idiate first make payment !!!!!");

							}

						}

						break;
					} else {
						System.out.println("Item Not Found !!!");
					}
				}

				break;

			}

			case 3: {
				System.out.println("=== Available Items in the Store ===");

				for (ItemMaster i : hm.values()) {
					System.out.println(i);

				}
				break;
			}
			case 4: {
				System.out.println("==== Transactions happened ====");

				for (Transactions t : tm.values()) {
					System.out.println(t);
				}
				break;
			}
			case 5: {

				System.out.println(" Exitting !!!");
				break;
			}
			default:
				System.out.println("Please Enter correct Choice ");
			}
		}
	}

}
