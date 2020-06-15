package API;

import dal.SQLDatabaseIO;
import dal.dto.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    UserService userService = new UserService();
    UserDTO testUser;
    List<UserDTO> testList;

    @Test
    @Order(1)
    void getData() {
        String name = "Mark";
        testList = userService.getData();
        assertEquals(name, testList.get(0).getUserName());
    }

    @Test
    @Order(2)
    void getUser() {
        String initial = "FD";
        testUser = userService.getUser(12);
        assertEquals(initial, testUser.getIni());
    }

    @Test
    @Order(3)
    void getRole() {
        int expected = 2;
        testList = userService.getRole("Produktionsleder");
        assertEquals(expected, testList.size());
    }

    @Test
    @Order(4)
    void getUserActivity() {
        boolean aktual = userService.getUserActivity(11);
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

        userService.createUser(newUser);
        testUser = userService.getUser(19);
        assertEquals(expected, testUser.getUserName());
    }

    @Test
    @Order(6)
    void activitySwitchUser() {
        testUser = userService.getUser(19);
        userService.activitySwitchUser(testUser);
        boolean aktual = userService.getUserActivity(19);
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