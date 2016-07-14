import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * By Martin and Yulian
 */
public class EditorToJava {

    private String filename;
    
    /**
	 * Creates new instance of EditorToJava and saves a filename as parameter.
	 * @param name
	 */
    public EditorToJava(String name){filename = name;}

    /**
	 * Saves File with the filename which is taken from the instance variable.
	 * @param text
	 */
    public void save(String text){
        File tmp = new File(filename+".java");
        Path p = Paths.get(tmp.getAbsolutePath());

        try(FileWriter writer = new FileWriter(tmp)) {
            writer.write(text);
        }
        catch(IOException e){}

    }

}
