

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.text.Text;

public class Controller {
	void initialize(){
		phase=1;
	}

private int phase;
@FXML
private Button checkbutton;
@FXML
private Button backbutton;
@FXML 
public Arc green;

@FXML
public void check(Event event){
	System.out.println("you just checked!");
	green.setVisible(true);
}

@FXML
public void goback(Event event){
	System.out.println("you just went back!");
	green.setVisible(false);
}
}
/*
 * initialize:  file to text
 * */
