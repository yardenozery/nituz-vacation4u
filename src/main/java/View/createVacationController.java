package View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class createVacationController extends generalController{

    public javafx.scene.control.Label lbl_markedFields;
    public javafx.scene.control.Label lbl_arrivalDate;
    public javafx.scene.control.Label lbl_arrivalStar;
    public javafx.scene.control.Label lbl_hotelRank;
    public javafx.scene.control.TextField txtfld_airLine;
    public javafx.scene.control.DatePicker datefld_departureDate;
    public javafx.scene.control.TextField txtfld_origin;
    public javafx.scene.control.TextField txtfld_destination;
    public javafx.scene.control.TextField txtfld_adultTicketsNumber;
    public javafx.scene.control.TextField txtfld_childTicketsNumber;
    public javafx.scene.control.TextField txtfld_babyTicketsNumber;
    public javafx.scene.control.CheckBox checkfld_flightBackIncluded;
    public javafx.scene.control.DatePicker datefld_arrivalDate;
    public javafx.scene.control.CheckBox checkfld_luggageIncluded;
    public javafx.scene.control.TextField txtfld_price;
    public javafx.scene.control.ChoiceBox cbxfld_vacationType;
    public javafx.scene.control.CheckBox checkfld_hotelIncluded;
    public javafx.scene.control.TextField txtfld_hotelGrade;
    public javafx.scene.control.TextField txtfld_freeText;

    public void CreateNewVacation(javafx.event.ActionEvent event) throws Exception{
        String arrivalDate = "";
        String vacationType = "";
        String hotelGrade = "";
        String freeText = "";

        if(datefld_arrivalDate.getValue() != null)
            arrivalDate = datefld_arrivalDate.getValue().toString();
        if(cbxfld_vacationType.getValue() != null && cbxfld_vacationType.getValue().toString() != "")
            vacationType = cbxfld_vacationType.getValue().toString();
        if(txtfld_hotelGrade.getText()!= null && !txtfld_hotelGrade.getText().equals(""))
            hotelGrade = txtfld_hotelGrade.getText();
        if(txtfld_freeText.getText()!= null && !txtfld_freeText.getText().equals(""))
            freeText = txtfld_freeText.getText();
        if(datefld_departureDate.getValue() == null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse("2019-01-01", formatter);
            datefld_departureDate.setValue(localDate);
        }

        boolean[] checkFields = controller.CreateNewVacation(txtfld_airLine.getText(), datefld_departureDate.getValue().toString(), txtfld_origin.getText(), txtfld_destination.getText(), txtfld_adultTicketsNumber.getText(),
                txtfld_childTicketsNumber.getText(), txtfld_babyTicketsNumber.getText(), String.valueOf(checkfld_flightBackIncluded.isSelected()), arrivalDate,
                String.valueOf(checkfld_luggageIncluded.isSelected()), txtfld_price.getText(), vacationType, String.valueOf(checkfld_hotelIncluded.isSelected()), hotelGrade, currentUserName,  freeText);

        checkVacationParams(event, checkFields);
    }

    private void checkVacationParams(javafx.event.ActionEvent event, boolean[] checkFields)  throws IOException {
        boolean wasAdded = true;

        if(checkFields[0] == true) txtfld_airLine.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[1] == true) datefld_departureDate.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[2] == true) txtfld_origin.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[3] == true) txtfld_destination.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[4] == true) txtfld_adultTicketsNumber.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[5] == true) txtfld_childTicketsNumber.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[6] == true) txtfld_babyTicketsNumber.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[7] == true) checkfld_flightBackIncluded.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[8] == true) datefld_arrivalDate.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[9] == true) checkfld_luggageIncluded.setStyle("-fx-border-width: 3; -fx-border-color: red");
        if(checkFields[10] == true) txtfld_price.setStyle("-fx-border-width: 3; -fx-border-color: red");
        for (int i = 0; i < 11; i++) {
            if (checkFields[i] == true) {
                lbl_markedFields.setVisible(true);
                wasAdded = false;
            }
        }

        if (wasAdded) changeToMainMenuScene(event);
    }

    public void showReturnDate(){
        if(checkfld_flightBackIncluded.isSelected()) {
            datefld_arrivalDate.setDisable(false);
            lbl_arrivalDate.setDisable(false);
            lbl_arrivalStar.setVisible(true);
        }else {
            datefld_arrivalDate.setDisable(true);
            lbl_arrivalDate.setDisable(true);
            lbl_arrivalStar.setVisible(false);
        }
    }

    public void showHotelRank(){
        if(checkfld_hotelIncluded.isSelected()) {
            txtfld_hotelGrade.setDisable(false);
            lbl_hotelRank.setDisable(false);
        }else {
            txtfld_hotelGrade.setDisable(true);
            lbl_hotelRank.setDisable(true);
        }
    }

}
