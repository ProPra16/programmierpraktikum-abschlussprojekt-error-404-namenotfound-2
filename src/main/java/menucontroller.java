//by Julian
import java.io.File;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class menucontroller {
	@FXML
	Button continuebutton;
	@FXML
	TextField xmlname,codename;
	
	File codefile;
	File presetfile;
	
	@FXML
	private void goon(){
		PresetDataBase.codepreset=codefile.getName();
		PresetDataBase.xmlpreset=presetfile.getName();
		Stage stage=(Stage) continuebutton.getScene().getWindow();
		stage.close();
		System.out.println(PresetDataBase.codepreset);
	}
	
	
    @FXML
    public void loadcodefile(){
    	FileChooser codefilechooser = new FileChooser();
    	codefilechooser.setTitle("Choose Codefile");
    	codefile =codefilechooser.showOpenDialog((Stage)continuebutton.getScene().getWindow());
    	PresetDataBase.codefilepath=codefile.getAbsolutePath();
    }
    
    @FXML
    public void loadpresetfile(){
    	FileChooser codefilechooser = new FileChooser();
    	codefilechooser.setTitle("Choose Presetfile");
    	presetfile =codefilechooser.showOpenDialog((Stage)continuebutton.getScene().getWindow());
    	PresetDataBase.presetpath=presetfile.getAbsolutePath();
    }
}
