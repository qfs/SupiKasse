package com.supi.kasse.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

@Data
public class Product implements Cloneable {
    final StringProperty eanProperty;
    final StringProperty nameProperty;
    final IntegerProperty priceProperty;

    public Product(final String ean) {
    	this (ean, null, 0);
    }

    public Product(final String ean, final String name, final int price) {
    	this.eanProperty = new SimpleStringProperty(ean);
        this.nameProperty = new SimpleStringProperty(name);
        this.priceProperty = new SimpleIntegerProperty(price);
    }

    public String getEan() {
    	return eanProperty.get();
    }

    public String getName() {
    	return nameProperty.get();
    }

    public void setName(final String name) {
    	nameProperty.set(name);
    }

    public int getPrice() {
    	return priceProperty.get();
    }

    public void setPrice(final int price) {
    	priceProperty.set(price);
    }

    public Product clone() {
    	return new Product(getEan(),getName(),getPrice());
    }
}
