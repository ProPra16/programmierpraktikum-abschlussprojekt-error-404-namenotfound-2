import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Martin on 22.06.2016.
 */
public class JavaToEditor {

    private String filename;

    public JavaToEditor(String name){this.filename = name;}

    public String read() {
        File source = new File("src/"+filename);
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
        return code;
    }
}
