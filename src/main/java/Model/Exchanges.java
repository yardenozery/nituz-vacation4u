package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exchanges {
    public ArrayList<String[]> getExchangeRequests(String currentUserName) {
        ArrayList<String[]> exchangeRequests = new ArrayList<>();
        String sql = "SELECT * FROM Exchanges WHERE requestTo = ? AND confirmYet = false";
        try (Connection conn = Model.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1, currentUserName);
            ResultSet rs  = pstmt.executeQuery();
            String[] res;
            while(rs.next()){
                res = new String[4];
                res[0] = String.valueOf(rs.getInt(1));
                res[1] = rs.getString(2);
                res[2] = String.valueOf(rs.getInt(3));
                res[3] = rs.getString(4);
                exchangeRequests.add(res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exchangeRequests;
    }

    public void confirmExchangeRequest(String[] vacation) {
        String sql = "UPDATE Exchanges SET requestFromVacationID = ? , "
                + "requestFrom = ?, "
                + "requestToVacationID = ?, "
                + "requestTo = ?, "
                + "confirmYet = true, "
                + "executed = false "
                + "WHERE requestFromVacationID = ? AND requestToVacationID = ?";

        try (Connection conn = Model.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, Integer.parseInt(vacation[0]));
            pstmt.setString(2, vacation[1]);
            pstmt.setInt(3, Integer.parseInt(vacation[2]));
            pstmt.setString(4, vacation[3]);
            pstmt.setInt(5, Integer.parseInt(vacation[0]));
            pstmt.setInt(6, Integer.parseInt(vacation[2]));
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        removeAllRequests(Integer.parseInt(vacation[2]));
        removeAllExchangeRequests(Integer.parseInt(vacation[2]));
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

    private void removeAllExchangeRequests(Integer vacationID) {
        String sql = "DELETE FROM Exchanges  WHERE requestToVacationID = ? AND confirmYet = false";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, vacationID);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void rejectExchangeRequest(String[] vacation) {
        String sql = "DELETE FROM Exchanges  WHERE requestFromVacationID = ? AND requestToVacationID = ?";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, Integer.parseInt(vacation[0]));
            pstmtInsertToTable.setInt(2, Integer.parseInt(vacation[2]));
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String[]> getExchangeConfirmations(String currentUserName) {
        ArrayList<String[]> exchangeConfirmations = new ArrayList<>();
        String sql = "SELECT * FROM Exchanges WHERE requestFrom = ? AND confirmYet = true AND executed = false";
        try (Connection conn = Model.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1, currentUserName);
            ResultSet rs  = pstmt.executeQuery();
            String[] res;
            while(rs.next()){
                res = new String[4];
                res[0] = String.valueOf(rs.getInt(1));
                res[1] = rs.getString(2);
                res[2] = String.valueOf(rs.getInt(3));
                res[3] = rs.getString(4);
                exchangeConfirmations.add(res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exchangeConfirmations;
    }

    public void okExchange(String[] vacation) {
        String sql = "UPDATE Exchanges SET requestFromVacationID = ? , "
                + "requestFrom = ?, "
                + "requestToVacationID = ?, "
                + "requestTo = ?, "
                + "confirmYet = true, "
                + "executed = true "
                + "WHERE requestFromVacationID = ? AND requestToVacationID = ?";

        try (Connection conn = Model.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, Integer.parseInt(vacation[0]));
            pstmt.setString(2, vacation[1]);
            pstmt.setInt(3, Integer.parseInt(vacation[2]));
            pstmt.setString(4, vacation[3]);
            pstmt.setInt(5, Integer.parseInt(vacation[0]));
            pstmt.setInt(6, Integer.parseInt(vacation[2]));
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void requestForExchange(Vacation vacationIwant, String myVacationID, String currentUserName) {
        String sql = "INSERT INTO Exchanges(requestFromVacationID, requestFrom, requestToVacationID, requestTo, confirmYet, executed) VALUES(?, ?, ?, ?, false, false)";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, Integer.parseInt(myVacationID));
            pstmtInsertToTable.setString(2, currentUserName);
            pstmtInsertToTable.setInt(3, Integer.parseInt(vacationIwant.getID()));
            pstmtInsertToTable.setString(4, vacationIwant.getUserName());

            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
