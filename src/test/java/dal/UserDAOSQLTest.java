package dal;

import dal.dto.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDAOSQLTest {
    UserDAOSQL userDAOSQL = new UserDAOSQL();
    UserDTO testUser;
    List<UserDTO> testList;

    @Test
    @Order(1)
    void getUser() {
        try {
            testUser = userDAOSQL.getUser(11);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }

        assertEquals(11,testUser.getUserID());

    }

    @Test
    @Order(2)
    void getRole() {
        try {
            testUser = userDAOSQL.getUser(11);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        assertEquals("Administrator",testUser.getJob());
    }

    @Test
    @Order(3)
    void getData() {
        try {
            testList = userDAOSQL.getData();
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        assertEquals("FD",testList.get(1).getIni());
        assertEquals("Lasse",testList.get(4).getUserName());
    }

    @Test
    @Order(4)
    void createUser() {
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
    @Order(5)
    void updateUser() {
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
    @Order(6)
    void getActivity() {
        try {
            boolean aktual = userDAOSQL.getUser(19).getAktiv();
            assertFalse(aktual);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(7)
    void aktivitySwitchUser() {
        try{
            userDAOSQL.aktivitySwitchUser(19);
            boolean aktual = userDAOSQL.getUser(19).getAktiv();
            assertTrue(aktual);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
    }



    @Test
    @Order(8)
    void cleanUP(){
        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioFinal_2020.userdto WHERE userName = 'Test'");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}