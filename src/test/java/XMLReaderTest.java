import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Created by jonas on 09.07.2016.
 */
public class XMLReaderTest {

    @Test
    public void testdata1(){
        PresetDataBase.presetpath = "./src/test/java/firstpresettest.txt";
        XMLReader.reader();
        assertEquals("NeverLucky",PresetDataBase.codeclassname);
        assertEquals("NeverLuckyTest",PresetDataBase.testclassname);
        assertEquals(true,PresetDataBase.babysteps);
        assertEquals(80,PresetDataBase.babystepstime);
        assertEquals(true,PresetDataBase.atdd);
    }

    @Test
    public void testdata2(){
        PresetDataBase.presetpath = "./src/test/java/secondpresettest.txt";
        XMLReader.reader();
        assertEquals("Testclassname",PresetDataBase.codeclassname);
        assertEquals("Testtestclassname",PresetDataBase.testclassname);
        assertEquals(false,PresetDataBase.babysteps);
        assertEquals(false,PresetDataBase.atdd);
    }
}
