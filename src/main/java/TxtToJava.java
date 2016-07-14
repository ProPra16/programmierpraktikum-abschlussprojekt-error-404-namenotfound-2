//by 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 * Convertiert eine .txt in eine .java Datei.
 * @author Julian and Michael, code inspired by EditorToJava and JavaToEditor.
 *
 */
public class TxtToJava {

	    
		/**
		 * transforms a txt file into a java file.
		 */
	    public static void transform() {
	        File source = new File(PresetDataBase.codefilepath);
	        Path pfad = Paths.get(source.getAbsolutePath());

	        ArrayList<String> buffer = new ArrayList<String>();
	        try {
	            buffer = (ArrayList<String>) Files.readAllLines(pfad);
	        }
	        catch (IOException e){}
	        String code = "";
	        for(int i = 0; i<buffer.size();i++){
	            if (i==0) code = code + buffer.get(i);

	            else code = code + "\n" + buffer.get(i);
	        }
	        save(code);
	    }
	    /**
	     * 
	     * @param String of a code that shall be saved into the java file.
	     */
	    public static void save(String a){
	        File tmp = new File("./src/main/resources/txt/"+PresetDataBase.codeclassname+".java");
	        Path p = Paths.get(tmp.getAbsolutePath());

	        try {
	            FileWriter writer = new FileWriter(tmp);
	            writer.write(a);
	            writer.flush();
	            writer.close();
	        }
	        catch(IOException e){}

	    }
}
