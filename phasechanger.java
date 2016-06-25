
import java.util.Collection;

import vk.core.api.CompileError;
import vk.core.api.CompilerFactory;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

public class phasechanger {

	public static void check() {
		
		String codeclassname = "defaultcode";
		/*get classname from database
		*String codecontent = .getText();
		CompilationUnit Code = new CompilationUnit(codeclassname, codecontent, false);
		
		String testclassname = "defaulttest";
		*get from database
		*String testcontent = .getText();
		CompilationUnit Test = new CompilationUnit(testclassname, testcontent, true);
		
		JavaStringCompiler compiler = CompilerFactory.getCompiler(Code, Test);
		
		compiler.compileAndRunTests();
		CompilerResult compileresults= compiler.getCompilerResult();
		if (compileresults.hasCompileErrors()==false){
			TestResult testresults = Test.getTestResult();
		
			int failedtests = testresults.getNumberOfFailedTests();
		
			switch(phase){ 
        		case 1: 
           			if (failedtests==1){
        *  				save textfield test to java
        *  				disable textfiled test
        *  				enable textfield code
           				phase=2;
           			}
            		break; 
        		case 2: 
           			if (failedtests==0){
        *  				save textfield code to java
           				phase=3;
           			}
            		break; 
        		case 3: 
           			if (failedtests==0){
        *  				save textfield code to java
        *   			disable textfiled code
        *   			enable textfield test
           				phase=1;
           			} 
           			break; 
        		default: 
           			System.out.println("Fehler!");
           			break;
        	} 
		
		} else {
			Collection<CompileError> codeerrors = compiler.getCompilerErrorsForCompilationUnit(Code);
			if (codeerrors.size()!=0){
				String Fehlermeldung = codeerrors.toString();
		*		label set text Fehlermeldung
		*		label set text codeerrors.size
			}else{
				Collection<CompileError> testerrors = compiler.getCompilerErrorsForCompilationUnit(Test);
				if (testerrors.size()!=0){
					String Fehlermeldung = testerrors.toString();
		*			label set text Fehlermeldung
		*			label set text testerrors.size();
				}
			}
		}
		
		*/
		
	}

}
