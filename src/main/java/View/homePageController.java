package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class homePageController extends generalController {

    /*
        Change to LogIn scene.
     */
    public void changeToLogInScene(javafx.event.ActionEvent event) throws IOException {
        Parent addSceneParent = FXMLLoader.load(getClass().getClassLoader().getResource("LogIn.fxml"));
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent, 550, 275);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }
}
