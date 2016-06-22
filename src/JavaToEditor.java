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
public class JavaToEditor extends Application{
    @Override
    public void start(Stage PriamryStage){
        TextArea code = new TextArea();
        ArrayList<String> puffer = reader("JavaFile.java");
        for(int i = 0; i<puffer.size();i++){
            if (i==0) code.appendText(puffer.get(i));
            else code.appendText("\n"+puffer.get(i));
        }

        StackPane root = new StackPane();
        root.getChildren().add(code);
        PriamryStage.setScene(new Scene(root,500,500));
        PriamryStage.show();
    }

    public static void main (String[] args){
        launch(args);
    }

    public ArrayList<String> reader(String name) {
        File source = new File("src/"+name);
        Path pfad = Paths.get(source.getAbsolutePath());

        ArrayList<String> buffer = new ArrayList<String>();
        try {
            buffer = (ArrayList<String>) Files.readAllLines(pfad);
        }
        catch (IOException e){}
        return buffer;
    }
}
