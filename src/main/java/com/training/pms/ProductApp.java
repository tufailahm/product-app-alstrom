package com.training.pms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.training.pms.dao.ProductDAO;
import com.training.pms.dao.ProductDAOImpl;
import com.training.pms.model.Product;

public class ProductApp {
	Scanner scanner = new Scanner(System.in);
	int choice = 0;
	// Animal a = new Cat();
	ProductDAO productDAO = new ProductDAOImpl();
	Product product = new Product();
	boolean result;
	List<Product> products = new ArrayList<Product>();

	public void startProductApp() throws IOException {

		// declaring local variables for input
		int productId = 0;
		String productName = null;
		int quantityOnHand = 0;
		int price = 0;

		while (true) {
			System.out.println("=================================================================");
			System.out.println("P R O D U C T    -     APP      M E N U");
			System.out.println("1. Add Product ");
			System.out.println("2. Delete Product ");
			System.out.println("3. Update Product ");
			System.out.println("4. Search Product By Id ");
			System.out.println("5. Search Product By Name ");
			System.out.println("6. Print All Products ");
			System.out.println("7. Search Product By Price(Lower/Upper) ");
			System.out.println("9. E X I T ");

			System.out.println("Please enter your choice : ");
			choice = scanner.nextInt();
			System.out.println("=================================================================");

			switch (choice) {
			case 1:
				// add product section
				System.out.println("WELCOME TO ADD PRODUCT SECTION ");
				// take input from user to add a product
				System.out.println("Please enter product id :");
				productId = scanner.nextInt();

				if (productDAO.isProductExists(productId)) {
					System.out.println("Product with product id : " + productId
							+ " already exists, please try with another product id");
					continue;
				}
				System.out.println("Please enter product name :");
				productName = scanner.next();
				System.out.println("Please enter product qoh :");
				quantityOnHand = scanner.nextInt();
				System.out.println("Please enter product price :");
				price = scanner.nextInt();

				product = new Product(productId, productName, quantityOnHand, price);
				// call dao layer to save the product
				result = productDAO.addProduct(product);
				if (result)
					System.out.println("Congratulations, your product : " + productName + " saved successfully");
				else
					System.out.println("Sorry your product cannot be saved");
				break;

			case 2:
				System.out.println("Please enter product id to delete :");
				productId = scanner.nextInt();

				if (productDAO.isProductExists(productId)) {
					productDAO.deleteProduct(productId);
					System.out.println("Product with product id : " + productId + " deleted successfully");
				} else {
					System.out.println(
							"Product with product id : " + productId + " does not exists, hence cannot be deleted");

				}
				break;
			case 3:
				// update product section
				System.out.println("WELCOME TO UPDATE PRODUCT SECTION ");

				// take input from user to update a product
				System.out.println("Please enter product id to update :");
				productId = scanner.nextInt();

				if (!productDAO.isProductExists(productId)) {
					System.out.println(
							"Product with product id : " + productId + " does not exists, so cannot be updated");
					continue;
				}

				System.out.println("Please enter new product name :");
				productName = scanner.next();

				System.out.println("Please enter new product qoh :");
				quantityOnHand = scanner.nextInt();

				System.out.println("Please enter new product price :");
				price = scanner.nextInt();

				product = new Product(productId, productName, quantityOnHand, price);
				// call dao layer to save the product
				productDAO.updateProduct(product);
				System.out.println("Congratulations, your product : " + productName + " updated successfully");

				break;
			case 4:
				System.out.println("Please enter product id to search :");
				productId = scanner.nextInt();

				if (productDAO.isProductExists(productId)) {
					Product temp = productDAO.searchByProductId(productId);
					System.out.println(temp);
				} else {
					System.out.println(
							"Product with product id : " + productId + " does not exists, hence cannot be deleted");

				}
			case 5:
				System.out.println("Please enter product name to search :");
				productName = scanner.next();
				products = productDAO.searchByProductName(productName);
				if (products.size() == 0) {
					System.out.println("No products matching your criteria");
					continue;
				}
				printProductDetails(products);
				break;
			case 6:
				if (products.size() == 0) {
					System.out.println("No products");
					continue;
				}
				products = productDAO.getProducts();
				printProductDetails(products);

				break;
			case 7:
				System.out.println("Please enter product price (lower) :");
				int lowerprice = scanner.nextInt();
				System.out.println("Please enter product price (upper) :");
				int upperprice = scanner.nextInt();
				products = productDAO.searchProductByPrice(lowerprice, upperprice);
				if (products.size() == 0) {
					System.out.println("No products matching your criteria");
					continue;
				}
				printProductDetails(products);
				break;

			case 9:
				System.out.println("Thanks for using my product app");
				System.exit(0);
			default:
				System.out.println("Invalid Choice , Please enter (1-6) or 9 to EXIT");
				break;
			}
		}
	}

	public void printProductDetails(List<Product> products) {
		Iterator<Product> iterator = products.iterator();
		while (iterator.hasNext()) {
			Product temp = iterator.next();
			System.out.println(temp);
		}
	}
}
