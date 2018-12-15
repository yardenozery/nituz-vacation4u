package View;

import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class showVacationsController extends generalController{


    public javafx.scene.control.TableView<Vacation> tbl_vacations;
    public javafx.scene.control.TableColumn tbl_col_origin;
    public javafx.scene.control.TableColumn tbl_col_destination;
    public javafx.scene.control.TableColumn tbl_col_departureDate;
    public javafx.scene.control.TableColumn tbl_col_price;
    public javafx.scene.control.TableColumn tbl_col_viewVacationButton;

    public void ShowVacations(ArrayList<String[]> vacations) throws Exception{


        tbl_col_origin.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("origin"));
        tbl_col_destination.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("destination"));
        tbl_col_departureDate.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("departureDate"));
        tbl_col_price.setCellValueFactory(
                new PropertyValueFactory<Vacation, String>("price"));
        tbl_col_viewVacationButton.setCellValueFactory(
                new PropertyValueFactory<Vacation, Button>("viewVacation"));


        for (String[] vacation: vacations) {

            Button viewVacationButton = new Button("View Vacation");

            Vacation newVacation = new Vacation(vacation[0],vacation[1],vacation[2],vacation[3],vacation[4],vacation[5],vacation[6],vacation[7],
                    vacation[8],vacation[9],vacation[10],vacation[11],vacation[12],vacation[13],vacation[14],vacation[15],vacation[16], viewVacationButton);


            viewVacationButton.setOnAction(event ->
            {
                try{
                    showChosenVacation(event, newVacation, true);
                }
                catch (Exception e)
                {e.printStackTrace();}
            });
            tbl_vacations.getItems().add(newVacation);
        }

    }



    /*public void ShowVacation(ArrayList<String[]> vacations) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("ShowVacations.fxml").openStream());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Vacations");
        stage.setScene(new Scene(root));

        showVacationsController showVacationsCon = loader.getController();
        showVacationsCon.ShowVacations(vacations);
        stage.show();
    }*/


}
