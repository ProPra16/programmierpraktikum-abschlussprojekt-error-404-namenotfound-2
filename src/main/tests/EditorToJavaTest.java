import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Created by Martin and Yulian
 */
@RunWith(Parameterized.class)
public class EditorToJavaTest {
	@parameters
	public static Collection<String[]> data(){
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
                "}"}
				return Arrays.asList(data);
		}
	public EditorToJavaTest(String r){
		this.r = r;
	}
		
	
	
	
    /*@Test
    public void beschreibtDateiRichtig(){
        String input = "public class HelloWorld{\n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\tSystem.out.println(\"Hello World\");\n" +
                "\t}\n" +
                "}";
        EditorToJava etj = new EditorToJava("HelloWorld2");
        etj.save(input);
        String output = new JavaToEditor("HelloWorld2").read();
        assertEquals(input,output);
    }*/
    
    @Test
    public void beschreibtDateiRichtig(){
    	EditorToJava testFile = new EditorToJava(HelloTest);
    	test.save(r);
    	String testOutput = new JavaToEditor("HelloTest").read();
    	assertEquals(r, testOutput);
    }
    

}