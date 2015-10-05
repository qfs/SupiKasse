package com.supi.kasse.view;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.supi.kasse.MainApp;
import com.supi.kasse.model.Bill;
import com.supi.kasse.model.Product;
import com.supi.kasse.model.ProductDatabase;
import com.supi.kasse.model.Sum;
import com.thirdparty.MoneyTool;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

@RunWith(MockitoJUnitRunner.class)
public class MainViewControllerTest {

	@Mock MainApp mainApp;
	@Mock ProductDatabase productDatabase;
	@Mock MoneyTool moneyTool;

	Bill bill;
	Sum sum;
	Product product;

    Label sumLabel = new Label();
	TextField eanInput = new TextField();
	TableView<Product> billTable = new TableView<>();

	MainViewController controller;

	@BeforeClass
	public static void initFx() {
		InitFx.init();
	}


	@Before
	public void setup() {
		setupMocks();
		setupSystemUnderTest();
	}

	private void setupMocks() {
		product = new Product("12345", "Test-Object", 599);
		bill = Mockito.spy(new Bill());
		sum = Mockito.spy(new Sum());

		when(mainApp.getCurrentBill()).thenReturn(bill);
		when(mainApp.getCurrentSum()).thenReturn(sum);
		when(productDatabase.get("12345")).thenReturn(product);

		when(moneyTool.format(599)).thenReturn("$5.99");
		when(moneyTool.format(0)).thenReturn("$0.00");
	}

	private void setupSystemUnderTest() {
		controller = new MainViewController(mainApp, productDatabase, moneyTool);
		controller.eanInput = eanInput;
		controller.sumLabel = sumLabel;
		controller.billTable = billTable;
		controller.setBill(bill);
		controller.setSum(sum);
	}


	@Test
	public void testEanInputKeyReleasedEnterCallsAddProduct() throws Exception {
		final KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "", "\n", KeyCode.ENTER, false, false, false, false);

		controller = mock(MainViewController.class);
		Mockito.doCallRealMethod().when(controller).eanInputKeyReleased(keyEvent);

		controller.eanInputKeyReleased(keyEvent);

		verify(controller).addProduct();

	}


	@Test
	public void testEanInputKeyReleasedEnterDoesNotCallAddProductOnOtherKeys() throws Exception {
		final KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "A", "A", KeyCode.A, false, false, false, false);

		controller = mock(MainViewController.class);
		Mockito.doCallRealMethod().when(controller).eanInputKeyReleased(keyEvent);

		controller.eanInputKeyReleased(keyEvent);

		verify(controller, never()).addProduct();

	}


	@Test
	public void testAddProduct() throws Exception {

		eanInput.setText("12345");
		sumLabel.setText("");

		controller.addProduct();

		assertEquals("$5.99",sumLabel.getText());
		verify(bill).add(product);

		assertFalse(eanInput.getStyleClass().contains("error"));
	}


	@Test
	public void testAddProductEmptyInput() throws Exception {

		eanInput.setText("");
		sumLabel.setText("");

		controller.addProduct();

		verify(bill,never()).add(product);
		assertEquals("",sumLabel.getText());

		assertFalse(eanInput.getStyleClass().contains("error"));
	}


	@Test
	public void testAddProductInvalid() throws Exception {

		eanInput.setText("bla");
		sumLabel.setText("");

		controller.addProduct();

		verify(bill,never()).add(product);
		assertEquals("",sumLabel.getText());

		assertTrue(eanInput.getStyleClass().contains("error"));
	}


	@Test
	public void testBuy() throws Exception {
		controller.buy();

		verify(mainApp).resetState();
	}


	@Test
	public void testApplySaleToProduct() throws Exception {
		final Product product1 = productDatabase.get("12345");
		final Product product2 = productDatabase.get("12345");
		final Product product3 = productDatabase.get("12345");

		bill.add(product1);
		bill.add(product2);

		controller.applySaleToProduct(product3);

		assertEquals(0,product3.getPrice());
	}


}
