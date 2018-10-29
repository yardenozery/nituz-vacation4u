package View;

import Model.Model;
import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // neta yarden osher hen
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("MainMenu.fxml").openStream());
        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root, 550, 275));
        root.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        //root.getStylesheets().add("src/View/ViewStyle.css");
        Model model = new Model();

        Controller controller = new Controller(model);
        mainMenuController view = fxmlLoader.getController();
        view.setController(controller);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
