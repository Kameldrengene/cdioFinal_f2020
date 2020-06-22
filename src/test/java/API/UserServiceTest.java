package API;

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
class UserServiceTest {
    final UserService userService = new UserService();
    UserDTO testUser;
    List<UserDTO> testList;

    @Test
    @Order(1)
    void getData() {
        try {
            userService.userController.userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String name = "Mark";
        testList = userService.getData();
        assertEquals(name, testList.get(0).getUserName());
    }

    @Test
    @Order(2)
    void getUser() {
        try {
            userService.userController.userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String initial = "FD";
        testUser = userService.getUser(12);
        assertEquals(initial, testUser.getIni());
    }

    @Test
    @Order(3)
    void getRole() {
        try {
            userService.userController.userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 2;
        testList = userService.getRole("Produktionsleder");
        assertEquals(expected, testList.size());
    }

    @Test
    @Order(4)
    void getUserActivity() {
        try {
            userService.userController.userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean aktual = userService.getUserActivity(11);
        assertTrue(aktual);
    }


    @Test
    @Order(5)
    void createUser() {
        try {
            userService.userController.userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String expected = "Test";
        UserDTO newUser = new UserDTO();
        newUser.setAktiv(false);
        newUser.setUserName("Test");
        newUser.setIni("TES");
        newUser.setJob("Laborant");
        newUser.setPassword("passNew");

        userService.createUser(newUser);
        testUser = userService.getUser(19);
        assertEquals(expected, testUser.getUserName());
    }

    @Test
    @Order(6)
    void activitySwitchUser() {
        try {
            userService.userController.userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        testUser = userService.getUser(19);
        userService.activitySwitchUser(testUser);
        boolean aktual = userService.getUserActivity(19);
        assertTrue(aktual);

    }

    @Test
    @Order(7)
    void updateUser() {
        try {
            userService.userController.userDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String expected = "Produktionsleder";

        UserDTO newUser = new UserDTO();
        newUser.setAktiv(false);
        newUser.setUserName("Test");
        newUser.setIni("TES");
        newUser.setJob("Produktionsleder");
        newUser.setPassword("passNew");
        newUser.setUserID(19);

        userService.updateUser(newUser);
        testUser = userService.getUser(19);
        assertEquals(expected, testUser.getJob());
    }

    @Test
    @Order(8)
    void cleanUP() {
        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioFinal_2020.userdto WHERE userName = 'Test'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}