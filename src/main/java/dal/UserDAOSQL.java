package dal;

import dal.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAOSQL implements IUserDAO {
    public final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    /**
     * Get all users
     * @return Returnerer alle bruger
     * @throws SQLException
     */
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

    /**
     * Returnerer en bruger
     * @param userId Bruger ID
     * @return En specifik bruger som har angivet ID
     * @throws SQLException
     */
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

    /**
     * Get all users with specific role
     * @param role Bruger rolle
     * @return Returnerer alle bruger som har en specifik rolle
     * @throws SQLException
     */
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

    /**
     * Create user
     * @param user User Data Transfer Objekt
     * @return Den oprettede bruger
     * @throws SQLException
     */
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

    /**
     * Update user
     * @param user User Data Transfer Objekt
     * @throws SQLException
     */
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

    /**
     * Switch activity of user
     * @param userId Bruger ID
     * @throws SQLException
     */
    @Override
    public void aktivitySwitchUser(int userId) throws SQLException { //We switch the activity of the user
        db.connect();
        ResultSet rs = db.query("SELECT aktiv FROM userdto where userID=" + userId);
        boolean b;
        int boolVal;
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

    /**
     * Get a users Activity
     * @param id Bruger ID
     * @return Bruger aktitet status
     * @throws SQLException
     */
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

    /**
     * Set user udfra ResultSet og returner
     * @param rs Række Database
     * @param user User Data Transfer Objekt
     * @throws SQLException
     */
    private void setUser(ResultSet rs, UserDTO user) throws SQLException {
        user.setUserID(rs.getInt("userID"));
        user.setUserName(rs.getString("userName"));
        user.setIni(rs.getString("ini"));
        user.setPassword(rs.getString("password"));
        user.setJob(rs.getString("job"));
        user.setAktiv(rs.getBoolean("aktiv"));
    }

}
