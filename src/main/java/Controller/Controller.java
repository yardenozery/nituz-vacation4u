package Controller;

import Model.Model;

public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public boolean[] CreateNewUser(String username,String password,String dateOfBirth,String firstName,String lastName,String city){
        return model.CreateNewUser(username, password, dateOfBirth, firstName, lastName, city);
    }

    public String[] ReadUser(String usr){
        return model.ReadUser(usr);
    }

    public boolean[] UpdateUser(String oldusername, String username,String password,String dateOfBirth,String firstName,String lastName,String city){
        return model.UpdateUser(oldusername, username, password, dateOfBirth, firstName, lastName, city);
    }

    public void DeleteUser(String userName){
        model.DeleteUser(userName);
    }

}
