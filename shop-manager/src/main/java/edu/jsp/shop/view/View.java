package edu.jsp.shop.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.jsp.shop.controller.Controller;
import edu.jsp.shop.model.Product;
import edu.jsp.shop.model.Shop;

public class View {
	static Scanner myInput = new Scanner(System.in);
	static Controller controller = new Controller();
	static Shop shop = new Shop();
	static {
		// Ask shop details for 1st run of appln
		// From 2nd run onwards check if shop exist,if yes then use existing
		Shop shopExist = controller.isShopExist();
		if (shopExist.getId() != 0) { // Shop Exist
			shop = shopExist; // Maintaining only 1 reference for further operation
			System.out.println("--------| Welcome Back to Shop |--------");
			System.out.println("S H O P   D E T A I L S |--------------- ");
			System.out.println("Id : " + shop.getId());
			System.out.println("Name : " + shop.getShopName());
			System.out.println("Address : " + shop.getAddress());
			System.out.println("GST : " + shop.getGst());
			System.out.println("Contact : " + shop.getContact());
			System.out.println("Owner : " + shop.getOwnerName() + "\n");
		} else {
			System.out.println("--------| Welcome to Shop |--------");
			System.out.print("Enter Id : ");
			shop.setId(myInput.nextInt());
			myInput.nextLine();
			System.out.print("Enter Shop Name : ");
			shop.setShopName(myInput.nextLine());
			System.out.print("Enter Owner Name : ");
			shop.setOwnerName(myInput.nextLine());
			System.out.print("Enter Shop Address  : ");
			shop.setAddress(myInput.nextLine());
			System.out.print("Enter GST Number : ");
			shop.setGst(myInput.nextLine());
			System.out.print("Enter Contact Number : ");
			shop.setContact(myInput.nextLong());
			myInput.nextLine();
			if (controller.addShop(shop) != 0) {
				System.out.println("Shop Added!\n");
			} else
				System.out.println("Oops! Something went wrong...\n");
		}
	}

