import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Martin and Yulian
 */
@RunWith(Parameterized.class)
public class EditorToJavaTest {
	private String r;
	@Parameters
	public static String[] data(){
		String[] data = {"public class HelloWorld{\n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\tSystem.out.println(\"Hello World\");\n" +
                "\t}\n" +
                "}", 
                "public class javaToEditorTestText{\n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\ttestText();\n" +
                "\t}\n" +
                "\tpublic static void textTest{\n" +
                "\t\tfor(int i = 0; i < 10; i++){\n" +
                "\t\t\tif(true){\n" + 
                "\t\t\t\treturn\n" + 
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}"};
				return data;
		}
	public EditorToJavaTest(String r){
		this.r = r;
	}  
    @Test
    public void beschreibtDateiRichtig(){
    	EditorToJava testFile = new EditorToJava("HelloTest");
    	testFile.save(r);
    	String testOutput = new JavaToEditor("HelloTest").read();
    	assertEquals(r, testOutput);
    }
    
}