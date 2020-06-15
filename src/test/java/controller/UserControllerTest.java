package controller;

import dal.IDALException;
import dal.SQLDatabaseIO;
import dal.dto.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    UserController userController = new UserController();
    UserDTO testUser;
    List<UserDTO> testList;

    @Test
    @Order(1)
    void getData() {
        String name = "Mark";
        try {
            testList = userController.getData();
            assertEquals(name,testList.get(0).getUserName());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getUser() {
        String initial ="FD";
        testUser = userController.getUser(12);
        assertEquals(initial,testUser.getIni());
    }

    @Test
    @Order(3)
    void getRole() {
        int expected = 2;
        List<UserDTO> lederList = userController.getRole("Produktionsleder");
        assertEquals(expected,lederList.size());
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
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}