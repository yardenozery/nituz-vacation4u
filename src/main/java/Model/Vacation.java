package Model;

import javafx.scene.control.Button;

public class Vacation {
    private String ID;
    private String airLine;
    private String departureDate;
    private String origin;
    private String destination;
    private String adultTicketsNumber;
    private String childTicketsNumber;
    private String babyTicketsNumber;
    private String flightBackIncluded;
    private String arrivalDate;
    private String luggageIncluded;
    private String price;
    private String vacationType;
    private String hotelIncluded;
    private String hotelGrade;
    private String userName;
    private String freeText;
    private Button viewVacation;




    public Vacation(String ID, String airLine, String departureDate, String origin, String destination, String adultTicketsNumber, String childTicketsNumber, String babyTicketsNumber, String flightBackIncluded, String arrivalDate, String luggageIncluded, String price, String vacationType, String hotelIncluded, String hotelGrade, String userName, String freeText, Button viewVacation) {
        this.ID = ID;
        this.airLine = airLine;
        this.departureDate = departureDate;
        this.origin = origin;
        this.destination = destination;
        this.adultTicketsNumber = adultTicketsNumber;
        this.childTicketsNumber = childTicketsNumber;
        this.babyTicketsNumber = babyTicketsNumber;
        this.flightBackIncluded = flightBackIncluded;
        this.arrivalDate = arrivalDate;
        this.luggageIncluded = luggageIncluded;
        this.price = price;

        this.vacationType = vacationType;
        this.hotelIncluded = hotelIncluded;
        this.hotelGrade = hotelGrade;
        this.userName = userName;
        this.freeText = freeText;
        this.viewVacation = viewVacation;
    }

    public String getID() {
        return ID;
    }

    public String getAirLine() {
        return airLine;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getAdultTicketsNumber() {
        return adultTicketsNumber;
    }

    public String getChildTicketsNumber() {
        return childTicketsNumber;
    }

    public String getBabyTicketsNumber() {
        return babyTicketsNumber;
    }

    public String getFlightBackIncluded() {
        return flightBackIncluded;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getLuggageIncluded() {
        return luggageIncluded;
    }

    public String getPrice() {
        return price;
    }

    public String getVacationType() {
        return vacationType;
    }

    public String getHotelIncluded() {
        return hotelIncluded;
    }

    public String getHotelGrade() {
        return hotelGrade;
    }

    public String getUserName() {
        return userName;
    }

    public String getFreeText() {
        return freeText;
    }

    public Button getViewVacation() {
        return viewVacation;
    }
}
