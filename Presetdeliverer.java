import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jonas on 21.06.2016.
 */
public class Presetdeliverer {
        static String classname;
        static String classcomment;
        static String testname;
        public static void main (String [] args){
            //File presettingcode = new File("presetcode.java");
            //File presettingtest = new File ("presettest.java");
            String vorlagecode = presetcode();
            String vorlagetest = "import static org.junit.Assert.*;\n"+
                                "import org.junit.Test;\n"+
                                "public class §{\n"+
                                "   @Test\n" +
                                "   public void testSomething(){\n" +
                                "   }\n"+
                                "}";
            //classname,classcomment,testname,babysteps und timetracker Einstellungen
            //Werden eingetragen bzw. eingelesen.
            XMLReader.reader();

            File presettingcode = new File(PresetDataBase.codeclassname+".java");
            File presettingtest = new File (PresetDataBase.testclassname+".java");
            //Die Lücken der CodeStrings werden gefüllt.
            vorlagecode = fillvorlagecode(vorlagecode);
            vorlagetest = fillvorlagetest(vorlagetest);
            writecodefile(presettingcode,vorlagecode);
            writetestfile(presettingtest,vorlagetest);
        }
        public static String fillvorlagecode (String vorlage){
            //Einfügen des Kommentars
            vorlage = fillinclasscomment(vorlage);
            //Einfügen des Klassennamens
            vorlage = fillinclassname(vorlage);
            return vorlage;
        }
        public static String fillinclasscomment(String vorlage){
            int i=0;
            String start = "";
            String end ="";
            while(vorlage.charAt(i)!='$'){
                i++;
            }
            start = vorlage.substring(0,i)+classcomment;
            end = vorlage.substring(i+1,vorlage.length());
            return start+end;
        }
        public static String fillinclassname (String vorlage) {
            String start ="";
            String end ="";
            int i=0;
            while(vorlage.charAt(i) != '?'){
                i++;
            }
            start = vorlage.substring(0,i)+classname;
            end = vorlage.substring(i+1,vorlage.length());
            return start+end;
        }
        public static String fillvorlagetest (String testvorlage){
            String start="";
            String end="";
            int i =0;
            while(testvorlage.charAt(i)!='§'){
                i++;
            }
            start = testvorlage.substring(0,i)+testname;
            end = testvorlage.substring(i+1,testvorlage.length());
            return start+end;
        }
        public static void writecodefile(File outputfile,String code) {
            Path p = Paths.get(outputfile.getAbsolutePath());
            try {
                FileWriter writer = new FileWriter(outputfile);
                writer.write(code);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        public static String presetcode (){
            File code = new File("presetcode.txt");
            Path p = Paths.get(code.getAbsolutePath());
            boolean filenotendet=true;
            String currentreadingline="";
            String output ="";
            try {
                FileReader reader = new FileReader(code);
                BufferedReader buffer = new BufferedReader(reader);
                while (filenotendet) {
                    currentreadingline = buffer.readLine();
                    if(currentreadingline !=null){
                        output+=currentreadingline+"\n";
                    }
                    if(currentreadingline==null) filenotendet=false;

                }
            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output;
        }
    }
