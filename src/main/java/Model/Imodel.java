package Model;

/**
 * Interface which describes the functions of the system
 */

public interface Imodel {
    /**
     * The function creates a new user to the system, registration form for the user includes the
     * next fiels: username, password, dateOfBirth, firstName, lastName, city
     * @param username
     * @param password
     * @param dateOfBirth
     * @param firstName
     * @param lastName
     * @param city
     * @return Boolean array which describes which of fields was not filled
     */
    boolean[] CreateNewUser(String username,String password,String dateOfBirth,String firstName,String lastName,String city);

    /**
     * The function allows search of users according to the unique user name of the user
     * @param usr
     * @return The fields of the given user as an array of String
     */
    String[] ReadUser(String usr);

    /**
     * The function updates the user's details by a specified form
     * @param username
     * @param password
     * @param dateOfBirth
     * @param firstName
     * @param lastName
     * @param city
     * @return Boolean array which describes which of fields was not filled
     */
    boolean[] UpdateUser(String oldusername,String username,String password,String dateOfBirth,String firstName,String lastName,String city);

    /**
     * The function deletes the user from the system permanently by a specified form
     * @param userName
     */
    void DeleteUser(String userName);

}
