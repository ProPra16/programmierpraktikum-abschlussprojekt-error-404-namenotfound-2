import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Martin on 11.07.2016.
 */
public class EditorToJavaTest {
    @Test
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
    }

}