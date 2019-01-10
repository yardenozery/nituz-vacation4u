
package Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The class implements the Imodel interface
 */
public class Model implements Imodel {

    private Users user;
    private Vacations vacation;
    private Purchases purchase;
    private Requestions requests;
    private Confirmations confirmations;
    private WaitingForCash waitingForCash;
    private Exchanges exchanges;

    /**
     * A constructor which initialize the variable "Users" and apply the function "CreateDB"
     */
    public Model() {
        this.CreateDB();
        user = new Users();
        vacation = new Vacations();
        purchase = new Purchases();
        requests = new Requestions();
        confirmations = new Confirmations();
        waitingForCash = new WaitingForCash();
        exchanges = new Exchanges();
    }

    /**
     * The function creates the database which interfaces the system
     */
    private void CreateDB() {
        //String url = "jdbc:sqlite:C:/sqlite/db/MyDB";
        String url = "jdbc:sqlite:MyDB";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                //System.out.println("The driver name is " + meta.getDriverName());
                //System.out.println("A new database has been created.");

                // users table
                String userTable = "CREATE TABLE IF NOT EXISTS Users(\n"
                        + "	username text PRIMARY KEY,\n"
                        + "	password text NOT NULL,\n"
                        + "	dateOfBirth text NOT NULL,\n"
                        + "	firstName text NOT NULL,\n"
                        + "	lastName text NOT NULL,\n"
                        + "	city text NOT NULL);";
                createNewTable(url, userTable);

                // vacations table
                String vacationTable = "CREATE TABLE IF NOT EXISTS Vacations(\n"
                        + "	vacationID integer PRIMARY KEY,\n"
                        + "	airLine text NOT NULL,\n"
                        + "	departureDate text NOT NULL,\n"
                        + "	origin text NOT NULL,\n"
                        + "	destination text NOT NULL,\n"
                        + "	adultTicketsNumber integer NOT NULL,\n"
                        + "	childTicketsNumber integer NOT NULL,\n"
                        + "	babyTicketsNumber integer NOT NULL,\n"
                        + "	flightBackIncluded text NOT NULL,\n"
                        + "	arrivalDate text,\n"
                        + "	luggageIncluded text NOT NULL,\n"
                        + "	price integer NOT NULL,\n"
                        + "	vacationType text,\n"
                        + "	hotelIncluded text,\n"
                        + "	hotelGrade text,\n"
                        + "	userName text NOT NULL,\n"
                        + "	freeText text);";
                createNewTable(url, vacationTable);

                // requests table
                String requestsTable = "CREATE TABLE IF NOT EXISTS Requests(\n"
                        + "	vacationID integer,\n"
                        + "	seller text NOT NULL,\n"
                        + "	buyer text NOT NULL,\n"
                        + "	primary key (vacationID, buyer));";
                createNewTable(url, requestsTable);

                // confirmation table
                String confirmationTable = "CREATE TABLE IF NOT EXISTS Confirmations(\n"
                        + "	vacationID integer PRIMARY KEY,\n"
                        + "	seller text NOT NULL,\n"
                        + "	buyer text NOT NULL);";
                createNewTable(url, confirmationTable);

                // purchases table
                String purchasesTable = "CREATE TABLE IF NOT EXISTS Purchases(\n"
                        + "	vacationID integer PRIMARY KEY,\n"
                        + "	seller text NOT NULL,\n"
                        + "	buyer text NOT NULL);";
                createNewTable(url, purchasesTable);

                // WaitingForCash table
                String waitingForCash = "CREATE TABLE IF NOT EXISTS WaitingForCash(\n"
                        + "	vacationID integer PRIMARY KEY,\n"
                        + "	seller text NOT NULL,\n"
                        + "	buyer text NOT NULL);";
                createNewTable(url, waitingForCash);

                // Exchanges table
                String exchanges = "CREATE TABLE IF NOT EXISTS Exchanges(\n"
                        + "	requestFromVacationID integer,\n"
                        + "	requestFrom text NOT NULL,\n"
                        + "	requestToVacationID integer,\n"
                        + "	requestTo text NOT NULL,\n"
                        + "	confirmYet text NOT NULL,\n"
                        + "	executed text NOT NULL,\n"
                        + "	primary key (requestFromVacationID, requestToVacationID));";
                createNewTable(url, exchanges);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The function creates a new table on the database
     * @param url
     * @param sql
     */
    private static void createNewTable(String url, String sql) {

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The function implements connection to the database
     * @return
     */
    protected static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:MyDB";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public boolean[] CreateNewUser(String username, String password, String dateOfBirth, String firstName, String lastName, String city) {
        return user.CreateNewUser(username, password, dateOfBirth, firstName, lastName, city);
    }

    @Override
    public String[] ReadUser(String usr) {
        return user.ReadUser(usr);
    }

    @Override
    public boolean[] UpdateUser(String oldusername, String username, String password, String dateOfBirth, String firstName, String lastName, String city) {
        return user.UpdateUser(oldusername, username,  password, dateOfBirth, firstName, lastName, city);
    }

    @Override
    public void DeleteUser(String userName) {
        user.DeleteUser(userName);

    }

    @Override
    public ArrayList<String[]> SearchVacationByFields(String origin, String destination, String departureDate, String arrivalDate, String minPrice, String maxPrice, String currentUserName){
        return vacation.SearchVacationByFields(origin, destination, departureDate, arrivalDate, minPrice, maxPrice, currentUserName);
    }

    @Override
    public boolean[] CreateNewVacation(String airLine, String departureDate, String origin, String destination, String adultTicketsNumber, String childTicketsNumber, String babyTicketsNumber, String flightBackIncluded, String arrivalDate, String luggageIncluded, String price, String vacationType, String hotelIncluded, String hotelGrade,String userName, String freeText) {
        return vacation.CreateNewVacation(airLine, departureDate, origin, destination, adultTicketsNumber, childTicketsNumber, babyTicketsNumber, flightBackIncluded, arrivalDate, luggageIncluded, price, vacationType, hotelIncluded, hotelGrade, userName, freeText);
    }

    //@Override
    //public boolean[] purchaseVacationByCreditCard(String creditCardNumber, String expirationDateMonth, String expirationDateYear, String cvv, Vacation vacation, String currentUserName){
    //    return purchase.purchaseVacationByCreditCard(creditCardNumber, expirationDateMonth, expirationDateYear, cvv, vacation, currentUserName);
    //}
//
    //@Override
    //public void purchaseVacationByPaypal(Vacation vacation, String currentUserName){
    //    purchase.purchaseVacationByPaypal(vacation, currentUserName);
    //}

    @Override
    public ArrayList<String[]> getRequests(String currentUserName){
        return  requests.getRequests(currentUserName);
    }

    @Override
    public String[] getVacation(String vacationID){
        return requests.getVacation(vacationID);
    }

    @Override
    public void confirmVacation(String[] vacation){
        confirmations.confirmVacation(vacation);
    }

    @Override
    public void rejectVacation(String[] vacation){
        requests.rejectVacation(vacation);
    }

    @Override
    public ArrayList<String[]> getConfirmations(String currentUserName){
        return confirmations.getConfirmations(currentUserName);
    }

    @Override
    public void addRequest(Vacation vacation, String currentUserName){
        requests.addRequest(vacation, currentUserName);
    }

    @Override
    public void removeVacation(Vacation vacation){
        (this.vacation).removeVacation(vacation);
    }

    @Override
    public void removeConfirmation(Vacation vacation, String currentUserName){
        confirmations.removeConfirmation(vacation, currentUserName);
    }

    @Override
    public void addWaitingForCashVacation(Vacation vacation, String currentUserName) {
        waitingForCash.addWaitingForCashVacation(vacation, currentUserName);
    }

    @Override
    public void insertToPurchasesTable(Vacation vacation) {
        purchase.insertToPurchasesTable(vacation);
    }

    @Override
    public ArrayList<String[]> getCashArrivedVacations(String currentUserName) {
        return waitingForCash.getCashArrivedVacations(currentUserName);
    }

    @Override
    public void removeWaitingForCashVacation(Vacation vacation) {
        waitingForCash.removeWaitingForCashVacation(vacation);
    }

    @Override
    public ArrayList<String[]> getExchangeRequests(String currentUserName) {
        return exchanges.getExchangeRequests(currentUserName);
    }

    @Override
    public void confirmExchangeRequest(String[] vacation) {
        exchanges.confirmExchangeRequest(vacation);
    }

    @Override
    public void rejectExchangeRequest(String[] vacation) {
        exchanges.rejectExchangeRequest(vacation);
    }

    @Override
    public ArrayList<String[]> getExchangeConfirmations(String currentUserName) {
        return exchanges.getExchangeConfirmations(currentUserName);
    }

    @Override
    public void okExchange(String[] vacation) {
        exchanges.okExchange(vacation);
    }

    @Override
    public ArrayList<String[]> myVacations(String currentUserName) {
        return vacation.myVacations(currentUserName);
    }

    @Override
    public void requestForExchange(Vacation vacationIwant, String myVacationID, String currentUserName) {
        exchanges.requestForExchange(vacationIwant, myVacationID, currentUserName);
    }
}
