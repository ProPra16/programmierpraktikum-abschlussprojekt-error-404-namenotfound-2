import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Martin on 22.06.2016.
 */
public class Control extends Application {

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage PrimaryStage) {

        // Presettings
        TextArea code = new TextArea();

        // Hier wird das Object zum lesen erzeugt und eingelesen
        JavaToEditor reader = new JavaToEditor("JavaFile.java");
        ArrayList<String> puffer = reader.read();
        for(int i = 0; i<puffer.size();i++){
            if (i==0) code.appendText(puffer.get(i));

            else code.appendText("\n"+puffer.get(i));
        }

        // Jetzt soll der Inhalt des Editors in eine Java Datei geschrieben werden
       // EditorToJava writer = new EditorToJava("InputTest.java");

        EditorToJava writer = new EditorToJava("InputTest.java");
        writer.save(code.getText());


        // Screen wird erzeugt
        StackPane root = new StackPane();
        root.getChildren().add(code);

        PrimaryStage.setScene(new Scene(root,500,500));
        PrimaryStage.show();
    }
}
