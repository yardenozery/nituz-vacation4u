package Model;

import javafx.scene.control.Button;

public class ExchangeConfirm {

    private String confirmFrom;
    private Button viewConfirmFromVacation;
    private Button viewMyVacation;
    private Button ok;

    public ExchangeConfirm(String confirmFrom, Button viewConfirmFromVacation, Button viewMyVacation, Button ok) {
        this.confirmFrom = confirmFrom;
        this.viewConfirmFromVacation = viewConfirmFromVacation;
        this.viewMyVacation = viewMyVacation;
        this.ok = ok;
    }

    public String getConfirmFrom() {
        return confirmFrom;
    }

    public Button getViewConfirmFromVacation() {
        return viewConfirmFromVacation;
    }

    public Button getViewMyVacation() {
        return viewMyVacation;
    }

    public Button getOk() {
        return ok;
    }
}
