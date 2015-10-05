package com.supi.kasse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductDatabaseTest {

	ProductDatabase db;

	@Before
	public void setupSystemUnderTest() {
		db = new ProductDatabase();
	}

	@Test
	public void testGetProduct() throws Exception {
		final Product product = db.get("20140427");
		assertEquals("Delik Leberwurst",product.getName());
	}

}
