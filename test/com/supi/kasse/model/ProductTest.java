package com.supi.kasse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	Product product;

	@Before
	public void setupSystemUnderTest() {
		product = new Product("1234","Test-Product",123);
	}

	@Test
	public void testGetEan() throws Exception {
		assertEquals("1234",product.getEan());
	}

	@Test
	public void testGetEanProperty() throws Exception {
		assertEquals("1234",product.getEanProperty().getValue());
	}

	@Test
	public void testGetName() throws Exception {
		assertEquals("Test-Product",product.getName());
	}

	@Test
	public void testGetNameProperty() throws Exception {
		assertEquals("Test-Product",product.getNameProperty().getValue());
	}

	@Test
	public void testSetName() throws Exception {
		product.setName("Bla");
		assertEquals("Bla",product.getName());
	}

	@Test
	public void testGetPrice() throws Exception {
		assertEquals(123,product.getPrice());
	}

	@Test
	public void testGetPriceProperty() throws Exception {
		assertEquals(123,product.getPriceProperty().getValue().intValue());
	}

	@Test
	public void testSetPrice() throws Exception {
		product.setPrice(567);
		assertEquals(567,product.getPrice());
	}


	@Test
	public void testEmptyConstructor() throws Exception {
		product = new Product("5678");
		assertEquals("5678",product.getEan());
		assertNull(product.getName());
		assertNotNull(product.getNameProperty());
		assertEquals(0,product.getPrice());
		assertNotNull(product.getPriceProperty());

	}


}
