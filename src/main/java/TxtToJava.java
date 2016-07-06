//by Julian and Michael, code inspired by EditorToJava and JavaToEditor
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TxtToJava {

	    

	    public static void transform() {
	        File source = new File("./src/main/resources/txt/"+PresetDataBase.codepreset+".txt");
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
