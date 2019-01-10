package View;

import Model.Confirm;
import Model.Vacation;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MyVacationsController extends generalController{

    public javafx.scene.control.TableView<MyVacation> tbl_myVacations;
    public javafx.scene.control.TableColumn tbl_col_origin;
    public javafx.scene.control.TableColumn tbl_col_destination;
    public javafx.scene.control.TableColumn tbl_col_departureDate;
    public javafx.scene.control.TableColumn tbl_col_viewVacaion;
    public javafx.scene.control.TableColumn tbl_col_exchange;

    private Vacation vacationIwant;

    public void showMyVacation(Vacation vacationIwant){

        this.vacationIwant = vacationIwant;

        tbl_col_origin.setCellValueFactory(
                new PropertyValueFactory<MyVacation, String>("origin"));
        tbl_col_destination.setCellValueFactory(
                new PropertyValueFactory<MyVacation, String>("destination"));
        tbl_col_departureDate.setCellValueFactory(
                new PropertyValueFactory<MyVacation, String>("departureDate"));
        tbl_col_viewVacaion.setCellValueFactory(
                new PropertyValueFactory<MyVacation, Button>("viewVacation"));
        tbl_col_exchange.setCellValueFactory(
                new PropertyValueFactory<MyVacation, Button>("exchange"));


        ArrayList<String[]> myVacationsID = new ArrayList<>();
        myVacationsID = controller.myVacations(currentUserName);

        for (String[] vacation: myVacationsID) {

            Button viewVacationButton = new Button("View Vacation");
            Button exchangeButton = new Button("Exchange Request");

            MyVacation newMyVacation = new MyVacation(vacation[1], vacation[2], vacation[3], viewVacationButton, exchangeButton);

            String[] newVacation = controller.getVacation(vacation[0]);

            Vacation showVacation = new Vacation(newVacation[0],newVacation[1],newVacation[2],newVacation[3],newVacation[4],newVacation[5],newVacation[6],newVacation[7],
                    newVacation[8],newVacation[9],newVacation[10],newVacation[11],newVacation[12],newVacation[13],newVacation[14],newVacation[15],newVacation[16], viewVacationButton);


            viewVacationButton.setOnAction(event ->
            {
                try{
                    showChosenVacation(event, showVacation, false);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            exchangeButton.setOnAction(event ->
            {
                try{
                    exchangeButton.setDisable(true);
                    requestForExchange(event, vacationIwant, vacation[0]);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            tbl_myVacations.getItems().add(newMyVacation);
        }
    }

    private void requestForExchange(ActionEvent event, Vacation vacationIwant, String myVacationID) {
        controller.requestForExchange(vacationIwant, myVacationID, currentUserName);
    }

    public class MyVacation{

        private String origin;
        private String destination;
        private String departureDate;
        private Button viewVacation;
        private Button exchange;

        public MyVacation(String origin, String destination, String departureDate, Button viewVacation, Button exchange) {
            this.origin = origin;
            this.destination = destination;
            this.departureDate = departureDate;
            this.viewVacation = viewVacation;
            this.exchange = exchange;
        }

        public String getOrigin() {
            return origin;
        }

        public String getDestination() {
            return destination;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public Button getViewVacation() {
            return viewVacation;
        }

        public Button getExchange() {
            return exchange;
        }
    }
}
