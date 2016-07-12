import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Martin on 11.07.2016.
 */
public class ATDDControllerTest {
    @Test
    public void test() throws IOException {

        String input = "public class blabla...    ";

        Parent root = (Parent) FXMLLoader.load(getClass().getResource("fxml/ATDDGUI.fxml"));
        ATDDController.giveCodeText(input);
        Stage menustage = new Stage();
        menustage.setTitle("ATDD");
        menustage.setScene(new Scene(root));
    }
}