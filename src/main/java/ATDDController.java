import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vk.core.api.*;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Julian,Martin,Michael
 */
public class ATDDController {
	@FXML
	public TextArea ATDDtestcodefield,terminalfield,writtencodearea;

    @FXML
    public Button atddcheckbutton;

    public static String codefieldText;
    public static boolean atddfirstcheck;


    /**
     * Laedt den Inhalt der Textfelder wenn ein ATDD Fenster aufgerufen wird.
     */
    public void initialize(){
        ATDDtestcodefield.setText(new JavaToEditor("./src/main/resources/txt/"+"ATDD"+Presetdeliverer.testname).read());
        terminalfield.setText("");
        writtencodearea.setText(new JavaToEditor(Presetdeliverer.classname).read());
    }

    /**
     * Das ATDD Programm muss den Code kennen zu dem es testen soll. Daher wird mit giveCodeText
     * der Code vom Controller an den ATDDControler uebergeben.
     * @param codefield
     * @param firstcheck
     *
     */
    public static void giveCodeText(String codefield){
        codefieldText = codefield;
        
    }

    /**
     * Eine einfache Methode, die den aktuellen Text aus dem Textfeld speichert.
     */
    public void saveATDD(){
        EditorToJava etj = new EditorToJava("./src/main/resources/txt/"+"ATDD"+Presetdeliverer.testname);
        etj.save(ATDDtestcodefield.getText());
    }

    /**
     *     Die Methode fuer den Checkbutton. Es wird geprueft wie viele Tests laufen. Nur wenn genau 1 Test fehlschlaegt soll
     *     der Testcode gespeichert und das ATDD Fenster geschlossen werden.
     */


@FXML
public void check(){
	ATDDFailedTests(codefieldText,ATDDtestcodefield.getText());
	if(PresetDataBase.atddfirstcheck){
		saveATDD();
        Stage stage=(Stage) atddcheckbutton.getScene().getWindow();
        stage.close();
	}
	
	if(ATDDFailedTests(codefieldText,ATDDtestcodefield.getText())==1){
        saveATDD();
        Stage stage=(Stage) atddcheckbutton.getScene().getWindow();
        stage.close();
    }
}

    /**
     * Inspired by check() aus dem Controller
     * Die Methode kompiliert den Code und die Tests. Gibt es dabei Fehler werden diese in dem Terminal ausgegeben.
     * Außerdem wird eine -1 returned, damit in der Check Methode das Fenster nicht geschlossen wird.
     * Kompilieret Beides, so werden die Tests durchgefuehrt und es wird die Anzahl der fehlgeschlagenen Tests returned.
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
        Collection<CompileError> codeerrors2 = compileresults.getCompilerErrorsForCompilationUnit(Test);
		
		CompileError compileerrors2;
		PresetDataBase.atddfirstcheck=false;
		if(codeerrors2.size()==1){
			for (Iterator<CompileError> iterator4 = codeerrors2.iterator(); iterator4.hasNext(); ){
				compileerrors2 = iterator4.next();
				String Message = compileerrors2.getMessage();
				if (Message.contains("cannot find symbol")){
					PresetDataBase.atddfirstcheck=true;
				}
			}
		}
        
        
        
        if (compileresults.hasCompileErrors()==false) {
            TestResult testresults = compiler.getTestResult();

            int failedtests = testresults.getNumberOfFailedTests();
            terminalfield.setText(terminalfield.getText()+"\n"+"Anzahl Fehlgeschlagener Tests: "+ failedtests);
            terminalfield.appendText("");
            Collection<TestFailure> Fails = testresults.getTestFailures();
            TestFailure Failure;
            for (Iterator<TestFailure> iterator1 = Fails.iterator(); iterator1.hasNext(); ){
                Failure = iterator1.next();
                String Message = Failure.getMessage();
                String Methodname = Failure.getMethodName();
                terminalfield.setText(terminalfield.getText()+"\n"+"Testmethode: "+Methodname+"\n"+Message+"\n");
                terminalfield.appendText("");
            }

            return failedtests;
        }
        else {
            CompilerResult output = compiler.getCompilerResult();
            Collection<CompileError> codeerrors = output.getCompilerErrorsForCompilationUnit(Code);
            if (codeerrors.size() != 0) {
                String Fehlermeldung = codeerrors.toString();
                terminalfield.setText(terminalfield.getText() + "\n" + Fehlermeldung);
                terminalfield.setText(terminalfield.getText() + "\n" + codeerrors.size());
                terminalfield.appendText("");
            } else {
                Collection<CompileError> testerrors = output.getCompilerErrorsForCompilationUnit(Test);
                if (testerrors.size() != 0) {
                    String Fehlermeldung = testerrors.toString();
                    terminalfield.setText(terminalfield.getText() + "\n" + Fehlermeldung);
                    terminalfield.setText(terminalfield.getText() + "\n" + (codeerrors.size()));
                    terminalfield.appendText("");

                }
            }
            return -1;
        }

    }


}
