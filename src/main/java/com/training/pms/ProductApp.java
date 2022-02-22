package com.training.pms;

import java.io.IOException;
import java.util.Scanner;
import com.training.pms.dao.ProductDAO;
import com.training.pms.dao.ProductDAOImpl;
import com.training.pms.model.Product;

public class ProductApp {
	Scanner scanner = new Scanner(System.in);
	int choice = 0;
	//Animal a = new Cat();
	ProductDAO productDAO = new ProductDAOImpl();
	Product product = new Product();
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

				System.out.println("Please enter product name :");
				productName = scanner.next();

				System.out.println("Please enter product qoh :");
				quantityOnHand = scanner.nextInt();

				System.out.println("Please enter product price :");
				price = scanner.nextInt();

				product = new Product(productId, productName, quantityOnHand, price);
				// call dao layer to save the product
				productDAO.addProduct(product);
				System.out.println("Congratulations, your product : " + productName + " saved successfully");

				break;
			case 3:
				// update product section
				System.out.println("WELCOME TO UPDATE PRODUCT SECTION ");

				// take input from user to update a product
				System.out.println("Please enter product id to update :");
				productId = scanner.nextInt();

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
				
			case 5:
				System.out.println("Please enter product name to search :");
				productName = scanner.next();
				productDAO.searchByProductName(productName);
				break;
			case 6:
					productDAO.printAllProducts();
					break;
			case 7:
				System.out.println("Please enter product price (lower) :");
				int lowerprice = scanner.nextInt();
				System.out.println("Please enter product price (upper) :");
				int upperprice = scanner.nextInt();
				productDAO.searchProductByPrice(lowerprice, upperprice);
				break;	
					
			case 9: System.out.println("Thanks for using my product app");
					System.exit(0);
			default:
				System.out.println("Invalid Choice , Please enter (1-6) or 9 to EXIT");
				break;
			}
		}
	}
}
