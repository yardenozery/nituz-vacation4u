package View;

import Model.Model;
import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("HomePage.fxml").openStream());
        primaryStage.setTitle("EveryVacation4U");
        primaryStage.setScene(new Scene(root, 550, 275));
        root.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Model model = new Model();

        Controller controller = new Controller(model);
        homePageController view = fxmlLoader.getController();
        view.setController(controller);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}