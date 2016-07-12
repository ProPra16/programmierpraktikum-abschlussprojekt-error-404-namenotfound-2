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
}
