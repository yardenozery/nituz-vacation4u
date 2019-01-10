package Model;

import javafx.scene.control.Button;

public class CashAriived {

    private String buyer;
    private Button viewVacation;
    private Button confirm;

    public CashAriived(String buyer, Button viewVacation, Button confirm) {
        this.buyer = buyer;
        this.viewVacation = viewVacation;
        this.confirm = confirm;
    }

    public String getBuyer() {
        return buyer;
    }

    public Button getViewVacation() {
        return viewVacation;
    }

    public Button getConfirm() {
        return confirm;
    }
}
