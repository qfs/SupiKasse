package com.supi.kasse.view;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import com.supi.kasse.MainApp;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MainViewTest extends ApplicationTest {

	@Override
	public void start(final Stage stage) throws Exception {
		MainApp app = new MainApp();
		app.start(stage);
	}

	@Test
	public void testSimpleProductAdd() {
		//given
		clickOn("#ean_input");
		write("4000417304001").push(KeyCode.ENTER);

		//then
		verifyThat("#sum_text",  hasText("TLR 0,99"));
	}

	@Test
	public void testAction() {
		//given
		clickOn("#ean_input");
		write("4000417304001").push(KeyCode.ENTER);
		write("4000417304001").push(KeyCode.ENTER);
		write("4000417304001").push(KeyCode.ENTER);

		//then
		verifyThat("#sum_text",  hasText("TLR 1,98"));
	}


}
