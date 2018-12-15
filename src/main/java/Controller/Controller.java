package Controller;

import Model.Vacation;
import Model.Request;
import Model.Model;

import java.util.ArrayList;

public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public boolean[] CreateNewUser(String username,String password,String dateOfBirth,String firstName,String lastName,String city){
        return model.CreateNewUser(username, password, dateOfBirth, firstName, lastName, city);
    }

    public String[] ReadUser(String usr){
        return model.ReadUser(usr);
    }

    public boolean[] UpdateUser(String oldusername, String username,String password,String dateOfBirth,String firstName,String lastName,String city){
        return model.UpdateUser(oldusername, username, password, dateOfBirth, firstName, lastName, city);
    }

    public void DeleteUser(String userName){
        model.DeleteUser(userName);
    }

    public ArrayList<String[]> SearchVacationByFields(String origin, String destination, String departureDate, String arrivalDate, String minPrice, String maxPrice){
        return model.SearchVacationByFields(origin, destination, departureDate, arrivalDate, minPrice, maxPrice);
    }

    public boolean[] CreateNewVacation(String airLine, String departureDate, String origin, String destination, String adultTicketsNumber, String childTicketsNumber,
                                     String babyTicketsNumber, String flightBackIncluded, String arrivalDate, String luggageIncluded, String price, String vacationType,
                                     String hotelIncluded, String hotelGrade, String userName, String freeText){
        return model.CreateNewVacation(airLine, departureDate, origin, destination, adultTicketsNumber, childTicketsNumber, babyTicketsNumber, flightBackIncluded, arrivalDate, luggageIncluded, price, vacationType, hotelIncluded, hotelGrade, userName, freeText);
    }

    public boolean[] purchaseVacationByCreditCard(String creditCardNumber, String expirationDateMonth, String expirationDateYear, String cvv,Vacation vacation, String currentUserName){
        return model.purchaseVacationByCreditCard(creditCardNumber, expirationDateMonth, expirationDateYear, cvv, vacation, currentUserName);
    }

    public void purchaseVacationByPaypal(Vacation vacation, String currentUserName){
        model.purchaseVacationByPaypal(vacation, currentUserName);
    }

    public ArrayList<String[]> getRequests(String currentUserName){
        return model.getRequests(currentUserName);
    }

    public String[] getVacation(String vacationID){
        return model.getVacation(vacationID);
    }

    public void confirmVacation(String[] vacation){
        model.confirmVacation(vacation);
    }

    public void rejectVacation(String[] vacation){
        model.rejectVacation(vacation);
    }

    public ArrayList<String[]> getConfirmations(String currentUserName){
        return model.getConfirmations(currentUserName);
    }

    public void addRequest(Vacation vacation, String currentUserName){
        model.addRequest(vacation, currentUserName);
    }

    public void removeVacation(Vacation vacation){
        model.removeVacation(vacation);
    }

    public void removeConfirmation(Vacation vacation, String currentUserName){
        model.removeConfirmation(vacation, currentUserName);
    }


}
