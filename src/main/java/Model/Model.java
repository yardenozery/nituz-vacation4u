
package Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The class implements the Imodel interface
 */
public class Model implements Imodel {

    Users user;

    /**
     * A constructor which initialize the variable "Users" and apply the function "CreateDB"
     */
    public Model() {
        user = new Users();
        this.CreateDB();
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
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                String userTable = "CREATE TABLE IF NOT EXISTS Users(\n"
                        + "	username text PRIMARY KEY,\n"
                        + "	password text NOT NULL,\n"
                        + "	dateOfBirth text NOT NULL,\n"
                        + "	firstName text NOT NULL,\n"
                        + "	lastName text NOT NULL,\n"
                        + "	city text NOT NULL);";
                createNewTable(url, userTable);
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
}
