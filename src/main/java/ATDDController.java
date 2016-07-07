import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import vk.core.api.*;

import java.util.Collection;

public class ATDDController {
@FXML
public TextArea ATDDtestcodefield,ErrorField;

    @FXML
    public Button atddcheckbutton;

public static String codefieldText;

    public void initialize(){
        ATDDtestcodefield.setText(new JavaToEditor("./src/main/resources/txt/"+"ATDD"+Presetdeliverer.testname).read());
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

            System.out.println("hier");

            return failedtests;
        }
        else {
            System.out.println("hier2");
            CompilerResult output = compiler.getCompilerResult();
            Collection<CompileError> codeerrors = output.getCompilerErrorsForCompilationUnit(Code);
            if (codeerrors.size() != 0) {
                String Fehlermeldung = codeerrors.toString();
                ErrorField.setText(ErrorField.getText() + "\n" + Fehlermeldung);
                ErrorField.setText(ErrorField.getText() + "\n" + codeerrors.size());
            } else {
                Collection<CompileError> testerrors = output.getCompilerErrorsForCompilationUnit(Test);
                if (testerrors.size() != 0) {
                    String Fehlermeldung = testerrors.toString();
                    ErrorField.setText(ErrorField.getText() + "\n" + Fehlermeldung);
                    ErrorField.setText(ErrorField.getText() + "\n" + (codeerrors.size()));

                }
            }
            return -1;
        }

    }


}
