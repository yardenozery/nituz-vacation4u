package View;

import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ChosenVacationController extends generalController{

    public javafx.scene.control.Label lbl_airLine;
    public javafx.scene.control.Label departureDate;
    public javafx.scene.control.Label lbl_origin;
    public javafx.scene.control.Label lbl_destination;
    public javafx.scene.control.Label lbl_adultTicketsNumber;
    public javafx.scene.control.Label lbl_childTicketsNumber;
    public javafx.scene.control.Label lbl_babyTicketsNumber;
    public javafx.scene.control.Label lbl_flightBackincluded;
    public javafx.scene.control.Label lbl_arrivalDate;
    public javafx.scene.control.Label lbl_luggageIncluded;
    public javafx.scene.control.Label lbl_price;
    public javafx.scene.control.Label lbl_vacationType;
    public javafx.scene.control.Label lbl_hotelIncluded;
    public javafx.scene.control.Label lbl_hotelRank;
    public javafx.scene.control.Label lbl_seller;
    public javafx.scene.control.Label lbl_freeText;
    public javafx.scene.control.Label lbl_notLoggedIn;
    public javafx.scene.control.Button btn_request;
    public javafx.scene.control.Button btn_exchange;

    public Vacation vacation;
    private Stage stage;

    public void ShowVacation(javafx.event.ActionEvent event, Vacation vacation, boolean requestVisible){

        btn_request.setVisible(requestVisible);
        btn_exchange.setVisible(requestVisible);
        if(event != null)
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        lbl_notLoggedIn.setVisible(false);
        this.vacation = vacation;

        lbl_airLine.setText("AirLine: " + vacation.getAirLine());
        departureDate.setText("Departure date: " + vacation.getDepartureDate());
        lbl_origin.setText("Origin: " + vacation.getOrigin());
        lbl_destination.setText("Destination: " + vacation.getDestination());
        lbl_adultTicketsNumber.setText("Adult ticket number: " + vacation.getAdultTicketsNumber());
        lbl_childTicketsNumber.setText("Child ticket number: " + vacation.getChildTicketsNumber());
        lbl_babyTicketsNumber.setText("Baby ticket number: " + vacation.getBabyTicketsNumber());

        if(vacation.getFlightBackIncluded().equals("true")){
            lbl_flightBackincluded.setText("Flight back included: Yes");
            lbl_arrivalDate.setText("Return date: " + vacation.getArrivalDate());
        }
        else{
            lbl_flightBackincluded.setText("Flight back included: No");
            lbl_arrivalDate.setText("");
        }

        if(vacation.getLuggageIncluded().equals("true"))
            lbl_luggageIncluded.setText("Luggage included: Yes");
        else
            lbl_luggageIncluded.setText("Luggage included: No");

        lbl_price.setText("Price: " + vacation.getPrice());

        if(vacation.getVacationType() != null && !vacation.getVacationType().equals(""))
            lbl_vacationType.setText("Vacation type: " + vacation.getVacationType());
        else
            lbl_vacationType.setText("");

        if(vacation.getHotelIncluded().equals("true"))
            lbl_hotelIncluded.setText("Hotel included: Yes");
        else
            lbl_hotelIncluded.setText("Hotel included: No");

        if(vacation.getHotelGrade() != null && !vacation.getHotelGrade().equals(""))
            lbl_hotelRank.setText("Hotel Rank: " + vacation.getHotelGrade());
        else
            lbl_hotelRank.setText("");

        lbl_seller.setText("Seller: " + vacation.getUserName());

        if(vacation.getFreeText() != null && !vacation.getFreeText().equals(""))
            lbl_freeText.setText("Description: " + vacation.getFreeText());
        else
            lbl_freeText.setText("");

    }

    public void requestSent(javafx.event.ActionEvent event) throws IOException {
        if(currentUserName != null && !currentUserName.equals("")){
            controller.addRequest(vacation, currentUserName);
            lbl_notLoggedIn.setText("The request has been sent");
            btn_request.setDisable(true);
            lbl_notLoggedIn.setVisible(true);
        }
        else{
            lbl_notLoggedIn.setVisible(true);
        }
    }

    public void showMyVacation(javafx.event.ActionEvent event) throws Exception{
        if(currentUserName != null && !currentUserName.equals("")){
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getClassLoader().getResource("MyVacations.fxml").openStream());
            root.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("My Vacations");
            stage.setScene(new Scene(root));

            MyVacationsController myVacationsController = loader.getController();
            myVacationsController.showMyVacation(vacation);
            stage.show();
            btn_exchange.setDisable(true);
        }
        else{
            lbl_notLoggedIn.setVisible(true);
        }
    }

}
