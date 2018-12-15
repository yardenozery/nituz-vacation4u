package Model;

import javafx.scene.control.Button;

public class Confirm {

    private String seller;
    private Button viewVacation;
    private Button purchase;

    public Confirm(String seller, Button viewVacation, Button purchase) {
        this.seller = seller;
        this.viewVacation = viewVacation;
        this.purchase = purchase;
    }

    public String getSeller() {
        return seller;
    }

    public Button getViewVacation() {
        return viewVacation;
    }

    public Button getPurchase() {
        return purchase;
    }
}
