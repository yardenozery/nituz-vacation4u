package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class searchVacationController extends generalController {

    public javafx.scene.control.TextField txtfld_origin;
    public javafx.scene.control.TextField txtfld_destination;
    public javafx.scene.control.DatePicker datefld_departureDate;
    public javafx.scene.control.DatePicker datefld_arrivalDate;
    public javafx.scene.control.TextField txtfld_minPrice;
    public javafx.scene.control.TextField txtfld_maxPrice;

    public void SearchVacationByFields(javafx.event.ActionEvent event) throws Exception{
        String departureDate="";
        String arrivalDate="";

        if(datefld_departureDate.getValue() != null)
            departureDate = datefld_departureDate.getValue().toString();
        if(datefld_arrivalDate.getValue() != null)
            arrivalDate = datefld_arrivalDate.getValue().toString();

        ArrayList<String[]> vacations = controller.SearchVacationByFields(txtfld_origin.getText(), txtfld_destination.getText(), departureDate,
                                                                                arrivalDate, txtfld_minPrice.getText(), txtfld_maxPrice.getText());

        ShowVacations(vacations);

        //for (String[] vacation: vacations) {
        //    for (String param: vacation) {
        //        System.out.print(param + " ");
        //    }
        //    System.out.println();
        //}
    }

    public void back(javafx.event.ActionEvent event) throws Exception{
        if(currentUserName == null || currentUserName.equals(""))
            changeToHomePageScene(event);
        else
            changeToMainMenuScene(event);

    }

    public void showReturnDate(){
        if(datefld_departureDate.getValue() != null)
            datefld_arrivalDate.setDisable(false);
        else
            datefld_arrivalDate.setDisable(true);
    }

    public void ShowVacations(ArrayList<String[]> vacations) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("ShowVacations.fxml").openStream());
        root.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Vacations");
        stage.setScene(new Scene(root));

        showVacationsController showVacationsCon = loader.getController();
        showVacationsCon.ShowVacations(vacations);
        stage.show();
    }
}
