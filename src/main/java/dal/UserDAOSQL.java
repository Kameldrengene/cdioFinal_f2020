package dal;

import dal.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAOSQL implements IUserDAO {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public UserDTO getUser(int userId) throws IDALException.DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM UserDTO where userID=" + userId); //Select all columns from userdto where userID is input

        UserDTO user = new UserDTO();
        //Try to insert columns into userDTO object
        try {
            rs.next();
            user.setUserID(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setIni(rs.getString("ini"));
            user.setCpr(rs.getString("cpr"));
            user.setPassword(rs.getString("password"));
            user.setJob(rs.getString("job"));
            user.setAktiv(rs.getBoolean("aktiv"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return user;
    }

    // -Mikkel
    public List<UserDTO> getRole(String role){

        List<UserDTO> userList = new ArrayList<>();

        try{
            db.connect();
            ResultSet rs = db.query("SELECT * FROM UserDTO where job ='" + role + "'");

            while(rs.next()){
                UserDTO user = new UserDTO();
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setIni(rs.getString("ini"));
                user.setCpr(rs.getString("cpr"));
                user.setPassword(rs.getString("password"));
                user.setJob(rs.getString("job"));
                user.setAktiv(rs.getBoolean("aktiv"));
                userList.add(user);
            }

            rs.close();
            db.close();


        //todo skal det håndteres anderledes?
        }catch(Exception e){
            e.printStackTrace();
        }

        return userList;
    }


    @Override
    public List<UserDTO> getData() throws IDALException.DALException { //We get a list of users here
        db.connect();
        ResultSet rs = db.query("SELECT * FROM UserDTO"); //Select all data from userdto


        List<UserDTO> userList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setIni(rs.getString("ini"));
                user.setCpr(rs.getString("cpr"));
                user.setPassword(rs.getString("password"));
                user.setJob(rs.getString("job"));
                user.setAktiv(rs.getBoolean("aktiv"));
                userList.add(user);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return userList;
    }

    @Override
    public UserDTO createUser(UserDTO user) throws IDALException.DALException { //We make a new user
        List<UserDTO> users = getData();
        db.connect();
        int idIndex = users.get(users.size()-1).getUserID()+1;
        user.setUserID(idIndex);
        db.update("insert into UserDTO (userID, userName, ini, cpr, password, job, aktiv) VALUES ('" + user.getUserID() + "', '" + user.getUserName() + "','" + user.getIni() + "','" + user.getCpr() + "','" + user.getPassword() + "','" + user.getJob() + "'," + user.getAktiv() + ")");
        db.close();
        return user;
    }

    @Override
    public void updateUser(UserDTO user) throws IDALException.DALException { //We update user with a new user
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM UserDTO where userID=" + user.getUserID());
            rs.next();
            if (rs.getInt("userID") == user.getUserID()) {
                db.update("UPDATE UserDTO SET userID = '" + user.getUserID() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE UserDTO SET userName = '" + user.getUserName() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE UserDTO SET ini = '" + user.getIni() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE UserDTO SET cpr = '" + user.getCpr() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE UserDTO SET password = '" + user.getPassword() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE UserDTO SET job = '" + user.getJob() + "' WHERE (userID = '" + user.getUserID() + "');");
                boolean b = user.getAktiv();
                int bool;
                if(b){
                    bool = 1;
                }else {
                    bool = 0;
                }
                db.update("UPDATE userdto SET aktiv = '" + bool + "' WHERE (userID = '" + user.getUserID() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    @Override
    public void aktivitySwitchUser(int userId) throws IDALException.DALException { //We switch the activity of the user
        db.connect();
        ResultSet rs = db.query("SELECT aktiv FROM UserDTO where userID=" + userId);
        boolean b;
        int boolVal = 0;
        try {
            rs.next();
            b = rs.getBoolean("aktiv");
            if(b){
                boolVal = 0;
            }else {
                boolVal = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.update("UPDATE UserDTO SET aktiv = " + boolVal + " WHERE userID=" + userId);
        db.close();
    }

    public boolean getActivity(int id) throws IDALException.DALException {
        UserDTO user = new UserDTO();
        try {
            db.connect();
            ResultSet rs = db.query("SELECT * FROM UserDTO where userID ='" + id + "'");
            rs.next();

            user.setUserID(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setIni(rs.getString("ini"));
            user.setCpr(rs.getString("cpr"));
            user.setPassword(rs.getString("password"));
            user.setJob(rs.getString("job"));
            user.setAktiv(rs.getBoolean("aktiv"));

            rs.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user.getAktiv();
    }
}
