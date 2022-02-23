package com.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Product;
import com.training.pms.utility.DBConnection;

//CODE - DB 
public class ProductDAOImpl implements ProductDAO {

	Connection connection = DBConnection.getConnection();

	public boolean addProduct(Product product) {
		System.out.println("##Adding products :" + product);
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = connection.prepareStatement("insert into product values(?,?,?,?)");
			statement.setInt(1, product.getProductId());
			statement.setString(2, product.getProductName());
			statement.setInt(3, product.getQuantityOnHand());
			statement.setInt(4, product.getPrice());

			rows = statement.executeUpdate();
			System.out.println(rows + " inserted successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;

	}

	public boolean updateProduct(Product product) {
		System.out.println("##Updating products :" + product);
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = connection.prepareStatement(
					"update product set productName = ? , quantityonhand = ?, price = ? where productid = ?");
			statement.setInt(4, product.getProductId());
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getQuantityOnHand());
			statement.setInt(3, product.getPrice());

			rows = statement.executeUpdate();
			System.out.println(rows + " updated successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}

	public boolean deleteProduct(int productId) {
		System.out.println("##Deleting product with product id  :" + productId);
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = connection.prepareStatement("delete from product where productid = ?");
			statement.setInt(1, productId);
			rows = statement.executeUpdate();
			System.out.println(rows + " deleted successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}

	public Product searchByProductId(int productId) {
		System.out.println("##Searching product with product id  :" + productId);
		Product product = new Product();
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from product where productId = ? ");
			stat.setInt(1, productId);

			ResultSet res = stat.executeQuery();
			res.next();
			product.setProductId(res.getInt(1));
			product.setProductName(res.getString(2));
			product.setQuantityOnHand(res.getInt(3));
			product.setPrice(res.getInt(4));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public List<Product> searchByProductName(String productName) {
		System.out.println("##Search product with product name  :" + productName);
		List<Product> products = new ArrayList<Product>();
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from product where productName like ? ");
			stat.setString(1, productName);

			ResultSet res = stat.executeQuery();
			while (res.next()) {
				Product product = new Product();
				product.setProductId(res.getInt(1));
				product.setProductName(res.getString(2));
				product.setQuantityOnHand(res.getInt(3));
				product.setPrice(res.getInt(4));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public List<Product> getProducts() {
		System.out.println("##Printing all products  ");
		List<Product> products = new ArrayList<Product>();

		Statement stat;
		try {
			stat = connection.createStatement();
			ResultSet res = stat.executeQuery("select * from product");
			while (res.next()) {
				Product product = new Product();
				product.setProductId(res.getInt(1));
				product.setProductName(res.getString(2));
				product.setQuantityOnHand(res.getInt(3));
				product.setPrice(res.getInt(4));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public List<Product> searchProductByPrice(int lowerPrice, int upperPrice) {
		System.out.println("##Search product with product price  :" + lowerPrice + " and " + upperPrice);
		List<Product> products = new ArrayList<Product>();
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from product where price between ? and ?");
			stat.setInt(1, lowerPrice);
			stat.setInt(2, upperPrice);

			ResultSet res = stat.executeQuery();
			while (res.next()) {
				Product product = new Product();
				product.setProductId(res.getInt(1));
				product.setProductName(res.getString(2));
				product.setQuantityOnHand(res.getInt(3));
				product.setPrice(res.getInt(4));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	// 2 - true
	// 909 - false
	public boolean isProductExists(int productId) {
		boolean productExists = false;
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from product where productId = ? ");
			stat.setInt(1, productId);

			ResultSet res = stat.executeQuery();
			productExists = res.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productExists;
	}

}
