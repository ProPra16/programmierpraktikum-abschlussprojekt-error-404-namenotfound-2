
//goback() and check() by Yulian, timemanager()by Julian and Jonas, rest by Julian
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Arc;
import java.util.Collection;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerFactory;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;



public class Controller {
	public void initialize(){
		Presetdeliverer.main();
		phase=1;
		babyclock=new BabystepClock();
		jte=new JavaToEditor(Presetdeliverer.classname);
		codefield.setText(jte.read());
		jte=new JavaToEditor(Presetdeliverer.testname);
		testcodefield.setText(jte.read());
		managephasegui(phase);
		timermanager();
	    

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
	                            timerlabel.setText("TIMER: "+babyclock.currenttime+"/"+babyclock.maxtime);
	                            if(babyclock.currenttime>=babyclock.maxtime-1){
	                            	backandcheck();
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
	
	public void backandcheck(){
		goback();
		check();
	}
	
	@FXML
	public void check(){
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
		
			switch(phase){ 
        		case 1: 
           			if (failedtests==1){
          				savetest();
           				phase=2;
           			}
            		break; 
        		case 2: 
           			if (failedtests==0){
          				savecode();
           				phase=3;
           			}
            		break; 
        		case 3: 
           			if (failedtests==0){
          				savecode();
           				phase=1;
           			} 
           			break; 
        		default: 
           			System.out.println("Fehler!");
           			break;
        	} 
		
		} else {
			CompilerResult output = compiler.getCompilerResult();
			Collection<CompileError> codeerrors = output.getCompilerErrorsForCompilationUnit(Code);
			if (codeerrors.size()!=0){
				String Fehlermeldung = codeerrors.toString();
				errorfield.setText(errorfield.getText()+"\n"+Fehlermeldung); 
				errorfield.setText(errorfield.getText()+"\n"+codeerrors.size());  
			}else{
				Collection<CompileError> testerrors = output.getCompilerErrorsForCompilationUnit(Test);
				if (testerrors.size()!=0){
					String Fehlermeldung = testerrors.toString();
					errorfield.setText(errorfield.getText()+"\n"+Fehlermeldung); 
					errorfield.setText(errorfield.getText()+"\n"+(codeerrors.size()));  

				}
			}
		}
		
		babyclock.reset();
		managephasegui(phase);				
		System.out.println("you just checked!");

	}
	
	@FXML
	public void goback(){
		if(phase == 2){
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

