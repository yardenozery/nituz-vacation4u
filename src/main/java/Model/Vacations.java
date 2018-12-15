package Model;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The class describes the functions on the vacations in the system.
 */
public class Vacations {

    private int vacationID;

    public Vacations() {
        vacationID = 0 ;
        int isEmpty = checkTableIsEmpty();
        if(isEmpty  == 0)
            vacationID = 1;

        if(vacationID != 1)
            vacationID = checkMaxID();
    }

    public ArrayList<String[]> SearchVacationByFields(String origin, String destination, String departureDate, String arrivalDate, String minPrice, String maxPrice){

        String sql;
        ArrayList<String[]> vacations = new ArrayList<>();
        ArrayList<ArrayList<String[]>> vacationsFiltered = new ArrayList<>();
        ArrayList<String[]> originFilter = new ArrayList<>();
        ArrayList<String[]> destinationFilter = new ArrayList<>();
        ArrayList<String[]> departureDateFilter = new ArrayList<>();
        ArrayList<String[]> arrivalDateFilter = new ArrayList<>();
        ArrayList<String[]> minPriceFilter = new ArrayList<>();
        ArrayList<String[]> maxPriceFilter = new ArrayList<>();
        vacationsFiltered.add(originFilter);
        vacationsFiltered.add(destinationFilter);
        vacationsFiltered.add(departureDateFilter);
        vacationsFiltered.add(arrivalDateFilter);
        vacationsFiltered.add(minPriceFilter);
        vacationsFiltered.add(maxPriceFilter);



        if(origin != null && !origin.equals("")){
            sql = "SELECT * FROM Vacations WHERE origin = ?";
            partOfResultSearch(vacationsFiltered.get(0), sql, origin);
        }
        if(destination != null && !destination.equals("")){
            sql = "SELECT * FROM Vacations WHERE destination = ?";
            partOfResultSearch(vacationsFiltered.get(1), sql, destination);
        }
        if(!departureDate.equals("")){
            sql = "SELECT * FROM Vacations WHERE departureDate == ?";
            partOfResultSearch(vacationsFiltered.get(2), sql, departureDate);
        }
        if(!arrivalDate.equals("")){
            sql = "SELECT * FROM Vacations WHERE arrivalDate == ?";
            partOfResultSearch(vacationsFiltered.get(3), sql, arrivalDate);
        }
        if(minPrice != null && !minPrice.equals("")){
            sql = "SELECT * FROM Vacations WHERE price >= ?";
            partOfResultSearch(vacationsFiltered.get(4), sql, minPrice);
        }
        if(maxPrice != null && !maxPrice.equals("")){
            sql = "SELECT * FROM Vacations WHERE price <= ?";
            partOfResultSearch(vacationsFiltered.get(5), sql, maxPrice);
        }

        int minVacationPerFilter = Integer.MAX_VALUE;
        int minIndex = 0;
        int index=0;
        int i;

        for(i=0; i<6; i++){
            if(vacationsFiltered.get(index).size() != 0) {
                if(vacationsFiltered.get(index).size() < minVacationPerFilter){
                    minVacationPerFilter = vacationsFiltered.get(index).size();
                    minIndex = index;
                }
            }
            else{
                vacationsFiltered.remove(index);
                index--;
            }
            index++;
        }

        if(minVacationPerFilter == Integer.MAX_VALUE)
            return vacations;


        for(int j=0; j<vacationsFiltered.get(minIndex).size(); j++){
            String[] vacation = vacationsFiltered.get(minIndex).get(j);
            boolean fittedVacation = true;
            for(i=0; i<vacationsFiltered.size() && fittedVacation; i++){
                if(!SameVacation(vacation, vacationsFiltered.get(i).get(j)))
                    fittedVacation = false;
            }
            if(fittedVacation)
                vacations.add(vacation);

            //if((SameVacation(vacation, vacationsFiltered.get(0).get(j))) && (SameVacation(vacation, vacationsFiltered.get(1).get(j))) &&
            //        (SameVacation(vacation, vacationsFiltered.get(2).get(j))) && (SameVacation(vacation, vacationsFiltered.get(3).get(j))) &&
            //        (SameVacation(vacation, vacationsFiltered.get(4).get(j))) && (SameVacation(vacation, vacationsFiltered.get(5).get(j))))

            //   vacations.add(vacation);
        }

        return vacations;
    }

