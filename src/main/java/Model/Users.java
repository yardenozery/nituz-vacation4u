package Model;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * The class describes the functions of the users use the system
 */
public class Users {

    /**
     * A private function which checks if the password is a valid password and if there is a field
     * which did not filled
     * @param username
     * @param password
     * @param dateOfBirth
     * @param firstName
     * @param lastName
     * @param city
     * @return A pair of a boolean array and the value of the variable "check"
     */
    private Pair<boolean[], Boolean> checkFields (String username,String password,String dateOfBirth,String firstName,String lastName,String city){

        boolean[] checkFields = new boolean[7];
        Boolean check = false;
        if(password.length() < 8) checkFields[1] = true;

        if(username.equals("")) checkFields[0] = true;
        if(dateOfBirth.equals("")) checkFields[2] = true;
        if(firstName.equals("")) checkFields[3] = true;
        if(lastName.equals("")) checkFields[4] = true;
        if(city.equals("")) checkFields[5] = true;


        ZoneId zonedId = ZoneId.of( "Israel" );
        String today = LocalDate.now( zonedId ).toString();
        String[] stringtodayDateSplit = today.split("-");
        int[] inttodayDateSplit = {Integer.parseInt(stringtodayDateSplit[0]), Integer.parseInt(stringtodayDateSplit[1]), Integer.parseInt(stringtodayDateSplit[2])};
        String[] stringDateSplit = dateOfBirth.split("-");
        int[] intDateSplit = {Integer.parseInt(stringDateSplit[0]), Integer.parseInt(stringDateSplit[1]), Integer.parseInt(stringDateSplit[2])};
        if(((inttodayDateSplit[0] - intDateSplit[0]) <18) || (((inttodayDateSplit[0] - intDateSplit[0]) == 18) && (inttodayDateSplit[1] < intDateSplit[1])) ||
                (((inttodayDateSplit[0] - intDateSplit[0]) == 18) && (inttodayDateSplit[1] == intDateSplit[1]) && (inttodayDateSplit[2] < intDateSplit[2]))){

            checkFields[6] = true;
        }

        for(int i= 0; i<7; i++)
            if(checkFields[i] == true) check = true;

        return new Pair<>(checkFields, check);
    }

    public boolean[] CreateNewUser(String username,String password,String dateOfBirth,String firstName,String lastName,String city) {


        Pair<boolean[], Boolean> resultOfCheckFields = checkFields(username, password, dateOfBirth, firstName, lastName, city);
        if (resultOfCheckFields.getValue()) return resultOfCheckFields.getKey();



        String sql = "INSERT INTO Users(username,password,dateOfBirth,firstName,lastName,city) VALUES(?,?,?,?,?,?)";

        try (Connection conn = Model.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, dateOfBirth);
            pstmt.setString(4, firstName);
            pstmt.setString(5, lastName);
            pstmt.setString(6, city);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return resultOfCheckFields.getKey();

    }


    public String[] ReadUser(String usr) {
        String sql = "SELECT username, password, dateOfBirth, firstName, lastName, city FROM Users WHERE username = ?";
        try (Connection conn = Model.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,usr);
            ResultSet rs  = pstmt.executeQuery();
            if(rs.isClosed()) {
                String[] res = new String[1];
                res[0] = "The given username is not exist";
                return res;
            }
            String[] res = new String[6];
            res[0] = rs.getString(1);
            res[1] = rs.getString(2);
            res[2] = rs.getString(3);
            res[3] = rs.getString(4);
            res[4] = rs.getString(5);
            res[5] = rs.getString(6);
            return res;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public boolean[] UpdateUser(String oldusername, String username,String password,String dateOfBirth,String firstName,String lastName,String city) {



        Pair<boolean[], Boolean> resultOfCheckFields = checkFields(username, password, dateOfBirth, firstName, lastName, city);
        if (resultOfCheckFields.getValue()) return resultOfCheckFields.getKey();


        String sql = "UPDATE Users SET username = ? , "
                + "password = ?, "
                + "dateOfBirth = ?, "
                + "firstName = ?, "
                + "lastName = ?, "
                + "city = ? "
                + "WHERE username = ?";

        try (Connection conn = Model.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, dateOfBirth);
            pstmt.setString(4, firstName);
            pstmt.setString(5, lastName);
            pstmt.setString(6, city);
            pstmt.setString(7, oldusername);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return resultOfCheckFields.getKey();

    }

    public void DeleteUser(String userName) {
        String sql = "DELETE FROM Users WHERE userName = ?";

        try (Connection conn = Model.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}