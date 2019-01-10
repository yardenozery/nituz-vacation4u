package View;

import Model.Confirm;
import Model.Request;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class MailBoxController extends generalController {

    public javafx.scene.control.TableView<Request> tbl_requests;
    public javafx.scene.control.TableColumn tbl_col_buyer;
    public javafx.scene.control.TableColumn tbl_col_viewRequestsVacation;
    public javafx.scene.control.TableColumn tbl_col_confirmVacation;
    public javafx.scene.control.TableColumn tbl_rejectVacation;


    public javafx.scene.control.TableView<Confirm> tbl_confirmations;
    public javafx.scene.control.TableColumn tbl_col_seller;
    public javafx.scene.control.TableColumn tbl_col_viewConfirmVacation;
    public javafx.scene.control.TableColumn tbl_col_purchase;
    public Stage stage;


    public void showRequests(Stage stage){

        this.stage = stage;
        tbl_requests.getItems().removeAll();// mabey not
        tbl_confirmations.getItems().removeAll();// mabey not

        tbl_col_buyer.setCellValueFactory(
                new PropertyValueFactory<Request, String>("buyer"));
        tbl_col_viewRequestsVacation.setCellValueFactory(
                new PropertyValueFactory<Request, Button>("viewVacation"));
        tbl_col_confirmVacation.setCellValueFactory(
                new PropertyValueFactory<Request, Button>("confirmVacation"));
        tbl_rejectVacation.setCellValueFactory(
                new PropertyValueFactory<Request, Button>("rejectVacation"));


        tbl_col_seller.setCellValueFactory(
                new PropertyValueFactory<Confirm, String>("seller"));
        tbl_col_viewConfirmVacation.setCellValueFactory(
                new PropertyValueFactory<Confirm, Button>("viewVacation"));
        tbl_col_purchase.setCellValueFactory(
                new PropertyValueFactory<Confirm, Button>("purchase"));

        fillRequestsTable();
        fillConfirmsTable();
    }


    private void fillRequestsTable(){
        ArrayList<String[]> requests = controller.getRequests(currentUserName);

        for(String[] vacation : requests){
            Button viewVacationButton = new Button("View Vacation");
            Button confirmVacationButton = new Button("Confirm Vacation");
            Button rejectVacationButton = new Button("Reject Vacation");

            Request newRequest = new Request(vacation[2], viewVacationButton, confirmVacationButton, rejectVacationButton);

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

            confirmVacationButton.setOnAction(event ->
            {
                try{
                    confirmVacationPressed(event, vacation);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            rejectVacationButton.setOnAction(event ->
            {
                try{
                    rejectVacationPressed(event, vacation);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            tbl_requests.getItems().add(newRequest);
        }
    }

    private void confirmVacationPressed(javafx.event.ActionEvent event, String[] vacation) throws Exception{
        controller.confirmVacation(vacation);
        changeToMailboxScene(event);
    }

    private void rejectVacationPressed(javafx.event.ActionEvent event, String[] vacation) throws Exception{
        controller.rejectVacation(vacation);
        changeToMailboxScene(event);
    }





   private void fillConfirmsTable(){
       ArrayList<String[]> confirmations = controller.getConfirmations(currentUserName);

       for(String[] vacation : confirmations){
           Button viewVacationButton = new Button("View Vacation");
           Button purchaseVacationButton = new Button("Purchase Vacation In Cash");

           Confirm newConfirm = new Confirm(vacation[1], viewVacationButton, purchaseVacationButton);

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

           purchaseVacationButton.setOnAction(event ->
           {
               try{
                   payedVacation(event, showVacation);
               }
               catch (Exception e)
               {e.printStackTrace();}
           });

           tbl_confirmations.getItems().add(newConfirm);
       }
   }

   public void payedVacation(javafx.event.ActionEvent event, Vacation vacation) throws Exception{
       controller.addWaitingForCashVacation(vacation, currentUserName);
       controller.removeConfirmation(vacation, currentUserName);
       changeToMailboxScene(event);
   }

}
