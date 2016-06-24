package Demo;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import vk.core.api.*;

class DemoCompileUnit {

	public static void main(String[] args) {
		String ClassName = "PrintInt";
		String Content = "class PrintInt { public static int ausgabe1() {return 1;}}";
		
		String Testname = "Testprint";
		String TestContent = "import static org.junit.Assert.*; import org.junit.Test; public class Testprint { @Test public void Wert1(){ assertEquals(1, PrintInt.ausgabe1());}}" ;
		
		// simple test and method as an example
		
		
		CompilationUnit Demo = new CompilationUnit(ClassName, Content, false);
		System.out.println(Demo.getClassContent());
		System.out.println(Demo.getClassName());
		System.out.println(Demo.getSourceFile());
		
		CompilationUnit DemoTest = new CompilationUnit(Testname,TestContent,true);
		System.out.println(DemoTest.getClassContent());
		System.out.println(DemoTest.getClassName());
		System.out.println(DemoTest.getSourceFile());
		
		// Returns name (classname), content (code) and file for compilation unit
		
		
		JavaStringCompiler Test = CompilerFactory.getCompiler(DemoTest, Demo);
		
		Test.compileAndRunTests();
		//Running classes and tests for specific compiler
		
		CompilerResult Output= Test.getCompilerResult();
		System.out.println(Output.hasCompileErrors());
		// Boolean for errors
		
		Collection<CompileError> errors = Output.getCompilerErrorsForCompilationUnit(DemoTest);
		String Fehlermeldung = errors.toString();
		
		System.out.println(Fehlermeldung);
		// Shows first error you can iterate if you need more
		
		Set<String> Namen = Test.getAllCompilationUnitNames();
		// Returns the names (Class Names) of all compilation units in the compiler
		
		Iterator iterator = Namen.iterator();
		while(iterator.hasNext()){
		  String element = (String) iterator.next();
		  System.out.println(element);
		}
		
		// There are a lot more functions in the Library for example statistics, error count etc.
		
		
	}

}
