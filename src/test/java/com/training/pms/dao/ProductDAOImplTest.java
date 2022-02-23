package com.training.pms.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductDAOImplTest {

	ProductDAO productDAO;
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		productDAO = new ProductDAOImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Testing product exists-1")
	void testIsProductExists1() {
		int productId=4;
		boolean actual = productDAO.isProductExists(productId);
		assertTrue(actual);
	}

	@Test
	@DisplayName("Testing product exists-2")
	void testIsProductExists2() {
		int productId=2;
		boolean actual = productDAO.isProductExists(productId);
		assertTrue(actual);
	}
	@Test
	@DisplayName("Testing product exists-3")
	void testIsProductExists4() {
		int productId=20827;
		boolean actual = productDAO.isProductExists(productId);
		assertFalse(actual);
	}

}
