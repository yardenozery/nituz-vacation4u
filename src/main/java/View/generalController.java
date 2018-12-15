package View;

import Controller.Controller;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/*
    An abstract class that all the controllers extends from.
    All the field of thr users saves in this class.
 */
public abstract class generalController {

    protected static Controller controller;
    public javafx.scene.control.Label txtfld_usrIsntExists;
    public boolean searchsuccess;

    public javafx.scene.control.TextField txtfld_userName;
    public javafx.scene.control.TextField txtfld_userPassword;
    public javafx.scene.control.DatePicker datefld_userBirthday;
    public javafx.scene.control.TextField txtfld_userFirstName;
    public javafx.scene.control.TextField txtfld_userLastName;
    public javafx.scene.control.TextField txtfld_userCity;

    public static String currentUserName;


    /**
     * set the controller to be used.
     *
     * @param controller - the controller of the project. one controller for all the class.
     */
    public void setController(Controller controller){
        this.controller = controller;
    }

    /**
     * The method checks if the fields that the user want to enter are valid.
     *
     * @param event
     * @param checkFields - boolean array that containes the fields of the user.
     * @throws IOException
     */
    public void checkUserParams(javafx.event.ActionEvent event, boolean[] checkFields)  throws IOException {
        boolean wasAdded = true;

            if (checkFields[1] == true){
                txtfld_usrIsntExists.setVisible(true);
                txtfld_usrIsntExists.setText("Password must be at least 8 characters.");
                txtfld_userPassword.setStyle("-fx-border-width: 3; -fx-border-color: red");


            }else {

                if(checkFields[0] == true) txtfld_userName.setStyle("-fx-border-width: 3; -fx-border-color: red");
                if(checkFields[2] == true) datefld_userBirthday.setStyle("-fx-border-width: 3; -fx-border-color: red");
                if(checkFields[3] == true) txtfld_userFirstName.setStyle("-fx-border-width: 3; -fx-border-color: red");
                if(checkFields[4] == true) txtfld_userLastName.setStyle("-fx-border-width: 3; -fx-border-color: red");
                if(checkFields[5] == true) txtfld_userCity.setStyle("-fx-border-width: 3; -fx-border-color: red");
                for (int i = 0; i < 6; i++) {
                    if (checkFields[i] == true) {
                        txtfld_usrIsntExists.setVisible(true);
                        txtfld_usrIsntExists.setText("All fields must be filled.");
                        wasAdded = false;
                    }
                }
                if(checkFields[6] == true){
                    datefld_userBirthday.setStyle("-fx-border-width: 3; -fx-border-color: red");
                    txtfld_usrIsntExists.setText("You must be over 18 to sign up.");
                    txtfld_usrIsntExists.setVisible(true);
                    wasAdded = false;
                }

                if (wasAdded){
                    currentUserName = txtfld_userName.getText();
                    changeToMainMenuScene(event);
                }
            }

    }

    /*
        Change to main scene.
     */
    public void changeToMainMenuScene(javafx.event.ActionEvent event) throws IOException {
        Parent addSceneParent = FXMLLoader.load(getClass().getClassLoader().getResource("MainMenu.fxml"));
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent, 550, 275);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }


    /*
        The method search a user from the database.
     */
    public void search(){
        String[] userDetails = controller.ReadUser(txtfld_userName.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(userDetails.length ==6) {
            searchsuccess = true;
            if(txtfld_userPassword != null)
                txtfld_userPassword.setText(userDetails[1]);
            LocalDate localDate = LocalDate.parse(userDetails[2], formatter);
            datefld_userBirthday.setValue(localDate);
            txtfld_userFirstName.setText(userDetails[3]);
            txtfld_userLastName.setText(userDetails[4]);
            txtfld_userCity.setText(userDetails[5]);
        }
        else{
            searchsuccess = false;
            txtfld_usrIsntExists.setVisible(true);
            //txtfld_userPassword.setText("");
            LocalDate localDate = LocalDate.parse("2000-01-01", formatter);
            datefld_userBirthday.setValue(localDate);
            txtfld_userFirstName.setText("");
            txtfld_userLastName.setText("");
            txtfld_userCity.setText("");
        }

    }

    /*
        Change to sign up scene.
     */
    public void changeToSignUpScene(javafx.event.ActionEvent event) throws IOException {
        Parent addSceneParent = FXMLLoader.load(getClass().getClassLoader().getResource("SignUp.fxml"));
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent, 608, 347);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    /*
        Change to home page scene.
     */
    public void changeToHomePageScene(javafx.event.ActionEvent event) throws IOException {
        currentUserName = "";
        Parent addSceneParent = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage.fxml"));
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent, 550, 275);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    /*
        Change to search vacation scene.
     */
    public void changeToSearchVacationScene(javafx.event.ActionEvent event) throws IOException {
        Parent addSceneParent = FXMLLoader.load(getClass().getClassLoader().getResource("SearchVacation.fxml"));
        addSceneParent.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(addSceneParent, 799, 425);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    public void showChosenVacation(javafx.event.ActionEvent event, Vacation vacation, boolean requestVisible) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("ChosenVacation.fxml").openStream());
        root.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Vacation");
        stage.setScene(new Scene(root));

        ChosenVacationController chosenVacationController = loader.getController();
        chosenVacationController.ShowVacation(event, vacation, requestVisible);
        stage.show();
    }

    public void changeToMailboxScene(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("mailbox.fxml").openStream());
        root.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(root, 691, 630);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        MailBoxController mailBoxController = loader.getController();
        mailBoxController.showRequests(window);
        window.show();
    }

}
