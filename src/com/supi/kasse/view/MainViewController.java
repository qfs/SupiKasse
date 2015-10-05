package com.supi.kasse.view;

import com.supi.kasse.MainApp;
import com.supi.kasse.model.Bill;
import com.supi.kasse.model.Product;
import com.supi.kasse.model.ProductDatabase;
import com.supi.kasse.model.Sum;
import com.thirdparty.MoneyTool;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainViewController {
	@FXML TableView<Product> billTable;
	@FXML TableColumn<Product, String> nameColumn;
	@FXML TableColumn<Product, Integer> priceColumn;
	@FXML Label sumLabel;
	@FXML TextField eanInput;

	final MainApp mainApp;
	final ProductDatabase productDatabase;

	final MoneyTool moneyTool;

	public MainViewController(final MainApp mainApp,
				final ProductDatabase productDatabase,
				final MoneyTool moneyTool) {
		super();
		this.mainApp = mainApp;
		this.productDatabase = productDatabase;
		this.moneyTool = moneyTool;
	}


	@FXML
	void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
		priceColumn.setCellFactory(column ->  { return new PriceTableCell(moneyTool);} );
	}


	public void setBill(final Bill currentBill) {
		billTable.setItems(currentBill);
	}

	public void setSum(final Sum currentSum) {
		updateSum();
	}

	@FXML
	void eanInputKeyReleased(final KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			addProduct();
		}
	}

	void addProduct() {
		assert productDatabase != null;

		eanInput.getStyleClass().remove("error");
		final String enteredEan = eanInput.getText();
		if ("".equals(enteredEan)) {
			return;
		}

		final Product product = productDatabase.get(enteredEan);

		if (product != null) {
			applySaleToProduct(product);
			addProductToBill(product);
			eanInput.setText("");
		} else {
			eanInput.selectAll();
			eanInput.getStyleClass().add("error");
		}
		focusEanInput();
	}


	void applySaleToProduct(Product product) {
		int count = 0;
		for (Product otherProduct: mainApp.getCurrentBill()) {
			if (otherProduct.getEan().equals(product.getEan())) {
				count++;
			}
		}

		if (count % 3 == 2) {
			product.setPrice(0); // Free!
		}
	}


	void addProductToBill(final Product product) {
		assert product != null;

		mainApp.getCurrentBill().add(product);
		mainApp.getCurrentSum().add(product.getPrice());

		updateDisplay();
	}

	private void updateDisplay() {
		billTable.scrollTo(mainApp.getCurrentBill().size()-1);
		updateSum();
	}

	private void updateSum() {
		final Number currentSum = mainApp.getCurrentSum().getValue();
		String formattedSum = moneyTool.format(currentSum.intValue());
		sumLabel.setText(formattedSum);
	}

	@FXML
	void buy() {
		mainApp.resetState();
		focusEanInput();
	}

	@FXML
	void focusEanInput() {
		eanInput.requestFocus();
	}
}
