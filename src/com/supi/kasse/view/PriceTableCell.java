package com.supi.kasse.view;

import com.supi.kasse.model.Product;
import com.thirdparty.MoneyTool;

import javafx.scene.control.TableCell;

public class PriceTableCell extends TableCell <Product, Integer> {

	final MoneyTool moneyTool;

	public PriceTableCell(final MoneyTool moneyTool) {
		super();
		this.moneyTool = moneyTool;
	}

	@Override
	protected void updateItem(final Integer price, final boolean empty) {
		super.updateItem(price, empty);
		if (price == null || empty) {
			setText("");
		} else {
			String formattedPrice = moneyTool.format(price);
			setText(formattedPrice);
		}
	}
};
