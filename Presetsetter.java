/**
 * Created by jonas on 22.06.2016.
 */
public class Presetsetter {
    public static String setclassname(String inputline){
        String classname="";
        Integer  i=0;
        while(inputline.charAt(i) != '>'){
            i++;
        }
        classname = inputline.substring(i+1,inputline.length());
        PresetDataBase.codeclassname=classname;
        return classname;
    }
    public static String setclasscomment(String inputline){
        String classcomment="";
        int i=0;
        while(inputline.charAt(i) != '>'){
            i++;
        }
        classcomment = inputline.substring(i+1,inputline.length());
        return classcomment;
    }
    public static String settestname (String inputline){
        String testname="";
        int i=0;
        while(inputline.charAt(i) != '>'){
            i++;
        }
        testname = inputline.substring(i+1,inputline.length());
        PresetDataBase.testclassname = testname;
        return testname;
    }
    public static boolean setbabysteps (String inputline){
        int i=0;
        while(inputline.charAt(i) != '>'){
            i++;
        }
        if (inputline.charAt(i+1)=='t' || inputline.charAt(i+1)=='T'){
            setbabysteptimer(i,inputline);
            return true;
        }
        else if (inputline.charAt(i+1)=='f' || inputline.charAt(i+1)=='F'){
            return false;
        }
        else{
            System.out.println("Ungültige Angabe bei Babysteps-Mode, default Einstellung (false) wurde übernommen.");
            return false;
        }
    }
    public static boolean setatdd (String inputline){
        int i=0;
        while(inputline.charAt(i) != '>'){
            i++;
        }
        if (inputline.charAt(i+1)=='t' || inputline.charAt(i+1)=='T'){
            return true;
        }
        else if ((inputline.charAt(i+1)=='f' || inputline.charAt(i+1)=='F')){
            return false;
        }
        else{
            System.out.println("Ungültige Angabe beim Timetracker, default Einstellung (false) wurde übernommen.");
            return false;
        }
    }
    public static void setbabysteptimer(int i, String inputline){
        while (inputline.charAt(i) != '#'){
            i++;
        }
        //Inputstelle sieht so aus #02:20 davon werden nur die Minuten genommen (02) in in eien Int gerechnet
        int minutes = Integer.parseInt(Character.toString(inputline.charAt(i+1))+Character.toString(inputline.charAt(i+2)));
        //Gleiche Inputstelle hier werden nur die Sekunden verwendet also 20 und in einen Int geechnet
        int seconds = Integer.parseInt(Character.toString(inputline.charAt(i+4))+Character.toString(inputline.charAt(i+5)));
        //Umrechnen der Zeit in Sekunden und einfügen in die DataBase :D
        PresetDataBase.babystepstime = (minutes*60)+seconds;
    }
}
