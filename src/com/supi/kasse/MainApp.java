package com.supi.kasse;

import java.io.IOException;

import com.supi.kasse.model.Bill;
import com.supi.kasse.model.ProductDatabase;
import com.supi.kasse.model.Sum;
import com.supi.kasse.view.MainViewController;
import com.thirdparty.MoneyTool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;

public class MainApp extends Application {

	Stage primaryStage;
	BorderPane rootLayout;

	@Getter ProductDatabase productDatabase;
	@Getter Bill currentBill;
	@Getter Sum currentSum;

	MainViewController mainViewController;

	@Override
	public void start(final Stage primaryStage) {
		loadProducts();

		setupPrimaryStage(primaryStage);
		initRootLayout(primaryStage);
		loadMainView(primaryStage);

		resetState();
	}

    void loadProducts() {
		this.productDatabase = new ProductDatabase();
	}

	void setupPrimaryStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("SupiKasse");
	}

	void initRootLayout(final Stage primaryStage) {
		try {
            // Load root layout from fxml file.
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            final Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final IOException e) {
            e.printStackTrace();
        }
	}

	void loadMainView(final Stage primaryStage) {
        try {
        	// init controller
        	final MoneyTool moneyTool = new MoneyTool();

            // Load person overview.
            final FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(paramClass ->
            	{return new MainViewController(this, productDatabase, moneyTool);});

            loader.setLocation(MainApp.class.getResource("view/MainView.fxml"));
            final AnchorPane personOverview = (AnchorPane) loader.load();

			mainViewController = loader.getController();

            // Set main view into the center of root layout.
            rootLayout.setCenter(personOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void resetState() {
		currentBill = new Bill();

		mainViewController.setBill(currentBill);

		currentSum = new Sum();
		mainViewController.setSum(currentSum);
	}

	public static void main(final String... args) {
		launch(args);
	}
}
