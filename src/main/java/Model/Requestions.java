package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Requestions {

    public ArrayList<String[]> getRequests(String currentUserName){
        ArrayList<String[]> requests = new ArrayList<>();
        String sql = "SELECT * FROM Requests WHERE seller = ?";
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
                requests.add(res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return requests;
    }

    public String[] getVacation(String vacationID){
        String[] vacation = new String[17];
        String sql = "SELECT * FROM Vacations WHERE vacationID = ?";
        try (Connection conn = Model.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1, Integer.parseInt(vacationID));
            ResultSet rs  = pstmt.executeQuery();
            while(rs.next()){
                vacation[0] = String.valueOf(rs.getString(1));
                vacation[1] = rs.getString(2);
                vacation[2] = rs.getString(3);
                vacation[3] = rs.getString(4);
                vacation[4] = rs.getString(5);
                vacation[5] = rs.getString(6);
                vacation[6] = rs.getString(7);
                vacation[7] = rs.getString(8);
                vacation[8] = rs.getString(9);
                vacation[9] = rs.getString(10);
                vacation[10] = rs.getString(11);
                vacation[11] = rs.getString(12);
                vacation[12] = rs.getString(13);
                vacation[13] = rs.getString(14);
                vacation[14] = rs.getString(15);
                vacation[15] = rs.getString(16);
                vacation[16] = rs.getString(17);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vacation;
    }

    public void rejectVacation(String[] vacation){
        Integer vacationID = Integer.parseInt(vacation[0]);
        String sql = "DELETE FROM Requests  WHERE vacationID = ? AND seller = ? AND buyer = ?";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, vacationID);
            pstmtInsertToTable.setString(2, vacation[1]);
            pstmtInsertToTable.setString(3, vacation[2]);
            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addRequest(Vacation vacation, String currentUserName){
        Integer vacationID = Integer.parseInt(vacation.getID());
        String sql = "INSERT INTO Requests(vacationID, seller, buyer) VALUES(?, ?,?)";
        try (Connection conn = Model.connect(); PreparedStatement pstmtInsertToTable  = conn.prepareStatement(sql)){

            pstmtInsertToTable.setInt(1, vacationID);
            pstmtInsertToTable.setString(2, vacation.getUserName());
            pstmtInsertToTable.setString(3, currentUserName);

            pstmtInsertToTable.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
