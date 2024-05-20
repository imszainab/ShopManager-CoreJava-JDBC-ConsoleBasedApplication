package edu.jsp.shop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import org.postgresql.Driver;

import edu.jsp.shop.model.Product;
import edu.jsp.shop.model.Shop;

public class Controller {
	static String pgURL="jdbc:postgresql://localhost:5432/shop";
	static Connection connection=null;
	static {
		Driver pgDriver = new Driver();
		try {
			//Register Driver
			DriverManager.registerDriver(pgDriver);
			//Establish Connection
			FileInputStream file = new FileInputStream("dbConfig.properties");
			Properties info = new Properties();
			info.load(file);
			connection = DriverManager.getConnection(pgURL, info);	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//To check shop already exist or not
	public Shop isShopExist() {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM shop");
			Shop isShopExist = new Shop();
			while (resultSet.next()) {
				isShopExist.setId(resultSet.getInt(1));
				isShopExist.setShopName(resultSet.getString(2));
				isShopExist.setAddress(resultSet.getString(3));
				isShopExist.setGst(resultSet.getString(4));
				isShopExist.setContact(resultSet.getLong(5));
				isShopExist.setOwnerName(resultSet.getString(6));	
			}
			return isShopExist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//To Add Shop
	public int addShop(Shop shop) {
		try {
			String insertShop= "INSERT INTO shop VALUES (?,?,?,?,?,?)";
			PreparedStatement prepareStatement = connection.prepareStatement(insertShop);
			prepareStatement.setInt(1, shop.getId());
			prepareStatement.setString(2,shop.getShopName());
			prepareStatement.setString(3,shop.getAddress());
			prepareStatement.setString(4,shop.getGst());
			prepareStatement.setLong(5,shop.getContact());
			prepareStatement.setString(6,shop.getOwnerName());
			return prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//To Add Product/s
	public void addProducts(Shop shop,List<Product> products) {
		for (Product product : products) {
				try {
					//Insert product into product table
					String insertProduct = "INSERT INTO product VALUES (?,?,?,?,?);";
					PreparedStatement prepareStatement = connection.prepareStatement(insertProduct);
					prepareStatement.setInt(1, product.getId());
					prepareStatement.setString(2, product.getProductName());
					prepareStatement.setDouble(3, product.getPrice());
					prepareStatement.setInt(4, product.getQuantity());
					prepareStatement.setBoolean(5, product.isAvailability());
					prepareStatement.executeUpdate();
					//Insert productId ,shopId into shop_product table
					String insertShop_Product="INSERT INTO shop_product VALUES (?,?);";
					PreparedStatement prepareStatement2 = connection.prepareStatement(insertShop_Product);
					prepareStatement2.setInt(1, shop.getId());
					prepareStatement2.setInt(2, product.getId());
					prepareStatement2.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	//Remove Product/s
	public int removeProduct(int productId) {
		try {
//			PreparedStatement removeFromProduct = connection.prepareStatement("DELETE FROM product WHERE id= ?;");
//			removeFromProduct.setInt(1, productId);
//			PreparedStatement removeFromShop_Product = connection.prepareStatement("DELETE FROM shop_product WHERE product_id = ?;");
//			removeFromShop_Product.setInt(1, productId);
//			return removeFromProduct.executeUpdate() + removeFromShop_Product.executeUpdate();
			CallableStatement removeP = connection.prepareCall("Call remove_product(?,?,?);");
			removeP.setInt(1, productId);
			removeP.registerOutParameter(2, Types.INTEGER);
			removeP.registerOutParameter(3, Types.INTEGER);
			removeP.executeUpdate();
			int productCountBefore = removeP.getInt(2);
			int productCountAfter = removeP.getInt(3);
			if (productCountBefore>productCountAfter) {
				return productCountAfter; //product count 0 means last record deleted
			}
			//return productCountBefore-productCountAfter;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return -1;
//	return 0;
	}
	
	// Update Products
	public int updateProduct(Product product) {
		String update="UPDATE product SET product_name = ? , price = ? , quantity = ? WHERE id = ?; ";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(update);
			prepareStatement.setString(1,product.getProductName());
			prepareStatement.setDouble(2, product.getPrice());
			prepareStatement.setInt(3, product.getQuantity()); //0
			prepareStatement.setInt(4,product.getId());
			String update_availability= "UPDATE product SET availability=? WHERE id=?;";
			PreparedStatement availability = connection.prepareStatement(update_availability);
			if (product.getQuantity()==0) {
				availability.setBoolean(1,false);
				availability.setInt(2,product.getId());
				availability.executeUpdate();
				product.setAvailability(false);
			}else {
				availability.setBoolean(1,true);
				availability.setInt(2,product.getId());
				availability.executeUpdate();
				product.setAvailability(true);
			}
			return prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;	
	}

	//To fetch all the Products
	public ResultSet fetchAllProducts() {
		try {
			Statement statement = connection.createStatement();
			return statement.executeQuery("SELECT * FROM product;");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null;
	}
	
	//To check Product has attributes or not
	public ResultSet checkProductResultSet(ResultSet resultSet) {
	Statement statement;
	try {
		statement = connection.createStatement();
		byte count=0;
		
		while(resultSet.next()) {
			if (++count > 0)
				break;
		}
		if (count==1) {
			return statement.executeQuery("SELECT * FROM product;");
		}else
			return null;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	
	//To Get Single Product
	public Product fetchParticularProduct(int id) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM product WHERE id=?;");
			prepareStatement.setInt(1, id);
			ResultSet productResultSet = checkProductResultSet(prepareStatement.executeQuery());
			Product product = new Product();
			while (productResultSet.next()) {
				if(productResultSet.getInt(1)==id) {
					product.setId(productResultSet.getInt(1));
					product.setProductName(productResultSet.getString(2));
					product.setPrice(productResultSet.getDouble(3));
					product.setQuantity(productResultSet.getInt(4));
					product.setAvailability(productResultSet.getBoolean(5));	
				}
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}


	//To Close Connection
	public void closeConnection() {
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
	
	//To fetch all the Products
//	public ResultSet fetchAllProducts() {
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet products = statement.executeQuery("SELECT * FROM product;");
//			byte count =0;
//			while (products.next()) {
//				if (++count >0) {
//					break;
//				}
//			}
//			if (count == 1) {
//				return  statement.executeQuery("SELECT * FROM product;");
//			} else {
//				return null;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	//Update Products
//	public  int update(int productId,Product product) {
//	try {
//		Product UpdatedProduct = product;
//		if (UpdatedProduct.getProductName()!=null) {
//			String updateName= "UPDATE product SET product_name=? WHERE id=?";
//			PreparedStatement name = connection.prepareStatement(updateName);
//			name.setString(1, UpdatedProduct.getProductName());
//			name.setInt(2, productId);
//			return name.executeUpdate();
//		}else if (UpdatedProduct.getPrice()!=0.0) {
//			String updatePrice= "UPDATE product SET price=? WHERE id=?";
//			PreparedStatement price = connection.prepareStatement(updatePrice);
//			price.setDouble(1, UpdatedProduct.getPrice());
//			price.setInt(2, productId);	
//			return price.executeUpdate();
//		}else if (UpdatedProduct.getQuantity()>=0) {
//			if (UpdatedProduct.getQuantity()==0) {
//				Statement availability = connection.createStatement();
//				availability.executeUpdate("UPDATE product SET availability=false WHERE id="+productId+";");
//			}
//			String updateQuantity= "UPDATE product SET quantity=? WHERE id=?";
//			PreparedStatement quantity = connection.prepareStatement(updateQuantity);
//			quantity.setInt(1, UpdatedProduct.getQuantity());
//			quantity.setInt(2, productId);	
//			return quantity.executeUpdate();
//		}
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return 0;
//}



