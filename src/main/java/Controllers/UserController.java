package Controllers;

import Funktionalitet.UserFunc;

import Data.UserDAOSQL;
import Data.dto.UserDTO;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.List;


public class UserController {

    public final UserDAOSQL userDAOSQL;

    //Constructor
    public UserController(){
        userDAOSQL = new UserDAOSQL();
    }

    public List<UserDTO> getData() throws SQLException {
        return userDAOSQL.getData();
    }


    public List<UserDTO> getRole(String role) throws WebApplicationException {
        return userDAOSQL.getRole(role);
    }


    public UserDTO activitySwitchUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.aktivitySwitchUser(user.getUserID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean CurrentActivity (int id){
        UserDAOSQL db = new UserDAOSQL();
        try {
            return db.getActivity(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserDTO getUser(int id) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            return db.getUser(id);
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
