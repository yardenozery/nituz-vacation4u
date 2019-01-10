package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WaitingForCash {

    public void addWaitingForCashVacation(Vacation vacation, String currentUserName) {
        String sqlInsertToTable = "INSERT INTO WaitingForCash(vacationID, seller, buyer) VALUES(?,?,?)";
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

    public ArrayList<String[]> getCashArrivedVacations(String currentUserName) {
        ArrayList<String[]> cashArrived = new ArrayList<>();
        String sql = "SELECT * FROM WaitingForCash WHERE seller = ?";
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
                cashArrived.add(res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cashArrived;
    }

    public void removeWaitingForCashVacation(Vacation vacation) {
        Integer vacationID = Integer.parseInt(vacation.getID());
        String sql = "DELETE FROM WaitingForCash WHERE vacationID = ?";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable = conn.prepareStatement(sql)) {
            pstmtInsertToTable.setInt(1, vacationID);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
