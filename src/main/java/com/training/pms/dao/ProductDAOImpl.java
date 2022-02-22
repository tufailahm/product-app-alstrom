package com.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.pms.model.Product;
import com.training.pms.utility.DBConnection;

//CODE - DB 
public class ProductDAOImpl implements ProductDAO {

	Connection connection = DBConnection.getConnection();

	public void addProduct(Product product) {
		System.out.println("##Adding products :" + product);
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("insert into product values(?,?,?,?)");
			statement.setInt(1, product.getProductId());
			statement.setString(2, product.getProductName());
			statement.setInt(3, product.getQuantityOnHand());
			statement.setInt(4, product.getPrice());

			int rows = statement.executeUpdate();
			System.out.println(rows + " inserted successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateProduct(Product product) {
		System.out.println("##Updating products :" + product);
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement
					("update product set productName = ? , quantityonhand = ?, price = ? where productid = ?");
			statement.setInt(4, product.getProductId());
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getQuantityOnHand());
			statement.setInt(3, product.getPrice());

			int rows = statement.executeUpdate();
			System.out.println(rows + " updated successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteProduct(int productId) {
		System.out.println("##Deleting product with product id  :" + productId);
	}

	public void searchByProductId(int productId) {
		System.out.println("##Searching product with product id  :" + productId);
	}

	public void searchByProductName(String productName) {
		System.out.println("##Search product with product name  :" + productName);
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from product where productName like ? ");
			stat.setString(1, productName);

			ResultSet res = stat.executeQuery();
			while (res.next()) {
				System.out.print(res.getString(1) + "   ");
				System.out.print(res.getString(2) + "    ");
				System.out.print(res.getString(3) + "    ");
				System.out.println(res.getString(4) + "    ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void printAllProducts() {
		System.out.println("##Printing all products  ");
		Statement stat;
		try {
			stat = connection.createStatement();
			ResultSet res = stat.executeQuery("select * from product");
			while (res.next()) {
				System.out.print(res.getString(1) + "   ");
				System.out.print(res.getString(2) + "    ");
				System.out.print(res.getString(3) + "    ");
				System.out.println(res.getString(4) + "    ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void searchProductByPrice(int lowerPrice, int upperPrice) {
		System.out.println("##Search product with product price  :" + lowerPrice + " and " + upperPrice);
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from product where price between ? and ?");
			stat.setInt(1, lowerPrice);
			stat.setInt(2, upperPrice);

			ResultSet res = stat.executeQuery();
			while (res.next()) {
				System.out.print(res.getString(1) + "   ");
				System.out.print(res.getString(2) + "    ");
				System.out.print(res.getString(3) + "    ");
				System.out.println(res.getString(4) + "    ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
