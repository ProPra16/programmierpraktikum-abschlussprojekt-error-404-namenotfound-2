import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonas on 09.07.2016.
 */
public class PresetsetterTest {
    @Test
    public void findclassname(){
        assertEquals("NeverLucky",Presetsetter.setclassname("<class name>NeverLucky"));
    }
    @Test
    public void findtestname(){
        assertEquals("NeverLuckyTest",Presetsetter.settestname("<test name>NeverLuckyTest"));
    }
    @Test
    public void findbabystepvalue(){
        assertEquals(true,Presetsetter.setbabysteps("<babysteps value>true #01:20"));
    }
    @Test
    public void calculatebabysteptime(){
        Presetsetter.setbabysteptimer(0,"<babysteps value>true #01:20");
        assertEquals(80,PresetDataBase.babystepstime);
    }
    @Test
    public void findatddvalue(){
        assertEquals(true,Presetsetter.setatdd("<atdd value>true"));
    }
    @Test
    public void findbabystepsvalue2(){
        assertEquals(false,Presetsetter.setbabysteps("<babysteps value>false"));
    }
    @Test
    public void findatddvalue2(){
        assertEquals(false,Presetsetter.setatdd("<atdd value>False"));
    }
}
