package View;

import Model.CashAriived;
import Model.Confirm;
import Model.Request;
import Model.Vacation;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CashArrivedController extends generalController{

    public javafx.scene.control.TableView<CashAriived> tbl_cashArrived;
    public javafx.scene.control.TableColumn tbl_col_buyer;
    public javafx.scene.control.TableColumn tbl_col_viewVacation;
    public javafx.scene.control.TableColumn tbl_col_confirm;
    public Stage stage;

    public void showCashArrived(Stage stage){

        this.stage = stage;
        tbl_cashArrived.getItems().removeAll();// maybe not

        tbl_col_buyer.setCellValueFactory(
                new PropertyValueFactory<CashAriived, String>("buyer"));
        tbl_col_viewVacation.setCellValueFactory(
                new PropertyValueFactory<CashAriived, Button>("viewVacation"));
        tbl_col_confirm.setCellValueFactory(
                new PropertyValueFactory<CashAriived, Button>("confirm"));

        fillCashArrivedTable();
    }

    private void fillCashArrivedTable() {
        ArrayList<String[]> cashArrivedVacations = controller.getCashArrivedVacations(currentUserName);

        for(String[] vacation : cashArrivedVacations){
            Button viewVacationButton = new Button("View Vacation");
            Button confirmButton = new Button("Purchase Vacation By Cash");

            CashAriived newCashAriived = new CashAriived(vacation[2], viewVacationButton, confirmButton);

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

            confirmButton.setOnAction(event ->
            {
                try{
                    confirmVacation(event, showVacation);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            tbl_cashArrived.getItems().add(newCashAriived);
        }
    }

    private void confirmVacation(ActionEvent event, Vacation vacation) throws Exception{
        controller.insertToPurchasesTable(vacation);
        controller.removeVacation(vacation);
        controller.removeWaitingForCashVacation(vacation);
        changeToCashArrivedScene(event);
    }
}
