package dal;

import org.junit.Test;

import static org.junit.Assert.*;
import dal.dto.*;

import java.sql.SQLException;
import java.util.List;

public class UserDAOSQLTest {
    UserDAOSQL userDAOSQL = new UserDAOSQL();
    UserDTO testUser;
    List<UserDTO> testList;

    @Test

    public void getUser() {
        try {
            testUser = userDAOSQL.getUser(11);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }

        assertEquals(11,testUser.getUserID());

    }

    @Test
    public void getRole() {
        try {
            testUser = userDAOSQL.getUser(11);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        assertEquals("Administrator",testUser.getJob());
    }

    @Test
    public void getData() {
        try {
            testList = userDAOSQL.getData();
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        assertEquals("FD",testList.get(1).getIni());
        assertEquals("Lasse",testList.get(4).getUserName());

    }

    @Test
    public void createUser() {
        UserDTO newUser = new UserDTO();
        newUser.setAktiv(false);
        newUser.setUserName("Test");
        newUser.setIni("TES");
        newUser.setJob("Laborant");
        newUser.setPassword("passNew");
        try {
            userDAOSQL.createUser(newUser);
            testUser = userDAOSQL.getUser(19);
            assertEquals("Test",testUser.getUserName());

        }catch (IDALException.DALException e){
            e.printStackTrace();
        }


    }

    @Test
    public void updateUser() {
        UserDTO newUser = new UserDTO();
        newUser.setAktiv(false);
        newUser.setUserName("Test");
        newUser.setIni("TES");
        newUser.setJob("Produktionsleder");
        newUser.setPassword("passNew");
        newUser.setUserID(19);
        try {
            userDAOSQL.updateUser(newUser);
            testUser = userDAOSQL.getUser(19);
            assertEquals("Produktionsleder",testUser.getJob());

        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
    }
    @Test
    public void getActivity() {
        try {
            boolean aktual = userDAOSQL.getUser(19).getAktiv();
            assertFalse(aktual);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
    }

    @Test
    public void aktivitySwitchUser() {
        try{
            userDAOSQL.aktivitySwitchUser(19);
            boolean aktual = userDAOSQL.getUser(19).getAktiv();
            assertTrue(aktual);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }


    }

    @Test
    public void cleanUP(){
        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioFinal_2020.userdto WHERE userName = 'Test'");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}