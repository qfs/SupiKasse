package com.supi.kasse.view;

import org.junit.BeforeClass;

import javafx.application.Application;
import javafx.stage.Stage;

public class InitFx {

	static boolean initialized = false;

	//initialize fx...
	public static class AsNonApp extends Application {
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        // noop
	    }
	}

	public static void init() {
		if (initialized) {
			return;
		}
		initialized = true;

	    // Initialise Java FX

	    // About to launch FX App
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(AsNonApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	    try {
			Thread.sleep(200);
		} catch (final InterruptedException e) {
			System.err.println(e);
		}
	    // FX App thread started
	}
}
