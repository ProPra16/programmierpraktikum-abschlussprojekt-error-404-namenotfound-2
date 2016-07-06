package propra.projekt7.controllers;
//by Julian
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import propra.projekt7.presets.PresetDataBase;

public class menucontroller {
	@FXML
	Button continuebutton;
	@FXML
	TextField xmlname,codename;
	
	@FXML
	private void goon(){
		PresetDataBase.codepreset=codename.getText();
		PresetDataBase.xmlpreset=xmlname.getText();
		Stage stage=(Stage) continuebutton.getScene().getWindow();
		stage.close();
	}
}
