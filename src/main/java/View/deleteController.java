package View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class deleteController extends generalController {

    public void delete(javafx.event.ActionEvent event) throws IOException {

        String[] checkUserTaken = controller.ReadUser(txtfld_userName.getText());
        if (checkUserTaken.length == 1) {
            txtfld_usrIsntExists.setVisible(true);

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Press OK to delete your user");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                controller.DeleteUser(txtfld_userName.getText());
                changeToMainMenuScene(event);
            } else {
                alert.close();
            }

        }
    }
}
