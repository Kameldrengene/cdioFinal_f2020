package Controllers;

import Funktionalitet.UserFunc;

import Data.UserDAOSQL;
import Data.dto.UserDTO;

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

    public List<UserDTO> getData()  {
        try {
            return userDAOSQL.getData();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public List<UserDTO> getRole(String role)  {
        try {
            return userDAOSQL.getRole(role);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public UserDTO activitySwitchUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.aktivitySwitchUser(user.getUserID());
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return user;
    }

    public boolean CurrentActivity (int id){
        UserDAOSQL db = new UserDAOSQL();
        try {
            return db.getActivity(id);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public UserDTO getUser(int id) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            return db.getUser(id);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public UserDTO createUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        UserFunc userF = new UserFunc();
        try {
            if(userF.isUserOk(user)){
                db.createUser(user);
            }
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return user;
    }

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

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }


}
