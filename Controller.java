

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
	}

private int phase;
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

public void checkandback(){
	check();
	goback();
}

@FXML
public void check(){
	babyclock.reset();
	managephasegui(phase-1);				//je nachdem ob phase erst um 1 verringert wird und dann goback(); aufgerufen wird oder andersherum!! genauso wie bei check();
	System.out.println("you just checked!");
	
}

@FXML
public void goback(){
	managephasegui(phase+1);
	System.out.println("you just went back!");
}

private void managephasegui(int phase){//benutzung: managephasegui(aktuelle Phase)
	if (phase==1){
		green.setVisible(true);
		red.setVisible(false);
		blue.setVisible(false);
		greentext.setVisible(true);
		redtext.setVisible(false);
		bluetext.setVisible(false);
	}
	else if(phase==2){
		green.setVisible(false);
		red.setVisible(true);
		blue.setVisible(false);
		greentext.setVisible(false);
		redtext.setVisible(true);
		bluetext.setVisible(false);
	}
	else{
		green.setVisible(false);
		red.setVisible(false);
		blue.setVisible(true);
		greentext.setVisible(false);
		redtext.setVisible(false);
		bluetext.setVisible(true);
	}
}
}

