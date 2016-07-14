
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import vk.core.api.*;

/**
 * Controller zum steuern der GUI.fxml
 * @author Julian Biston, parts: goback() by Yulian, check() by Michael ATDD-modified by Martin , timemanager() by Jonas and Julian
 *
 */

public class Controller {
	public void initialize() throws IOException{
		//
		firstcheck = true;
		secondcheck = true;

		Parent root=(Parent) FXMLLoader.load(getClass().getResource("fxml/menuGUI.fxml"));
		Stage menustage=new Stage();
		menustage.setTitle("Menu");
		menustage.setScene(new Scene(root));
		menustage.showAndWait();
		Presetdeliverer.main();

		if(PresetDataBase.atdd) phase=3;
		else phase=1;

		babyclock=new BabystepClock();
		jte=new JavaToEditor("./src/main/resources/txt/"+Presetdeliverer.classname);
		codefield.setText(jte.read());
		jte=new JavaToEditor("./src/main/resources/txt/"+Presetdeliverer.testname);
		testcodefield.setText(jte.read());
		managephasegui(phase);
		timermanager();
		savecode();
		savetest();
		if(PresetDataBase.atdd) check();
	    

	}

	public void timermanager(){
        Thread time = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                	if(babyclock.running){
	                    try {
	                        Thread.sleep(1000);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    Platform.runLater(new Runnable(){
	                    	@Override public void run(){
	                            timerlabel.setText("Babysteptimer: "+(babyclock.currenttime+1)+"/"+babyclock.maxtime);
	                            if(babyclock.currenttime>=babyclock.maxtime-1 && PresetDataBase.babysteps){
	                            	backandcheck();
	                            	errorfield.setText("");
	                            }
	                    	}
	                    });
                	}
                }
            }
        });
        time.start();
    }
	EditorToJava etj;
	JavaToEditor jte;
	BabystepClock babyclock;
	boolean firstcheck;
	boolean secondcheck;
	
	private static int phase;
	
	@FXML
	private Button checkbutton,backbutton;
	
	@FXML 
	public Arc green,blue,red;
	
	@FXML
	public Label greentext,redtext,bluetext,testlabel,codelabel;
	
	@FXML
	volatile Label timerlabel;
	
	@FXML
	public TextArea codefield,testcodefield,errorfield;
	
	@FXML
	public MenuItem closebutton;
	
	@FXML
	public void exit(){
		System.exit(0);
	}
	
	public void savetest(){
		etj=new EditorToJava(Presetdeliverer.testname);
		etj.save(testcodefield.getText());
	}
	
	public void savecode(){
		etj=new EditorToJava(Presetdeliverer.classname);
		etj.save(codefield.getText());
	}


	/**
	 *
	 * @throws IOException
     */
	@FXML
	public void check() throws IOException{
		if(firstcheck){
			savetest();
			phase = 2;
			managephasegui(phase);
			firstcheck = false;
		}
		String codecontent = codefield.getText();
		CompilationUnit Code = new CompilationUnit(Presetdeliverer.classname, codecontent, false);
	
		String testcontent = testcodefield.getText();
		CompilationUnit Test = new CompilationUnit(Presetdeliverer.testname, testcontent, true);
		
		JavaStringCompiler compiler = CompilerFactory.getCompiler(Code, Test);
		
		compiler.compileAndRunTests();
		CompilerResult compileresults= compiler.getCompilerResult();
		if (compileresults.hasCompileErrors()==false){
			TestResult testresults = compiler.getTestResult();
		
			int failedtests = testresults.getNumberOfFailedTests();

			errorfield.setText(errorfield.getText()+"\n"+"Anzahl Fehlgeschlagener Tests: "+ failedtests);
			errorfield.appendText("");

			Collection<TestFailure> Fails = testresults.getTestFailures();
			TestFailure Failure;
			for (Iterator<TestFailure> iterator1 = Fails.iterator(); iterator1.hasNext(); ){
				Failure = iterator1.next();
				String Message = Failure.getMessage();
				String Methodname = Failure.getMethodName();
				errorfield.setText(errorfield.getText()+"\n"+"Testmethode: "+Methodname+"\n"+Message+"\n");
				errorfield.appendText("");
			}
		
			switch(phase){ 
        		case 1: 
           			if (failedtests==1){
          				savetest();
           				phase=2;
           				babyclock.reset();
           				managephasegui(phase);
						errorfield.setText("");
           			}
            		break; 
        		case 2: 
           			if (failedtests==0){
          				savecode();
           				phase=3;
           				babyclock.reset();
           				managephasegui(phase);
						errorfield.setText("");
						secondcheck = false;
					}
            		break; 
        		case 3:
					if (failedtests==0) {
						if (PresetDataBase.atdd && ATDDFailedTests(codefield.getText(), new JavaToEditor("./src/main/resources/txt/" + "ATDD" + Presetdeliverer.testname).read()) == 0) {
							savecode();
							savetest();
							babyclock.reset();
							managephasegui(phase);
							Parent root = (Parent) FXMLLoader.load(getClass().getResource("fxml/ATDDGUI.fxml"));
							ATDDController.giveCodeText(codefield.getText());
							Stage menustage = new Stage();
							menustage.setTitle("ATDD");
							menustage.setScene(new Scene(root));
							menustage.showAndWait();
							babyclock.reset();
							phase = 1;
							managephasegui(phase);
						}
						else {

							savecode();
							savetest();
							phase = 1;
							babyclock.reset();
							managephasegui(phase);
							errorfield.setText("");
						}
					}
					break;
						
        			default:
           			break;
        	} 
		
		} else {
			CompilerResult output = compiler.getCompilerResult();
			Collection<CompileError> codeerrors = output.getCompilerErrorsForCompilationUnit(Code);
			if (codeerrors.size()!=0){
				CompileError compileerrors;
				for (Iterator<CompileError> iterator2 = codeerrors.iterator(); iterator2.hasNext(); ){
					compileerrors = iterator2.next();
					String Message = compileerrors.getMessage();
					String Wrongcode = compileerrors.getCodeLineContainingTheError();
					long errorline = compileerrors.getLineNumber();
					errorfield.setText(errorfield.getText()+"\n"+"Fehler in Zeile "+errorline+"\n"+Wrongcode+"\n"+Message+"\n");
					errorfield.appendText("");
				}
			}else{
				Collection<CompileError> testerrors = output.getCompilerErrorsForCompilationUnit(Test);
				if (testerrors.size()!=0){
					
					CompileError compileerrors;
					for (Iterator<CompileError> iterator3 = testerrors.iterator(); iterator3.hasNext(); ){
						compileerrors = iterator3.next();
						String Message = compileerrors.getMessage();
						String Wrongcode = compileerrors.getCodeLineContainingTheError();
						long errorline = compileerrors.getLineNumber();
						errorfield.setText(errorfield.getText()+"\n"+"Fehler in Zeile "+errorline+"\n"+Wrongcode+"\n"+Message+"\n");
						errorfield.appendText("");
					}
				}
			}
		}

	}

	/**
	 *
	 * @param codecontent
	 * @param testcontent
     * @return
     */
	public int ATDDFailedTests(String codecontent, String testcontent){
		CompilationUnit Code = new CompilationUnit(Presetdeliverer.classname, codecontent, false);

		CompilationUnit Test = new CompilationUnit("ATDD"+Presetdeliverer.testname, testcontent, true);

		JavaStringCompiler compiler = CompilerFactory.getCompiler(Code, Test);

		compiler.compileAndRunTests();
		CompilerResult compileresults= compiler.getCompilerResult();
		if (compileresults.hasCompileErrors()==false) {
			TestResult testresults = compiler.getTestResult();

			int failedtests = testresults.getNumberOfFailedTests();

			return failedtests;
		}
		else {
			CompilerResult output = compiler.getCompilerResult();
			Collection<CompileError> codeerrors = output.getCompilerErrorsForCompilationUnit(Code);
			if (codeerrors.size() != 0) {
				String Fehlermeldung = codeerrors.toString();
				errorfield.setText(errorfield.getText()+"\n"+Fehlermeldung);
				errorfield.setText(errorfield.getText()+"\n"+codeerrors.size());
				errorfield.appendText("");
			}else{
				Collection<CompileError> testerrors = output.getCompilerErrorsForCompilationUnit(Test);
				if (testerrors.size()!=0){
					String Fehlermeldung = testerrors.toString();
					errorfield.setText(errorfield.getText()+"\n"+Fehlermeldung);
					errorfield.setText(errorfield.getText()+"\n"+(codeerrors.size()));
					errorfield.appendText("");

				}
			}
			return -1;
		}

	}






	public void  backandcheck(){
		testcodefield.setText(new JavaToEditor(Presetdeliverer.testname).read());
		codefield.setText(new JavaToEditor(Presetdeliverer.classname).read());
		babyclock.reset();
	}
	
	@FXML
	public void goback(){
		if(phase == 2){
			if(secondcheck){
				firstcheck = true;
			}
			codefield.setText(new JavaToEditor(Presetdeliverer.classname).read());
			phase = 1;
			
			babyclock.reset();
			managephasegui(phase);
		}
		else if(phase == 3){
			codefield.setText(new JavaToEditor(Presetdeliverer.classname).read());
		}
	}
	
	private void managephasegui(int phase){//benutzung: managephasegui(aktuelle Phase)
		if (phase==1){
			babyclock.restart();
			green.setVisible(true);
			red.setVisible(false);
			blue.setVisible(false);
			greentext.setVisible(true);
			redtext.setVisible(false);
			bluetext.setVisible(false);
			backbutton.setDisable(false);
			codefield.setDisable(true);
			testcodefield.setDisable(false);
			codelabel.setDisable(true);
			testlabel.setDisable(false);

		}
		else if(phase==2){
			green.setVisible(false);
			red.setVisible(true);
			blue.setVisible(false);
			greentext.setVisible(false);
			redtext.setVisible(true);
			bluetext.setVisible(false);
			backbutton.setDisable(false);
			codefield.setDisable(false);
			testcodefield.setDisable(true);
			codelabel.setDisable(false);
			testlabel.setDisable(true);

		}
		else if(phase==3){
			babyclock.stop();
			green.setVisible(false);
			red.setVisible(false);
			blue.setVisible(true);
			greentext.setVisible(false);
			redtext.setVisible(false);
			bluetext.setVisible(true);
			backbutton.setDisable(false);
			codefield.setDisable(false);
			testcodefield.setDisable(false);
			codelabel.setDisable(false);
			testlabel.setDisable(false);

		}
	}
}

