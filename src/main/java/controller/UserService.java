package controller;

import dal.IUserDAO;
import dal.UserDAOSQL;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class UserService {

    public UserDAOSQL getData() throws IUserDAO.DALException {
        return new UserDAOSQL();
    }

    public UserDTO activitySwitchUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.aktivitySwitchUser(user.getUserID());
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


    public UserDTO createUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.createUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


    public UserDTO updateUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.updateUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


}
