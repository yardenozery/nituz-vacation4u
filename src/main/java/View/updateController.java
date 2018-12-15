package View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
    The class describes how user is updated in the system.
 */
public class updateController extends generalController {

    public javafx.scene.control.Button btn_update;
    public javafx.scene.control.Label txtfld_usrExists;
    private String oldUserName;

    /**
     * The method update a user in the system.
     *
     * @param event
     * @throws IOException
     */
    public void update(javafx.event.ActionEvent event) throws IOException {

        String[] checkUserTaken = controller.ReadUser(txtfld_userName.getText());

        if(datefld_userBirthday.getValue() == null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse("2000-01-01", formatter);
            datefld_userBirthday.setValue(localDate);
        }

        if(oldUserName.equals(txtfld_userName.getText()) || checkUserTaken.length ==1){
            boolean[] checkFields = controller.UpdateUser(oldUserName, txtfld_userName.getText(),txtfld_userPassword.getText(),datefld_userBirthday.getValue().toString(), txtfld_userFirstName.getText(),txtfld_userLastName.getText(), txtfld_userCity.getText());
            txtfld_userName.setStyle("");
            txtfld_userPassword.setStyle("");
            datefld_userBirthday.setStyle("");
            txtfld_userFirstName.setStyle("");
            txtfld_userLastName.setStyle("");
            txtfld_userCity.setStyle("");

            checkUserParams(event, checkFields);
            currentUserName = txtfld_userName.getText();
        }
        else{
            txtfld_userName.setStyle("-fx-border-width: 3; -fx-border-color: red");
            txtfld_usrExists.setVisible(true);
        }

    }

    /**
     * the function check if the given username exist in the database
     */
    public void searchForUpdate(){
        txtfld_userName.setText(currentUserName);
        search();
        if(searchsuccess){
            oldUserName = txtfld_userName.getText();
            txtfld_usrIsntExists.setVisible(false);
            //txtfld_userName.setDisable(true);
            txtfld_userPassword.setDisable(false);
            datefld_userBirthday.setDisable(false);
            txtfld_userFirstName.setDisable(false);
            txtfld_userLastName.setDisable(false);
            txtfld_userCity.setDisable(false);
            btn_update.setDisable(false);

            txtfld_userName.setStyle("");
            txtfld_userPassword.setStyle("");
            datefld_userBirthday.setStyle("");
            txtfld_userFirstName.setStyle("");
            txtfld_userLastName.setStyle("");
            txtfld_userCity.setStyle("");
        }

    }

}
