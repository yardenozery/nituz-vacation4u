package Model;

import javafx.scene.control.Button;

public class ExchangeRequest {

    private String requestFrom;
    private Button viewRequestFromVacation;
    private Button viewMyVacation;
    private Button confirm;
    private Button reject;

    public ExchangeRequest(String requestFrom, Button viewRequestFromVacation, Button viewMyVacation, Button confirm, Button reject) {
        this.requestFrom = requestFrom;
        this.viewRequestFromVacation = viewRequestFromVacation;
        this.viewMyVacation = viewMyVacation;
        this.confirm = confirm;
        this.reject = reject;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public Button getViewRequestFromVacation() {
        return viewRequestFromVacation;
    }

    public Button getViewMyVacation() {
        return viewMyVacation;
    }

    public Button getConfirm() {
        return confirm;
    }

    public Button getReject() {
        return reject;
    }
}
