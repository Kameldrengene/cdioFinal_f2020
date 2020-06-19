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
    final UserDAOSQL userDAOSQL = new UserDAOSQL();
    UserDTO testUser;
    List<UserDTO> testList;

    @Test
    @Order(1)
    void getUser() {
        userDAOSQL.db.setDB("cdioTest_2020");
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
        userDAOSQL.db.setDB("cdioTest_2020");
        int expected = 1;
        testList = userDAOSQL.getRole("Administrator");
        assertEquals(expected,testList.size());
    }

    @Test
    @Order(3)
    void getData() {
        userDAOSQL.db.setDB("cdioTest_2020");
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
        userDAOSQL.db.setDB("cdioTest_2020");
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
        userDAOSQL.db.setDB("cdioTest_2020");
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
        userDAOSQL.db.setDB("cdioTest_2020");
        try {
            boolean aktual = userDAOSQL.getActivity(19);
            assertFalse(aktual);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(7)
    void aktivitySwitchUser() {
        userDAOSQL.db.setDB("cdioTest_2020");
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
            sqlDatabaseIO.update("DELETE FROM cdioTest_2020.userdto WHERE userName = 'Test'");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}