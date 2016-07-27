import java.io.*;

/**
 * Created by jonas on 21.06.2016.
 */
public class XMLReader {
    /**
     * Ließt eine Presettextdatei ein und Filtert die Zeilen welche die Werte fuer die PresetDataBase besitzen aus.
     * Die Zeilen welche die gewuenschten Programm Presets beinhalten werden an den Presetsetter weiter gegeben.
     * Die Daten welche der Presetsetter gelesen hat, werden in die PresetDataBase geschrieben.
     */
    public static void reader() {
        File inputdata = new File(PresetDataBase.presetpath);
        String currentreadingline =" ";
        boolean filenotendet=true;
        try {
            FileReader reader = new FileReader(inputdata);
            BufferedReader buffer = new BufferedReader(reader);
                while(filenotendet){
                    currentreadingline = buffer.readLine();
                    if(currentreadingline !=null && currentreadingline.length()>11) {
                        String linestart = currentreadingline.substring(0, 11);
                        //Bestimmt die Zeilen mit wichtigem Inhalt eindeutig.
                        if (linestart.equals("<descriptio")) {
                            Presetdeliverer.classcomment = Presetsetter.setclasscomment(currentreadingline);
                        } else if (linestart.equals("<class name")) {
                            Presetdeliverer.classname = Presetsetter.setclassname(currentreadingline);
                        } else if (linestart.equals("<test name>")) {
                            Presetdeliverer.testname = Presetsetter.settestname(currentreadingline);
                        } else if (linestart.equals("<babysteps ")) {
                            PresetDataBase.babysteps = Presetsetter.setbabysteps(currentreadingline);
                        } else if (linestart.equals("<atdd value")) {
                            PresetDataBase.atdd = Presetsetter.setatdd(currentreadingline);
                        }
                    }
                    if(currentreadingline==null) filenotendet=false;
                }
            try {
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
