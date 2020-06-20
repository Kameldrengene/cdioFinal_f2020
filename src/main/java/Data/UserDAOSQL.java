package Data;

import Data.dto.UserDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAOSQL implements IUserDAO {
    public final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    //Get all users
    @Override
    public List<UserDTO> getData() throws SQLException { //We get a list of users here
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto"); //Select all data from userdto


        List<UserDTO> userList = new ArrayList<>();
        //We do as in getUser, except we make new user until rs is empty
        while (rs.next()) {
            UserDTO user = new UserDTO();
            setUser(rs, user);
            userList.add(user);
        }
        rs.close();
        db.close();
        return userList;
    }

    //Get all users
    @Override
    public UserDTO getUser(int userId) throws SQLException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto where userID=" + userId); //Select all columns from userdto where userID is input

        UserDTO user = new UserDTO();
        //Try to insert columns into userDTO object
        rs.next();
        setUser(rs, user);
        rs.close();
        db.close();
        return user;
    }

    // -Mikkel
    //Get all users with specific role
    @Override
    public List<UserDTO> getRole(String role) throws SQLException {

        List<UserDTO> userList = new ArrayList<>();

        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto where job ='" + role + "'");

        while(rs.next()){
            UserDTO user = new UserDTO();
            setUser(rs, user);
            userList.add(user);
        }
        rs.close();
        db.close();

        return userList;
    }

    //Create user
    @Override
    public UserDTO createUser(UserDTO user) throws SQLException { //We make a new user
        List<UserDTO> users = getData();
        db.connect();
        int idIndex = users.get(users.size()-1).getUserID()+1;
        user.setUserID(idIndex);
        db.update("insert into userdto (userID, userName, ini, password, job, aktiv) VALUES ('" + user.getUserID() + "', '" + user.getUserName() + "','" + user.getIni() + "','" + user.getPassword() + "','" + user.getJob() + "'," + user.getAktiv() + ")");
        db.close();
        return user;
    }

    //Update user
    @Override
    public void updateUser(UserDTO user) throws SQLException { //We update user with a new user
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto where userID=" + user.getUserID());
        rs.next();
        if (rs.getInt("userID") == user.getUserID()) {
            db.update("UPDATE userdto SET userID = '" + user.getUserID() + "' WHERE (userID = '" + user.getUserID() + "');");
            db.update("UPDATE userdto SET userName = '" + user.getUserName() + "' WHERE (userID = '" + user.getUserID() + "');");
            db.update("UPDATE userdto SET ini = '" + user.getIni() + "' WHERE (userID = '" + user.getUserID() + "');");
            db.update("UPDATE userdto SET password = '" + user.getPassword() + "' WHERE (userID = '" + user.getUserID() + "');");
            db.update("UPDATE userdto SET job = '" + user.getJob() + "' WHERE (userID = '" + user.getUserID() + "');");
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
        db.close();
    }

    //Switch activity of user
    @Override
    public void aktivitySwitchUser(int userId) throws SQLException { //We switch the activity of the user
        db.connect();
        ResultSet rs = db.query("SELECT aktiv FROM userdto where userID=" + userId);
        boolean b;
        int boolVal = 0;
        rs.next();
        b = rs.getBoolean("aktiv");
        if(b){
            boolVal = 0;
        }else {
            boolVal = 1;
        }

        db.update("UPDATE userdto SET aktiv = " + boolVal + " WHERE userID=" + userId);
        db.close();
    }

    //Get a users Activity
    @Override
    public boolean getActivity(int id) throws SQLException {
        UserDTO user = new UserDTO();
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto where userID ='" + id + "'");
        rs.next();

        setUser(rs, user);

        rs.close();
        db.close();

        return user.getAktiv();
    }

    //todo comment
    private UserDTO setUser(ResultSet rs, UserDTO user) throws SQLException {
        user.setUserID(rs.getInt("userID"));
        user.setUserName(rs.getString("userName"));
        user.setIni(rs.getString("ini"));
        user.setPassword(rs.getString("password"));
        user.setJob(rs.getString("job"));
        user.setAktiv(rs.getBoolean("aktiv"));
        return user;
    }

}
