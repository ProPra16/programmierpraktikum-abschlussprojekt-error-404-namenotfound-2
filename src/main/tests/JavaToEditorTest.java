import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by jonas on 10.07.2016.
 */
public class JavaToEditorTest {
    @Test
    public void readinhelloworld(){
        JavaToEditor testobjekt = new JavaToEditor("HelloWorld");
        System.out.println(testobjekt.read());
        assertEquals("public class HelloWorld{\n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\tSystem.out.println(\"Hello World!\");\n" +
                "\t}\n" +
                "}",testobjekt.read());
    }
    
    @Test
    public void readJavaToEditorTestText(){
    	JavaToEditor testIt = new JavaToEditor("JavaToEditorTestText");
    	assertEquals("public class javaToEditorTestText{\n" +
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
    	"}", testIt.read());
    }
}
