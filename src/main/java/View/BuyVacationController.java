package View;

import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;


public class BuyVacationController extends generalController{

    public javafx.scene.control.CheckBox checkfld_creditCard;
    public javafx.scene.control.CheckBox checkfld_paypal;
    public javafx.scene.control.Label lbl_comment;
    public javafx.scene.control.TextField txtfld_creditCardNumber;
    public javafx.scene.control.TextField txtfld_expirationDateMonth;
    public javafx.scene.control.TextField txtfld_expirationDateYear;
    public javafx.scene.control.TextField txtfld_cvv;
    public javafx.scene.control.Button btn_cvvNotKnown;
    public javafx.scene.control.Button btn_purchase;
    public javafx.scene.control.Label lbl_creditCardNumber;
    public javafx.scene.control.Label lbl_expirationDatete;
    public javafx.scene.control.Label lbl_cvv;
    public javafx.scene.control.Label lbl_slash;
    public javafx.scene.control.Label lbl_MMYYYY;

    public javafx.scene.control.TextField txtfld_paypalUserName;
    public javafx.scene.control.PasswordField txtfld_paypalPassword;
    public javafx.scene.control.Label lbl_paypalUserName;
    public javafx.scene.control.Label lbl_paypalPassword;
    public javafx.scene.control.Label lbl_paypalComment;
    public javafx.scene.control.Label lbl_cvvNotKnow;
    public javafx.scene.image.ImageView paypalLogo;

    public Vacation vacation;
    private Stage stage;//////////////////////////////////////////////mabey static

    public void setPurchaseVacation(Stage stage, Vacation  vacation){
        this.vacation = vacation;
        this.stage = stage;
    }

    public void purchaseVacation(javafx.event.ActionEvent event) throws Exception{
        boolean[] checkFields;
        if(checkfld_creditCard.isSelected()){
            checkFields = controller.purchaseVacationByCreditCard(txtfld_creditCardNumber.getText(), txtfld_expirationDateMonth.getText(), txtfld_expirationDateYear.getText(), txtfld_cvv.getText(), vacation, currentUserName);
            checkVacationParams(event, checkFields);
        }
        else{
            if(!txtfld_paypalUserName.getText().equals("") && !txtfld_paypalPassword.getText().equals("")){
                controller.purchaseVacationByPaypal(vacation, currentUserName);
                controller.removeVacation(vacation);
                controller.removeConfirmation(vacation, currentUserName);
                showConfirmation(event);
                lbl_paypalComment.setText("");
            }
            else
                lbl_paypalComment.setText("User name or password is not valid.");
        }

    }

    private void checkVacationParams(javafx.event.ActionEvent event, boolean[] checkFields)  throws IOException {
        lbl_comment.setText("");
        txtfld_creditCardNumber.setStyle("");
        txtfld_expirationDateMonth.setStyle("");
        txtfld_expirationDateYear.setStyle("");
        txtfld_cvv.setStyle("");
        boolean wasAdded = true;

        if(checkFields[0] == true){
            txtfld_creditCardNumber.setStyle("-fx-border-width: 3; -fx-border-color: red");
            lbl_comment.setText("The credit card number is not valid, Pleas enter 16 digits");
        }
        if(checkFields[1] == true){
            txtfld_expirationDateYear.setStyle("-fx-border-width: 3; -fx-border-color: red");
            lbl_comment.setText(lbl_comment.getText() + "\nThe expiration date year is not valid.");
        }
        if(checkFields[2] == true){
            txtfld_expirationDateMonth.setStyle("-fx-border-width: 3; -fx-border-color: red");
            lbl_comment.setText(lbl_comment.getText() + "\nThe expiration date month is not valid.");
        }
        if(checkFields[3] == true){
            txtfld_expirationDateMonth.setStyle("-fx-border-width: 3; -fx-border-color: red");
            txtfld_expirationDateYear.setStyle("-fx-border-width: 3; -fx-border-color: red");
            lbl_comment.setText(lbl_comment.getText() + "\nThe expiration date has passed.");
        }
        if(checkFields[4] == true){
            txtfld_cvv.setStyle("-fx-border-width: 3; -fx-border-color: red");
            lbl_comment.setText(lbl_comment.getText() + "\nThe CVV number is not valid, Pleas enter 3 digits");
        }

        for (int i = 0; i < 5; i++) {
            if (checkFields[i] == true) {
                lbl_comment.setVisible(true);
                wasAdded = false;
            }
        }

        if (wasAdded){
            controller.removeVacation(vacation);
            controller.removeConfirmation(vacation, currentUserName);
            showConfirmation(event);
        }
    }

    private void showConfirmation(javafx.event.ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("The vacation purchase Successfully!");
        alert.showAndWait();

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    public void showChosenVacation(javafx.event.ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("ChosenVacation.fxml").openStream());
        root.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Scene addScene = new Scene(root, 909, 611);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addScene);

        ChosenVacationController chosenVacationController = loader.getController();
        chosenVacationController.ShowVacation(null, vacation, true);
        window.show();
    }

    public void showPaypalFunction(){
        if(checkfld_paypal.isSelected()){
            checkfld_creditCard.setSelected(false);
            setVisibleFunction(false);
        }
        else{
            checkfld_creditCard.setSelected(true);
            setVisibleFunction(true);
        }

    }

    public void showCreditCardFunction(){
        if(checkfld_creditCard.isSelected()){
            checkfld_paypal.setSelected(false);
            setVisibleFunction(true);
        }
        else{
            checkfld_paypal.setSelected(true);
            setVisibleFunction(false);
        }

    }

    private void setVisibleFunction(boolean visible){
        lbl_comment.setVisible(visible);
        txtfld_creditCardNumber.setVisible(visible);
        txtfld_expirationDateMonth.setVisible(visible);
        txtfld_expirationDateYear.setVisible(visible);
        txtfld_cvv.setVisible(visible);
        btn_cvvNotKnown.setVisible(visible);
        lbl_creditCardNumber.setVisible(visible);
        lbl_expirationDatete.setVisible(visible);
        lbl_cvv.setVisible(visible);
        lbl_slash.setVisible(visible);
        lbl_MMYYYY.setVisible(visible);

        txtfld_paypalUserName.setVisible(!visible);
        txtfld_paypalPassword.setVisible(!visible);
        lbl_paypalUserName.setVisible(!visible);
        lbl_paypalPassword.setVisible(!visible);
        lbl_paypalComment.setVisible(!visible);
        paypalLogo.setVisible(!visible);

    }

    public void cvvNotKnowShow(){
        lbl_cvvNotKnow.setVisible(true);
    }

    public void cvvNotKnowDisappear(){
        lbl_cvvNotKnow.setVisible(false);
    }


}