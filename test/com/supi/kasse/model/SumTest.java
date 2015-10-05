package com.supi.kasse.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SumTest {

	@Test
	public void testAdd() throws Exception {
		Sum sum = new Sum();

		sum.add(35);

		assertEquals(35, sum.getValue().intValue());
	}

}
