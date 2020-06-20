package Data;


import dal.SQLDatabaseIO;
import dal.UserDAOSQL;
import dal.dto.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
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
        try {
            userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            testUser = userDAOSQL.getUser(11);
        }catch (SQLException e){
            e.printStackTrace();
        }

        assertEquals(11,testUser.getUserID());

    }

    @Test
    @Order(2)
    void getRole() {
        try {
            userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 1;
        try {
            testList = userDAOSQL.getRole("Administrator");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(expected,testList.size());
    }

    @Test
    @Order(3)
    void getData() {
        try {
            userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            testList = userDAOSQL.getData();
        }catch (SQLException e){
            e.printStackTrace();
        }
        assertEquals("FD",testList.get(1).getIni());
        assertEquals("Lasse",testList.get(4).getUserName());
    }

    @Test
    @Order(4)
    void createUser() {
        try {
            userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    void updateUser() {
        try {
            userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    void getActivity() {
        try {
            userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            boolean aktual = userDAOSQL.getActivity(19);
            assertFalse(aktual);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(7)
    void aktivitySwitchUser() {
        try {
            userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            userDAOSQL.aktivitySwitchUser(19);
            boolean aktual = userDAOSQL.getUser(19).getAktiv();
            assertTrue(aktual);
        }catch (SQLException e){
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