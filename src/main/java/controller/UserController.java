package controller;

import Funktionalitet.UserFunc;
import dal.IDALException;
import dal.IUserDAO;
import dal.UserDAOSQL;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


public class UserController {

    private UserDAOSQL userDAOSQL;

    //Constructor
    public UserController(){
        userDAOSQL = new UserDAOSQL();
    }

    public List<UserDTO> getData() throws IDALException.DALException {
        return userDAOSQL.getData();
    }


    public List<UserDTO> getRole(String role){
        return userDAOSQL.getRole(role);
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

    public boolean CurrentActivity (int id){
        UserDAOSQL db = new UserDAOSQL();
        try {
            return db.getActivity(id);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserDTO getUser(int id) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            return db.getUser(id);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO createUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        UserFunc userF = new UserFunc();
        try {
            if(userF.isUserOk(user)){
                db.createUser(user);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
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
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


}
