
package Model;

import View.showVacationsController;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class Purchases {

    //public showVacationsController.Vacation vacation;


    public void purchaseVacationByPaypal(Vacation vacation, String currentUserName){
        insertToPurchasesTable(vacation, currentUserName);
        deleteFromVacationTable(vacation);
    }


    public boolean[] purchaseVacationByCreditCard(String creditCardNumber, String expirationDateMonth, String expirationDateYear, String cvv, Vacation vacation, String currentUserName){

        Pair<boolean[], Boolean> resultOfCheckFields = checkFields(creditCardNumber, expirationDateMonth, expirationDateYear, cvv);
        if (resultOfCheckFields.getValue()) return resultOfCheckFields.getKey();


        insertToPurchasesTable(vacation, currentUserName);
        deleteFromVacationTable(vacation);

        return resultOfCheckFields.getKey();
    }

    private Pair<boolean[], Boolean> checkFields (String creditCardNumber, String expirationDateMonth, String expirationDateYear, String cvv){

        boolean[] checkFields = new boolean[5];
        Boolean check = false;

        if(creditCardNumber.length() == 16){
            for(int i=0; i < 16; i++){
                if(!Character.isDigit(creditCardNumber.charAt(i)))
                    checkFields[0] = true;
            }
        }
        else
            checkFields[0] = true;


        ZoneId zonedId = ZoneId.of( "Israel" );
        String today = LocalDate.now( zonedId ).toString();
        String[] todayDateFields = today.split("-");


        if(expirationDateYear.length() == 4){
            for(int y=0; y < 4; y++){
                if(!Character.isDigit(expirationDateYear.charAt(y)))
                    checkFields[1] = true;
            }
            if((!checkFields[1]) && (Integer.parseInt(expirationDateYear) < Integer.parseInt(todayDateFields[0])))
                checkFields[3] = true;
        }
        else
            checkFields[1] = true;

        if(expirationDateMonth.length() == 2){
            if((!Character.isDigit(expirationDateMonth.charAt(0))) || (!Character.isDigit(expirationDateMonth.charAt(1))))
                checkFields[2] = true;
            if(!checkFields[2] && !(Integer.valueOf((expirationDateMonth)) <= 12 && Integer.valueOf((expirationDateMonth)) >= 1))
                checkFields[2] = true;
            if((!checkFields[2] && !checkFields[1]) && ((Integer.parseInt(expirationDateYear) == Integer.parseInt(todayDateFields[0])) && (Integer.parseInt(expirationDateMonth) < Integer.parseInt(todayDateFields[1]))))
                checkFields[3] = true;
        }
        else
            checkFields[2] = true;


        if(cvv.length() == 3){
            for(int k=0; k < 3; k++){
                if(!Character.isDigit(cvv.charAt(k)))
                    checkFields[4] = true;
            }
        }
        else
            checkFields[4] = true;

        for(int i= 0; i<5; i++)
            if(checkFields[i] == true) check = true;

        return new Pair<>(checkFields, check);
    }

    private void insertToPurchasesTable(Vacation vacation, String currentUserName){

        String sqlInsertToTable = "INSERT INTO Purchases(vacationID, seller, buyer) VALUES(?,?,?)";
        try (Connection conn = Model.connect();
             PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sqlInsertToTable)){

            pstmtInsertToTable.setInt(1, Integer.parseInt(vacation.getID()));
            pstmtInsertToTable.setString(2, vacation.getUserName());
            pstmtInsertToTable.setString(3, currentUserName);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteFromVacationTable(Vacation vacation){

        String sqlInsertToTable = "DELETE FROM Vacations WHERE vacationID = ?";
        try (Connection conn = Model.connect();
             PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sqlInsertToTable)){

            pstmtInsertToTable.setInt(1, Integer.parseInt(vacation.getID()));
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
