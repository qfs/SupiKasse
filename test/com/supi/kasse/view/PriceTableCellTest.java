package com.supi.kasse.view;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thirdparty.MoneyTool;

import javafx.application.Application;
import javafx.stage.Stage;

@RunWith(MockitoJUnitRunner.class)
public class PriceTableCellTest {

	@Mock MoneyTool moneyTool;
	PriceTableCell cell;


	@BeforeClass
	public static void initFx() {
		InitFx.init();
	}

	@Before
	public void setupSystemUnderTest() {
		cell = new PriceTableCell(moneyTool);
	}

	@Test
	public void testUpdateItem() throws Exception {
		when(moneyTool.format(12)).thenReturn("$0.12");

		cell.updateItem(12,false);
		assertEquals("$0.12", cell.getText());
	}

}
