package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Confirmations {

    public void confirmVacation(String[] vacation){ //  vacation = ID, seller, buyer
        Integer vacationID = Integer.parseInt(vacation[0]);
        String sql = "INSERT INTO Confirmations(vacationID, seller, buyer) VALUES(?, ?,?)";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, vacationID);
            pstmtInsertToTable.setString(2, vacation[1]);
            pstmtInsertToTable.setString(3, vacation[2]);

            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        removeAllRequests(vacationID);
        removeAllExchangeRequests(vacationID);
    }

    private void removeAllExchangeRequests(Integer vacationID) {
        String sql = "DELETE FROM Exchanges  WHERE requestToVacationID = ?";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, vacationID);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeAllRequests(Integer vacationID){
        String sql = "DELETE FROM Requests  WHERE vacationID = ?";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, vacationID);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String[]> getConfirmations(String currentUserName){
        ArrayList<String[]> confirmations = new ArrayList<>();
        String sql = "SELECT * FROM Confirmations WHERE buyer = ?";
        try (Connection conn = Model.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1, currentUserName);
            ResultSet rs  = pstmt.executeQuery();
            String[] res;
            while(rs.next()){
                res = new String[3];
                res[0] = String.valueOf(rs.getInt(1));
                res[1] = rs.getString(2);
                res[2] = rs.getString(3);
                confirmations.add(res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return confirmations;
    }

    public void removeConfirmation(Vacation vacation, String currentUserName){
        Integer vacationID = Integer.parseInt(vacation.getID());
        String sql = "DELETE FROM Confirmations  WHERE vacationID = ?";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, vacationID);
            //pstmtInsertToTable.setString(2, vacation.getUserName());
            //pstmtInsertToTable.setString(3, currentUserName);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
