package com.training.pms.dao;

import com.training.pms.model.Product;

public interface ProductDAO {
	public void addProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(int productId);
	public void searchByProductId(int productId);
	public void searchByProductName(String productName);
	public void searchProductByPrice(int lowerPrice,int upperPrice);
	public void printAllProducts();
}
