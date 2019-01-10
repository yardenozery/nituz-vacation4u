package View;

import Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ExchangeVacationController extends generalController{

    public javafx.scene.control.TableView<ExchangeRequest> tbl_exchangeRequests;
    public javafx.scene.control.TableColumn tbl_col_requestFrom;
    public javafx.scene.control.TableColumn tbl_col_viewRequestFromVacation;
    public javafx.scene.control.TableColumn tbl_col_requestViewMyVacation;
    public javafx.scene.control.TableColumn tbl_col_confirmExchange;
    public javafx.scene.control.TableColumn tbl_col_rejectExchange;


    public javafx.scene.control.TableView<ExchangeConfirm> tbl_exchangeConfirmations;
    public javafx.scene.control.TableColumn tbl_col_confirmFrom;
    public javafx.scene.control.TableColumn tbl_col_viewConfirmFromVacation;
    public javafx.scene.control.TableColumn tbl_col_confirmViewMyVacation;
    public javafx.scene.control.TableColumn tbl_col_ok;
    public Stage stage;


    public void showExchanges(Stage stage){

        this.stage = stage;

        tbl_col_requestFrom.setCellValueFactory(
                new PropertyValueFactory<ExchangeRequest, String>("requestFrom"));
        tbl_col_viewRequestFromVacation.setCellValueFactory(
                new PropertyValueFactory<ExchangeRequest, Button>("viewRequestFromVacation"));
        tbl_col_requestViewMyVacation.setCellValueFactory(
                new PropertyValueFactory<ExchangeRequest, Button>("viewMyVacation"));
        tbl_col_confirmExchange.setCellValueFactory(
                new PropertyValueFactory<ExchangeRequest, Button>("confirm"));
        tbl_col_rejectExchange.setCellValueFactory(
                new PropertyValueFactory<ExchangeRequest, Button>("reject"));


        tbl_col_confirmFrom.setCellValueFactory(
                new PropertyValueFactory<ExchangeConfirm, String>("confirmFrom"));
        tbl_col_viewConfirmFromVacation.setCellValueFactory(
                new PropertyValueFactory<ExchangeConfirm, Button>("viewConfirmFromVacation"));
        tbl_col_confirmViewMyVacation.setCellValueFactory(
                new PropertyValueFactory<ExchangeConfirm, Button>("viewMyVacation"));
        tbl_col_ok.setCellValueFactory(
                new PropertyValueFactory<ExchangeConfirm, Button>("ok"));

        fillExchangeRequestsTable();
        fillExchangeConfirmaionsTable();
    }


    private void fillExchangeRequestsTable(){
        ArrayList<String[]> exchangeRequests = controller.getExchangeRequests(currentUserName);

        for(String[] vacation : exchangeRequests){
            Button viewRequestFromVacation = new Button("View Vacation");
            Button requestViewMyVacation = new Button("View Vacation");
            Button confirmExchange = new Button("Confirm Vacation");
            Button rejectExchange = new Button("Reject Vacation");

            ExchangeRequest newExchangeRequest = new ExchangeRequest(vacation[1], viewRequestFromVacation, requestViewMyVacation, confirmExchange, rejectExchange);

            String[] newVacation;
            newVacation = controller.getVacation(vacation[0]);

            Vacation fromVacation = new Vacation(newVacation[0],newVacation[1],newVacation[2],newVacation[3],newVacation[4],newVacation[5],newVacation[6],newVacation[7],
                    newVacation[8],newVacation[9],newVacation[10],newVacation[11],newVacation[12],newVacation[13],newVacation[14],newVacation[15],newVacation[16], viewRequestFromVacation);

            newVacation = controller.getVacation(vacation[2]);

            Vacation myVacation = new Vacation(newVacation[0],newVacation[1],newVacation[2],newVacation[3],newVacation[4],newVacation[5],newVacation[6],newVacation[7],
                    newVacation[8],newVacation[9],newVacation[10],newVacation[11],newVacation[12],newVacation[13],newVacation[14],newVacation[15],newVacation[16], requestViewMyVacation);

            viewRequestFromVacation.setOnAction(event ->
            {
                try{
                    showChosenVacation(event, fromVacation, false);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            requestViewMyVacation.setOnAction(event ->
            {
                try{
                    showChosenVacation(event, myVacation, false);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            confirmExchange.setOnAction(event ->
            {
                try{
                    confirmVacationPressed(event, vacation);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            rejectExchange.setOnAction(event ->
            {
                try{
                    rejectVacationPressed(event, vacation);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            tbl_exchangeRequests.getItems().add(newExchangeRequest);
        }
    }

    private void fillExchangeConfirmaionsTable(){
        ArrayList<String[]> exchangeConfirmations = controller.getExchangeConfirmations(currentUserName);

        for(String[] vacation : exchangeConfirmations){
            Button viewConfirmFromVacation = new Button("View Vacation");
            Button confirmViewMyVacation = new Button("View Vacation");
            Button okExchange = new Button("OK");

            ExchangeConfirm newExchangeConfirm = new ExchangeConfirm(vacation[3], viewConfirmFromVacation, confirmViewMyVacation, okExchange);

            String[] newVacation;
            newVacation = controller.getVacation(vacation[2]);

            Vacation fromVacation = new Vacation(newVacation[0],newVacation[1],newVacation[2],newVacation[3],newVacation[4],newVacation[5],newVacation[6],newVacation[7],
                    newVacation[8],newVacation[9],newVacation[10],newVacation[11],newVacation[12],newVacation[13],newVacation[14],newVacation[15],newVacation[16], viewConfirmFromVacation);

            newVacation = controller.getVacation(vacation[0]);

            Vacation myVacation = new Vacation(newVacation[0],newVacation[1],newVacation[2],newVacation[3],newVacation[4],newVacation[5],newVacation[6],newVacation[7],
                    newVacation[8],newVacation[9],newVacation[10],newVacation[11],newVacation[12],newVacation[13],newVacation[14],newVacation[15],newVacation[16], confirmViewMyVacation);

            viewConfirmFromVacation.setOnAction(event ->
            {
                try{
                    showChosenVacation(event, fromVacation, false);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            confirmViewMyVacation.setOnAction(event ->
            {
                try{
                    showChosenVacation(event, myVacation, false);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            okExchange.setOnAction(event ->
            {
                try{
                    okPressed(event, vacation, fromVacation, myVacation);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });

            tbl_exchangeConfirmations.getItems().add(newExchangeConfirm);
        }
    }


    private void confirmVacationPressed(javafx.event.ActionEvent event, String[] vacation) throws Exception{
        controller.confirmExchangeRequest(vacation);
        changeToExchangeScene(event);
    }

    private void rejectVacationPressed(javafx.event.ActionEvent event, String[] vacation) throws Exception{
        controller.rejectExchangeRequest(vacation);
        changeToExchangeScene(event);
    }

    private void okPressed(ActionEvent event, String[] vacation, Vacation fromVacation, Vacation myVacation) throws Exception{
        controller.okExchange(vacation);
        controller.removeVacation(fromVacation);
        controller.removeVacation(myVacation);
        changeToExchangeScene(event);
    }
}
