package controller;

import dal.SQLDatabaseIO;
import dal.dto.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    UserController userController;
    UserDTO testUser;
    List<UserDTO> testList;
public UserControllerTest() throws SQLException{
    userController =new UserController();
    userController.userDAOSQL.db.setDB("cdioTest_2020");
}

    @Test
    @Order(1)
    void getData() {
        String name = "testr";
        testList = userController.getData();
        assertEquals(name,testList.get(7).getUserName());
    }

    @Test
    @Order(2)
    void getUser() {

        String initial ="ter";
        testUser = userController.getUser(18);
        assertEquals(initial,testUser.getIni());
    }

    @Test
    @Order(3)
    void getRole() {

        int expected = 2;
        testList = userController.getRole("Produktionsleder");
        assertEquals(expected,testList.size());
    }

    @Test
    @Order(4)
    void currentActivity() {
        boolean aktual = userController.CurrentActivity(11);
        assertTrue(aktual);
    }

    @Test
    @Order(5)
    void createUser() {
        String expected = "Test";
        UserDTO newUser = new UserDTO();
        newUser.setAktiv(false);
        newUser.setUserName("Test");
        newUser.setIni("TES");
        newUser.setJob("Laborant");
        newUser.setPassword("passNew");

        userController.createUser(newUser);
        testUser = userController.getUser(19);
        assertEquals(expected,testUser.getUserName());
    }

    @Test
    @Order(6)
    void activitySwitchUser() {
        testUser = userController.getUser(19);
        userController.activitySwitchUser(testUser);
        boolean aktual = userController.CurrentActivity(19);
        assertTrue(aktual);

    }

    @Test
    @Order(7)
    void updateUser() {
        String expected = "Produktionsleder";

        UserDTO newUser = new UserDTO();
        newUser.setAktiv(false);
        newUser.setUserName("Test");
        newUser.setIni("TES");
        newUser.setJob("Produktionsleder");
        newUser.setPassword("passNew");
        newUser.setUserID(19);
        userController.updateUser(newUser);
        testUser = userController.getUser(19);
        assertEquals(expected,testUser.getJob());

    }

    @Test
    @Order(8)
   void cleanUP(){
        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioFinal_2020.userdto WHERE userName = 'Test'");
            sqlDatabaseIO.update("DELETE FROM cdioTest_2020.userdto WHERE userName = 'Test'");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}