	public static void main(String[] args) {
		do {
			System.out.println("Select operation to perform : ");
			System.out.println("1. Add Product/s \n2. Get Product/s \n3. Remove Product/s \n4. Update Product/s \n0. Exit");
			System.out.print("Enter digit respective to desired option : ");
			byte userChoice = myInput.nextByte();
			myInput.nextLine();
			switch (userChoice) {
			case 1: // 1. Add Product/s
				List<Product> products = new ArrayList<Product>();
				boolean continueToAdd = true;
				do {
					Product product = new Product();
					System.out.print("Enter product Id : ");
					product.setId(myInput.nextInt());
					myInput.nextLine();
					System.out.print("Enter product Name : ");
					product.setProductName(myInput.nextLine());
					System.out.print("Enter product Price : ");
					product.setPrice(myInput.nextDouble());
					myInput.nextLine();
					System.out.print("Enter product quantity : ");
					int quantity = myInput.nextInt();
					myInput.nextLine();
					product.setQuantity(quantity);
					if (quantity > 0) {
						// Set availability true
						product.setAvailability(true);
					} else {
						// Set availability false\
						product.setAvailability(false);
					}
					products.add(product);
					System.out.print("Continue to add product ? y/n : ");
					String toContinue = myInput.nextLine();
					if (toContinue.equalsIgnoreCase("n")) {
						continueToAdd = false;
					}
				} while (continueToAdd);
				//
				controller.addProducts(shop, products);
				System.out.println("Products added Successfully !\n");
				break;
			case 2:
				System.out.print("Enter Product Id : ");
				int p_id = myInput.nextInt();
				myInput.nextLine();
				try {
					Product product = controller.fetchParticularProduct(p_id);
					if(product.getId()==p_id) {
						System.out.printf("| %-15s ","ID");
						System.out.printf("| %-10s |%n",product.getId());
						System.out.printf("| %-15s ","PRODUCT NAME");
						System.out.printf("| %-10s |%n",product.getProductName());
						System.out.printf("| %-15s ","PRICE");
						System.out.printf("| %-10s |%n",product.getPrice());
						System.out.printf("| %-15s ","QUANTITY");
						System.out.printf("| %-10s |%n",product.getQuantity());
						System.out.printf("| %-15s ","AVAILABILITY");
						System.out.printf("| %-10s |%n",product.isAvailability());	
						System.out.println("Product Information Fetched Successfully. \n");
					}
				}catch(NullPointerException e) {
					System.out.println("No Such Product Exist..Details Cannot be Displayed!\n");
				}
				break;
			case 3:
				ResultSet productsResultSet = controller.fetchAllProducts();
				if (productsResultSet == null) {
					System.out.println("No product! Remove operation cannot be performed.\n");
				} else {
					System.out.println("Available products in shop :");
					System.out.println("ID    | PRODUCT NAME");
					try {
						while (productsResultSet.next()) {
							System.out.print(productsResultSet.getInt(1) + "    | ");
							System.out.println(productsResultSet.getString(2));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.print("Enter product id to remove :");
					int removeId = myInput.nextInt();
					myInput.nextLine();
					if (controller.removeProduct(removeId) >= 0) {
						System.out.println("Product Removed Successfully! \n");
					} else // -1 means no id match
						System.out.println("Invalid Product Id : " + removeId + " \n");
				}
				break;
			case 4:
				ResultSet productsAvailable = controller.fetchAllProducts();
				if (productsAvailable == null) {
					System.out.println("No product! Update operation cannot be performed.\n");
				} else {
					System.out.println("Available products in shop :");
					System.out.printf(" | %-4s | %-11s | %-11s | %-10s | %-10s  |%n","ID","PRODUCT NAME","PRICE","QUANTITY","AVAILABILITY");
					try {
						while (productsAvailable.next()) {
							System.out.printf(" | %-4d |",productsAvailable.getInt(1));
							System.out.printf(" %-12s |",productsAvailable.getString(2));
							System.out.printf(" %-11f |",productsAvailable.getDouble(3));
							System.out.printf(" %-10d |",productsAvailable.getInt(4));
							System.out.printf(" %-13b | %n",productsAvailable.getBoolean(5));
						}	
						
						System.out.print("Enter Product Id : ");
						int id = myInput.nextInt();
						myInput.nextLine();
						
						Product productToUpdate = controller.fetchParticularProduct(id); //null pointer
						System.out.printf("| %-15s ","ID");
						System.out.printf("| %-10s |%n",productToUpdate.getId());
						System.out.printf("| %-15s ","PRODUCT NAME");
						System.out.printf("| %-10s |%n",productToUpdate.getProductName());
						System.out.printf("| %-15s ","PRICE");
						System.out.printf("| %-10s |%n",productToUpdate.getPrice());
						System.out.printf("| %-15s ","QUANTITY");
						System.out.printf("| %-10s |%n",productToUpdate.getQuantity());
						System.out.printf("| %-15s ","AVAILABILITY");
						System.out.printf("| %-10s |%n",productToUpdate.isAvailability());
						
						if (productToUpdate!=null) {
							System.out.println("What to Update ?");
							System.out.println("1. Product Name \n2. Product Price \n3. Product Quantity ");
							System.out.print("Enter digit respective to desired option : ");
							int userUpdate = myInput.nextInt();
							myInput.nextLine();

							switch (userUpdate) {
							case 1:
								System.out.print("Enter Product Name :");
								productToUpdate.setProductName(myInput.nextLine());
//								controller.updateProduct(productToUpdate);
//								System.out.println("Updated Product Name : " + productToUpdate.getProductName() + "\n");
								break;
							case 2:
								System.out.print("Enter Product Price :");
								productToUpdate.setPrice(myInput.nextDouble());
								break;
							case 3:
								System.out.print("Enter Product Quantity : ");
								productToUpdate.setQuantity(myInput.nextInt());
								break;
							}
							if (controller.updateProduct(productToUpdate) != 0) {
								System.out.printf("| %-15s ","ID");
								System.out.printf("| %-10s |%n",productToUpdate.getId());
								System.out.printf("| %-15s ","PRODUCT NAME");
								System.out.printf("| %-10s |%n",productToUpdate.getProductName());
								System.out.printf("| %-15s ","PRICE");
								System.out.printf("| %-10s |%n",productToUpdate.getPrice());
								System.out.printf("| %-15s ","QUANTITY");
								System.out.printf("| %-10s |%n",productToUpdate.getQuantity());
								System.out.printf("| %-15s ","AVAILABILITY");
								System.out.printf("| %-10s |%n",productToUpdate.isAvailability());
								System.out.println("PRODUCT UPDATED SUCCESSFULLY ! \n");
							}else {
								System.out.println("no update");
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						System.out.println("No Product Exist With Specified Id !\n");
					}
				}
				break;
			case 0: // 0. Exit
				System.out.println("----- E X I T -----");
				System.exit(0);
				break;
			default:
				System.out.println("------------- INVALID SELECTION -------------\n");
				break;
			}
		} while (true);
	}

}
