

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * Main-Methode zum starten des Programms
 * @author Julian Biston
 * Alle GUI (fxml) Elemente/Dateien by Julian Biston
 *
 */
public class Mastermain extends Application {
	
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
