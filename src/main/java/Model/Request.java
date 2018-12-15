package Model;

import javafx.scene.control.Button;

public class Request {

    private String buyer;
    private Button viewVacation;
    private Button confirmVacation;
    private Button rejectVacation;

    public Request(String buyer, Button viewVacation, Button confirmVacation, Button rejectVacation) {
        this.buyer = buyer;
        this.viewVacation = viewVacation;
        this.confirmVacation = confirmVacation;
        this.rejectVacation = rejectVacation;
    }

    public String getBuyer() {
        return buyer;
    }

    public Button getViewVacation() {
        return viewVacation;
    }

    public Button getConfirmVacation() {
        return confirmVacation;
    }

    public Button getRejectVacation() {
        return rejectVacation;
    }
}
