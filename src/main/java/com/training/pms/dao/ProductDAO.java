package com.training.pms.dao;

import java.util.List;

import com.training.pms.model.Product;

public interface ProductDAO {
	public boolean addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(int productId);
	public Product searchByProductId(int productId);
	public List<Product> searchByProductName(String productName);
	public List<Product> searchProductByPrice(int lowerPrice,int upperPrice);
	public List<Product> getProducts();
	
	public boolean isProductExists(int productId);
}
