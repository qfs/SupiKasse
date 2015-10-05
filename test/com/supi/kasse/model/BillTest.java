package com.supi.kasse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BillTest {

	Bill bill;

	@Before
	public void setupSystemUnderTest() {
		bill = new Bill();
	}

	@Test
	public void testInit() throws Exception {
		assertEquals(0, bill.size());
	}

	@Test
	public void testAdd() throws Exception {
		final Product product = new Product("1234","Test",12321);
		bill.add(product);

		assertEquals(1, bill.size());
		assertEquals(product,bill.get(0));
	}
}
