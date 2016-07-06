

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mastermain extends Application {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
		
		
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("fxml/GUI.fxml"));
		primaryStage.setTitle("Program Stepper");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
	}
	
}
