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
                "    public static void main(String [] args){\n" +
                "        System.out.println(\"Hello World!\");\n" +
                "    }\n" +
                "}",testobjekt.read());
    }
}
