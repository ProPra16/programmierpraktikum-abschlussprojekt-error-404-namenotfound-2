import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jonas and Yulian
 */
public class JavaToEditorTest {
    @Test
    public void readinhelloworld(){
        JavaToEditor testobjekt = new JavaToEditor("HelloWorld");
        //System.out.println(testobjekt.read());
        assertEquals("public class HelloWorld{\n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\tSystem.out.println(\"Hello World!\");\n" +
                "\t}\n" +
                "}",testobjekt.read());
    }
    
    @Test
    public void readHelloTest2(){
        JavaToEditor testFile = new JavaToEditor("HelloTest2");
        //System.out.println(testFile.read());
        assertEquals("public class HelloTest2{\n" +
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
                "}",testFile.read());
    }
}