    private void partOfResultSearch(ArrayList<String[]> filteredVacations, String sql, String filteredBy){
        try (Connection conn = Model.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1, filteredBy);
            ResultSet rs  = pstmt.executeQuery();
            String[] res;
            while(rs.next()){
                res = new String[17];
                res[0] = rs.getString(1);
                res[1] = rs.getString(2);
                res[2] = rs.getString(3);
                res[3] = rs.getString(4);
                res[4] = rs.getString(5);
                res[5] = rs.getString(6);
                res[6] = rs.getString(7);
                res[7] = rs.getString(8);
                res[8] = rs.getString(9);
                res[9] = rs.getString(10);
                res[10] = rs.getString(11);
                res[11] = rs.getString(12);
                res[12] = rs.getString(13);
                res[13] = rs.getString(14);
                res[14] = rs.getString(15);
                res[15] = rs.getString(16);
                res[16] = rs.getString(17);
                filteredVacations.add(res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean SameVacation(String[] v1, String[] v2){
        if(v1[0] != v2[0])
            return false;
        return true;
    }

    public boolean[] CreateNewVacation(String airLine, String departureDate, String origin, String destination, String adultTicketsNumber, String childTicketsNumber,
                                       String babyTicketsNumber, String flightBackIncluded, String arrivalDate, String luggageIncluded, String price, String vacationType,
                                       String hotelIncluded, String hotelGrade, String userName, String freeText) {

        Pair<boolean[], Boolean> resultOfCheckFields = checkFields(airLine, departureDate, origin, destination, adultTicketsNumber, childTicketsNumber,
                babyTicketsNumber, flightBackIncluded, arrivalDate, luggageIncluded, price);
        if (resultOfCheckFields.getValue()) return resultOfCheckFields.getKey();


        //int maxIDVacation = 0 ;
        //int isEmpty = checkTableIsEmpty();
        //if(isEmpty  == -1)
        //    return null;
        //if(isEmpty  == 0){
        //    maxIDVacation = 1;
        //}
//
        //if(maxIDVacation != 1){
        //    maxIDVacation = checkMaxID();
        //    if(maxIDVacation  == -1)
        //        return null;
        //}

        insertToVacationTable(vacationID, airLine, departureDate, origin, destination, adultTicketsNumber, childTicketsNumber,
                babyTicketsNumber, flightBackIncluded, arrivalDate, luggageIncluded, price, vacationType, hotelIncluded, hotelGrade, userName, freeText);

        vacationID++;
        return resultOfCheckFields.getKey();
    }

    private Integer checkTableIsEmpty(){
        String sqlCheckTableIsEmpty = "SELECT Count(*) FROM Vacations";
        try (Connection conn = Model.connect();
             PreparedStatement pstmtIsEmpty  = conn.prepareStatement(sqlCheckTableIsEmpty)) {
            ResultSet rsIsEmpty = pstmtIsEmpty.executeQuery();
            //return (Integer) rsIsEmpty.getObject("vacationID");
            return (Integer) rsIsEmpty.getObject(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    private Integer checkMaxID(){

        String sqlCheckMaxID = "SELECT Max(vacationID) FROM Vacations";

        try (Connection conn = Model.connect();
             PreparedStatement pstmtMaxID  = conn.prepareStatement(sqlCheckMaxID)) {
            ResultSet rsMaxID = pstmtMaxID.executeQuery();
            //return (Integer) rsMaxID.getObject("vacationID")+1;
            return (Integer) rsMaxID.getObject(1)+1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    private void insertToVacationTable(Integer vacationIDString,String airLine, String departureDate, String origin, String destination, String adultTicketsNumber, String childTicketsNumber,
                                       String babyTicketsNumber, String flightBackIncluded, String arrivalDate, String luggageIncluded, String price, String vacationType,
                                       String hotelIncluded, String hotelGrade, String userName, String freeText){

        String sqlInsertToTable = "INSERT INTO Vacations(vacationID, airLine, departureDate, origin, destination, adultTicketsNumber, childTicketsNumber,\n" +
                "                babyTicketsNumber, flightBackIncluded, arrivalDate, luggageIncluded, price, vacationType, hotelIncluded, hotelGrade, userName, freeText) VALUES(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = Model.connect();
             PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sqlInsertToTable)){

            pstmtInsertToTable.setInt(1, vacationIDString);
            pstmtInsertToTable.setString(2, airLine);
            pstmtInsertToTable.setString(3, departureDate);
            pstmtInsertToTable.setString(4, origin);
            pstmtInsertToTable.setString(5, destination);
            pstmtInsertToTable.setInt(6, Integer.valueOf(adultTicketsNumber));
            pstmtInsertToTable.setInt(7, Integer.valueOf(childTicketsNumber));
            pstmtInsertToTable.setInt(8, Integer.valueOf(babyTicketsNumber));
            pstmtInsertToTable.setString(9, flightBackIncluded);
            pstmtInsertToTable.setString(10, arrivalDate);
            pstmtInsertToTable.setString(11, luggageIncluded);
            pstmtInsertToTable.setInt(12, Integer.valueOf(price));
            pstmtInsertToTable.setString(13, vacationType);
            pstmtInsertToTable.setString(14, hotelIncluded);
            pstmtInsertToTable.setString(15, hotelGrade);
            pstmtInsertToTable.setString(16, userName);
            pstmtInsertToTable.setString(17, freeText);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Pair<boolean[], Boolean> checkFields (String airLine, String departureDate, String origin, String destination, String adultTicketsNumber, String childTicketsNumber,
                                                  String babyTicketsNumber, String flightBackIncluded, String arrivalDate, String luggageIncluded, String price){

        boolean[] checkFields = new boolean[11];
        Boolean check = false;

        if(airLine.equals("")) checkFields[0] = true;
        if(departureDate.equals("")) checkFields[1] = true;
        if(origin.equals("")) checkFields[2] = true;
        if(destination.equals("")) checkFields[3] = true;
        if(adultTicketsNumber.equals("")) checkFields[4] = true;
        if(childTicketsNumber.equals("")) checkFields[5] = true;
        if(babyTicketsNumber.equals("")) checkFields[6] = true;
        if(flightBackIncluded.equals("")) checkFields[7] = true;
        if(flightBackIncluded.equals("true"))
            if(arrivalDate.equals(""))
                checkFields[8] = true;
        if(luggageIncluded.equals("")) checkFields[9] = true;
        if(price.equals("")) checkFields[10] = true;

        for(int i= 0; i<11; i++)
            if(checkFields[i] == true) check = true;

        return new Pair<>(checkFields, check);
    }

    public void removeVacation(Vacation vacation){

        Integer vacationID = Integer.parseInt(vacation.getID());
        String sql = "DELETE FROM Vacations  WHERE vacationID = ?";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, vacationID);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
