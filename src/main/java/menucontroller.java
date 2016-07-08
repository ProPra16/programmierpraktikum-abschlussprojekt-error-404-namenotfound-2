//by Julian
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class menucontroller {
	@FXML
	Button continuebutton;
	@FXML
	TextField xmlname,codename,preclassname,pretestname,premins,presecs;
	@FXML
	CheckBox prebaby,preatdd;
	
	boolean own=false;
	File codefile;
	File presetfile;
	
	
	@FXML 
	private void createpreset(){
		 File preset = new File("./src/main/resources/txt/createdpreset.txt");
	     Path p = Paths.get(preset.getAbsolutePath());
	     String presetcode=new String("<class name>"+preclassname.getText()+"\n<test name>"+pretestname.getText()+"\n<babysteps value>"+prebaby.isSelected()+" #"+premins.getText()+":"+presecs.getText()+"\n<atdd value>"+preatdd.isSelected());
	     own=true;
	     PresetDataBase.presetpath="./src/main/resources/txt/createdpreset.txt";
	     if(PresetDataBase.codefilepath!=null && PresetDataBase.presetpath!=null){
	    		continuebutton.setDisable(false);
	    }
	     
	     try {
	     FileWriter writer = new FileWriter(preset);
	     	writer.write(presetcode);
	     	writer.flush();
	     	writer.close();
	     }
	     catch(IOException e){}

	    }
	
	
	@FXML
	public void goon(){
		PresetDataBase.codepreset=codefile.getName();
		if(own==false){
			PresetDataBase.xmlpreset=presetfile.getName();
		
		}
		else if(own==true){
			PresetDataBase.xmlpreset="createdpreset.txt";
		}
		Stage stage=(Stage) continuebutton.getScene().getWindow();
		stage.close();
		
	}
	
	
    @FXML
    public void loadcodefile(){
    	FileChooser codefilechooser = new FileChooser();
    	codefilechooser.setTitle("Choose Codefile");
    	codefile =codefilechooser.showOpenDialog((Stage)continuebutton.getScene().getWindow());
    	PresetDataBase.codefilepath=codefile.getAbsolutePath();
    	if(PresetDataBase.codefilepath!=null && (own==true ||PresetDataBase.presetpath!=null)){
    		continuebutton.setDisable(false);
    	}
    	
    }
    
    @FXML
    public void loadpresetfile(){
    	FileChooser codefilechooser = new FileChooser();
    	codefilechooser.setTitle("Choose Presetfile");
    	presetfile =codefilechooser.showOpenDialog((Stage)continuebutton.getScene().getWindow());
    	PresetDataBase.presetpath=presetfile.getAbsolutePath();
    	own=false;
    	if(PresetDataBase.codefilepath!=null && (own==true ||PresetDataBase.presetpath!=null)){
    		continuebutton.setDisable(false);
    	}
    }
}
