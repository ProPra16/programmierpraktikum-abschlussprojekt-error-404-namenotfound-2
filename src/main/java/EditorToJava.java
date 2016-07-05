import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Martin on 22.06.2016.
 */
public class EditorToJava {

    private String filename;

    public EditorToJava(String name){filename = name;}

    public void save(String a){
        File tmp = new File(filename+".java");
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
