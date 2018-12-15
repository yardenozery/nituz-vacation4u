package Model;

import java.util.ArrayList;

/**
 * Interface which describes the functions of the system
 */

public interface Imodel {
    /**
     * The function creates a new user in the system, registration form for the user includes the
     * next fiels: username, password, dateOfBirth, firstName, lastName, city
     * @return Boolean array which describes which of fields was not filled
     */
    boolean[] CreateNewUser(String username,String password,String dateOfBirth,String firstName,String lastName,String city);

    /**
     * The function allows search of users according to the unique user name of the user
     * @return The fields of the given user as an array of String
     */
    String[] ReadUser(String usr);

    /**
     * The function updates the user's details by a specified form
     * @return Boolean array which describes which of fields was not filled
     */
    boolean[] UpdateUser(String oldusername,String username,String password,String dateOfBirth,String firstName,String lastName,String city);

    /**
     * The function deletes the user from the system permanently by a specified form
     * @param userName
     */
    void DeleteUser(String userName);

    /**
     * The function allows to search vacations by destination
     * @return The vacations in the given destination
     */
    ArrayList<String[]> SearchVacationByFields(String origin, String destination, String departureDate, String arrivalDate, String minPrice, String maxPrice);

    /**
     * The function creates a new vacation in the system
     * @return
     */

    boolean[] CreateNewVacation(String airLine, String departureDate, String origin, String destination, String adultTicketsNumber, String childTicketsNumber,
                                String babyTicketsNumber, String flightBackIncluded, String arrivalDate, String luggageIncluded, String price, String vacationType,
                                String hotelIncluded, String hotelGrade,String userName, String freeText);

    boolean[] purchaseVacationByCreditCard(String creditCardNumber, String expirationDateMonth, String expirationDateYear, String cvv, Vacation vacation, String currentUserName);

    void purchaseVacationByPaypal(Vacation vacation, String currentUserName);

    ArrayList<String[]> getRequests(String currentUserName);

    String[] getVacation(String vacationID);

    void confirmVacation(String[] vacation);

    void rejectVacation(String[] vacation);

    ArrayList<String[]> getConfirmations(String currentUserName);

    void addRequest(Vacation vacation, String currentUserName);

    void removeVacation(Vacation vacation);

    void removeConfirmation(Vacation vacation, String currentUserName);
}
