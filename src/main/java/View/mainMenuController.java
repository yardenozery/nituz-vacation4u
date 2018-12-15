package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainMenuController extends generalController {

    /*
        Change to searching scene.
     */
    public void changeToSearchScene(javafx.event.ActionEvent event) throws IOException {
        Parent addSceneParent = FXMLLoader.load(getClass().getClassLoader().getResource("search.fxml"));
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent);//, 550, 275
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    /*
        Change to update scene.
     */
    public void changeToUpdateScene(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent addSceneParent = loader.load(getClass().getClassLoader().getResource("Update.fxml").openStream());
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent);//, 550, 275
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //////////////////////////////////////////////////////////////////////////
        updateController UC = loader.getController();
        UC.searchForUpdate();
        //////////////////////////////////////////////////////////////////////////
        window.setScene(addScene);
        window.show();
    }

    /*
        Change to deleting scene.
     */
    public void changeToDeleteScene(javafx.event.ActionEvent event) throws IOException {
        Parent addSceneParent = FXMLLoader.load(getClass().getClassLoader().getResource("delete.fxml"));
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent);//, 550, 275
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    /*
        Change to crate vacation scene.
     */
    public void changeToCreateVacation(javafx.event.ActionEvent event) throws IOException {
        Parent addSceneParent = FXMLLoader.load(getClass().getClassLoader().getResource("createVacation.fxml"));
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent);//, 550, 275
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }


}
