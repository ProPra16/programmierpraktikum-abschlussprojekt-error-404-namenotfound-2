import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jonas on 21.06.2016.
 */
public class Presetdeliverer {
        static String classname;
        static String classcomment;
        static String testname;

    /**
     * Verwaltet die Aufrufe vom XMLReader sowie TxtToJava.
     * Besitzt Codevorlaen, welche zum befüllen weiter gegeben werden.
     * Sollte ATDD aktiv sein, wird außerdem das schreiben einer weiteren Testdatei veranlasst.
     */
        public static void main (){
            String vorlagetest ="import static org.junit.Assert.*;\n"+
                                "import org.junit.Test;\n"+
                                "public class %{\n"+
                                "   @Test\n" +
                                "   public void testSomething(){\n" +
                                "   }\n"+
                                "}";
            String ATDDvorlage ="import static org.junit.Assert.*;\n"+
                                "import org.junit.Test;\n"+
                                "public class ATDD%{\n"+
                                "   @Test\n" +
                                "   public void testSomething(){\n" +
                                "   }\n"+
                                "}";
            //classname,classcomment,testname,babysteps und timetracker Einstellungen
            //Werden eingetragen bzw. eingelesen.
            XMLReader.reader();
            TxtToJava.transform();
         //   File presettingcode = new File(PresetDataBase.codeclassname+".java");
            File presettingtest = new File ("./src/main/resources/txt/"+PresetDataBase.testclassname+".java");
            vorlagetest = fillvorlagetest(vorlagetest);
            writetestfile(presettingtest,vorlagetest);
            //Wenn ATDD aktiv, dann Erstelle eine weitere Testdatei, speziell für ATDD.
            if (PresetDataBase.atdd){
                ATDDvorlage = fillvorlagetest(ATDDvorlage);
                File presetatddtest = new File ("./src/main/resources/txt/"+"ATDD"+PresetDataBase.testclassname+".java");
                writetestfile(presetatddtest,ATDDvorlage);
            }
        }

    /**
     * Befüllt die TExtvorlage bin den gefilterten Werten asu der PresetDataBase.
     * @param testvorlage
     * @return String
     */
    public static String fillvorlagetest (String testvorlage){
            String start="";
            String end="";
            int i =0;
            while(testvorlage.charAt(i)!='%'){
                i++;
            }
            start = testvorlage.substring(0,i)+testname;
            end = testvorlage.substring(i+1,testvorlage.length());
            return start+end;
        }

    /**
     *
     * @param outputfile
     * @param test
     */
        public static void writetestfile(File outputfile,String test) {
            Path p = Paths.get(outputfile.getAbsolutePath());
            try {
                FileWriter writer = new FileWriter(outputfile);
                writer.write(test);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
