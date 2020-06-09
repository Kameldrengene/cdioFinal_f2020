package dal;

import services.SQLDatabaseIO;
import dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAOSQL implements IUserDAO {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public UserDTO getUser(int userId) throws DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto where userID=" + userId); //Select all columns from userdto where userID is input

        UserDTO user = new UserDTO();
        //Try to insert columns into userDTO object
        try {
            rs.next();
            user.setUserID(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setIni(rs.getString("ini"));
            user.setCpr(rs.getString("cpr"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setAktiv(rs.getBoolean("aktiv"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return user;
    }

    @Override
    public List<UserDTO> getData() throws DALException { //We get a list of users here
        db.connect();
        ResultSet rs = db.query("SELECT * FROM userdto"); //Select all data from userdto


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
                user.setRole(rs.getString("role"));
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
    public void createUser(UserDTO user) throws DALException { //We make a new user
        db.connect();
        db.update("insert into userdto (userID, userName, ini, cpr, password, role, aktiv) VALUE ('" + user.getUserID() + "','" + user.getUserName() + "','" + user.getIni() + "','" + user.getCpr() + "','" + user.getPassword() + "','" + user.getRole() + "','" + user.getAktiv() + "')");
        db.close();
    }

    @Override
    public void updateUser(UserDTO user) throws DALException { //We update user with a new user
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM userdto where userID=" + user.getUserID());
            rs.next();
            if (rs.getInt("userID") == user.getUserID()) {
                db.update("UPDATE userdto SET userID = '" + user.getUserID() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE userdto SET userName = '" + user.getUserName() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE userdto SET ini = '" + user.getIni() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE userdto SET cpr = '" + user.getCpr() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE userdto SET password = '" + user.getPassword() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE userdto SET role = '" + user.getRole() + "' WHERE (userID = '" + user.getUserID() + "');");
                db.update("UPDATE userdto SET aktiv = '" + user.getAktiv() + "' WHERE (userID = '" + user.getUserID() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();

    }

    @Override
    public void deleteUser(int userId) throws DALException { //We delete user
        db.connect();
        db.update("delete from userdto where userID=" + userId);
        db.update("delete from userdto_roles where userID="+userId);
        db.close();
    }
}
