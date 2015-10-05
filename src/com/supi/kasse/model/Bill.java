package com.supi.kasse.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.experimental.Delegate;

public class Bill implements ObservableList<Product> {

	@Delegate
	final ObservableList<Product> data = FXCollections.observableArrayList();

}
