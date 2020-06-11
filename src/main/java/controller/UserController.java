package controller;

import Funktionalitet.UserFunc;
import dal.IDALException;
import dal.UserDAOSQL;
import dal.dto.UserDTO;

import java.util.List;


public class UserController {

    private UserDAOSQL userDAO;

    //Constructor
    public UserController(){
        userDAO = new UserDAOSQL();
    }

    public List<UserDTO> getData() throws IDALException.DALException {
        return userDAO.getData();
    }

    public List<UserDTO> getRole(String role){
        return userDAO.getRole(role);
    }


    public UserDTO activitySwitchUser(UserDTO user) {
        try {
            userDAO.aktivitySwitchUser(user.getUserID());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean getActivity(int userID){
        try {
            return userDAO.getActivity(userID);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserDTO getUser(int userID) {
        try {
            return userDAO.getUser(userID);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO opretUser(UserDTO user) {
        UserFunc userF = new UserFunc();
        try {
            if(userF.isUserOk(user)){
                userDAO.createUser(user);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


    public UserDTO opdaterUser(UserDTO user) {
        UserFunc userF = new UserFunc();
        try {
            if(userF.isUserOk(user)) {
                userDAO.updateUser(user);
            }
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


}
