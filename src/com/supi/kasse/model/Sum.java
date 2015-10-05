package com.supi.kasse.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import lombok.experimental.Delegate;

public class Sum implements ObservableValue<Number> {

	private interface ObservableValueOfNumber extends ObservableValue<Number> {};

	@Delegate(types=ObservableValueOfNumber.class)
	final IntegerProperty data = new SimpleIntegerProperty(0);

	public void add(final int price) {
	    int currentValue = data.getValue();
		currentValue += price;
		data.set(currentValue);
	}



}
