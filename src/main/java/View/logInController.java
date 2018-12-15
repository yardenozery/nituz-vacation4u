package View;

import java.io.IOException;

public class logInController extends generalController {

    public void LogIn(javafx.event.ActionEvent event)  throws IOException {
        String[] userDetails = controller.ReadUser(txtfld_userName.getText());
        if(userDetails.length ==6) {
            if(txtfld_userPassword.getText().equals(userDetails[1])) {
                currentUserName = txtfld_userName.getText();
                changeToMainMenuScene(event);
            }else {
                txtfld_usrIsntExists.setVisible(true);
                txtfld_usrIsntExists.setText("Incorrect password");
            }
        }
        else {
            txtfld_usrIsntExists.setVisible(true);
            txtfld_usrIsntExists.setText("User is not exist");
        }
    }
}
