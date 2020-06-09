package controller;

import dal.IDALException;
import dal.IUserDAO;
import dal.UserDAOSQL;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


public class UserController {

    public UserDAOSQL getData() throws IDALException.DALException {
        return new UserDAOSQL();
    }

    public UserDTO activitySwitchUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.aktivitySwitchUser(user.getUserID());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


    public UserDTO createUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.createUser(user);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


    public UserDTO updateUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.updateUser(user);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


}
