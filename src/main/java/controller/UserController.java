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

    /**
     * Constructor
     */
    public UserController(){
        userDAOSQL = new UserDAOSQL();
    }

    /**
     * Get all users
     * @return Liste over alle bruger
     */
    public List<UserDTO> getData()  {
        try {
            return userDAOSQL.getData();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    /**
     * Returnerer en Bruger
     * @param id Bruger ID
     * @return En specifik bruger som har angivet ID
     */
    public UserDTO getUser(int id) {

        try {
            return userDAOSQL.getUser(id);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    /**
     * Get all users with specific role
     * @param role Bruger rolle
     * @return Returnerer alle bruger som har en specifik rolle
     */
    public List<UserDTO> getRole(String role)  {
        try {
            return userDAOSQL.getRole(role);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    /**
     * Create a user
     * @param user User Data Transfer Objekt
     */
    public void createUser(UserDTO user) {
        UserFunc userF = new UserFunc();
        try {
            if(userF.isUserOk(user)){
                userDAOSQL.createUser(user);
            }
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    /**
     * Update user
     * @param user User Data Transfer Objekt
     * @return created User
     */
    public UserDTO updateUser(UserDTO user) {
        UserFunc userF = new UserFunc();
        try {
            if(userF.isUserOk(user)) {
                userDAOSQL.updateUser(user);
            }
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return user;
    }

    /**
     * Switch activity of user
     * @param user User Data Transfer Objekt
     * @return User object
     */
    public UserDTO activitySwitchUser(UserDTO user) {
        try {
            userDAOSQL.aktivitySwitchUser(user.getUserID());
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return user;
    }

    /**
     * Get a users Activity
     * @param id Bruger ID
     * @return Bruger aktitet status
     */
    public boolean CurrentActivity (int id){
        try {
            return userDAOSQL.getActivity(id);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    /**
     * Kaster en Exception videre hvis der sker en fejl i systemet
     * @param status Web status
     * @param msg Besked der skal stå i fejl meddelelse
     * @return returner en Webapplication Exception
     */
    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }


}
