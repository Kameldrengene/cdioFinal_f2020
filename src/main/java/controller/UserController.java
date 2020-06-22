package controller;

import Funktionalitet.UserFunc;

import dal.UserDAOSQL;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;


public class UserController {

    public final UserDAOSQL userDAOSQL;
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    //Constructor
    public UserController(){
        userDAOSQL = new UserDAOSQL();
    }

    //Get all users
    public List<UserDTO> getData()  {
        try {
            return userDAOSQL.getData();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    //Get all users
    public UserDTO getUser(int id) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            return db.getUser(id);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    //Get all users with specific role
    public List<UserDTO> getRole(String role)  {
        try {
            return userDAOSQL.getRole(role);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public void createUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        UserFunc userF = new UserFunc();
        try {
            if(userF.isUserOk(user)){
                db.createUser(user);
            }
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    //Create user
    public UserDTO updateUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        UserFunc userF = new UserFunc();
        try {
            if(userF.isUserOk(user)) {
                db.updateUser(user);
            }
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return user;
    }

    //Switch activity of user
    public UserDTO activitySwitchUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.aktivitySwitchUser(user.getUserID());
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return user;
    }

    //Get a users Activity
    public boolean CurrentActivity (int id){
        UserDAOSQL db = new UserDAOSQL();
        try {
            return db.getActivity(id);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }


    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }


}
