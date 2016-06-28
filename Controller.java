

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
		BabystepClock babyclock=new BabystepClock();
		timerlabel=babyclock.timelabel;
	}

private static int phase;
@FXML
private Button checkbutton;
@FXML
private Button backbutton;
@FXML 
public Arc green;
@FXML 
public Arc blue;
@FXML 
public Arc red;
@FXML
public Label greentext;
@FXML
public Label redtext;
@FXML
public Label bluetext;
@FXML
public Label timerlabel;

public void checkandback(){
	check();
	goback();
}

@FXML
public void check(){
	babyclock.reset();
	managephasegui(phase);				
	System.out.println("you just checked!");
	
}

@FXML
public void goback(){
	managephasegui(phase);
	System.out.println("you just went back!");
	babyclock.reset();
}

private void managephasegui(int phase){//benutzung: managephasegui(aktuelle Phase)
	if (phase==1){
		babyclock.restart;
		green.setVisible(true);
		red.setVisible(false);
		blue.setVisible(false);
		greentext.setVisible(true);
		redtext.setVisible(false);
		bluetext.setVisible(false);
		backbutton.setDisable(false);
	}
	else if(phase==2){
		green.setVisible(false);
		red.setVisible(true);
		blue.setVisible(false);
		greentext.setVisible(false);
		redtext.setVisible(true);
		bluetext.setVisible(false);
		backbutton.setDisable(false);
	}
	else if(phase==3){
		babyclock.stop();
		green.setVisible(false);
		red.setVisible(false);
		blue.setVisible(true);
		greentext.setVisible(false);
		redtext.setVisible(false);
		bluetext.setVisible(true);
		backbutton.setDisable(true);
	}
}
}

