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

public class ATDDController {
	@FXML
	public TextArea ATDDtestcodefield,terminalfield;

    @FXML
    public Button atddcheckbutton;

    public static String codefieldText;

    
    
    public void initialize(){
        ATDDtestcodefield.setText(new JavaToEditor("./src/main/resources/txt/"+"ATDD"+Presetdeliverer.testname).read());
        terminalfield.setText("");
    }

    
    public static void giveCodeText(String codefield){
        codefieldText = codefield;
    }

    public void saveATDD(){
        EditorToJava etj = new EditorToJava("./src/main/resources/txt/"+"ATDD"+Presetdeliverer.testname);
        etj.save(ATDDtestcodefield.getText());
    }
@FXML
public void check(){
	if(ATDDFailedTests(codefieldText,ATDDtestcodefield.getText())==1){
        saveATDD();
        Stage stage=(Stage) atddcheckbutton.getScene().getWindow();
        stage.close();
    }
}

    public int ATDDFailedTests(String codecontent, String testcontent){
        CompilationUnit Code = new CompilationUnit(Presetdeliverer.classname, codecontent, false);

        CompilationUnit Test = new CompilationUnit("ATDD"+Presetdeliverer.testname, testcontent, true);

        JavaStringCompiler compiler = CompilerFactory.getCompiler(Code, Test);

        compiler.compileAndRunTests();
        CompilerResult compileresults= compiler.getCompilerResult();
        if (compileresults.hasCompileErrors()==false) {
            TestResult testresults = compiler.getTestResult();

            int failedtests = testresults.getNumberOfFailedTests();
            System.out.println(failedtests);
            System.out.println(terminalfield.getText());
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
