package com.supi.kasse.model;

import java.util.HashMap;
import java.util.Map;

import lombok.experimental.Delegate;

public class ProductDatabase implements Map<String,Product> {

	@Delegate
	Map<String,Product> data = new HashMap<String,Product>();

	public ProductDatabase() {
		initProducts();
	}

	void initProducts() {
		addProduct("4000417023001","RS Mandel",99);
		addProduct("4000417304001","RS Honig-Salz",99);
		addProduct("4000417280008","RS Olympia",99);
		addProduct("4000417011008","RS Knusper",99);
		addProduct("20140427","Delik Leberwurst",129);
		addProduct("8711600854703","Dusch Das Fresh",199);
		addProduct("20358549","W5 Geschirr-Tabs",99);
		addProduct("4043619824243","DVI Cable",1499);
	}

	void addProduct(final String ean, final String name, final int price) {
		final Product newProduct = new Product(ean, name, price);
		data.put(ean,newProduct);
	}

	public Product get(final String ean) {
		final Product product = data.get(ean);
		if (product == null) {
			return product;
		}
		return product.clone();
	}



}
