package Data;

import dal.RaavarebatchDAOSQL;
import dal.SQLDatabaseIO;
import dal.dto.RaavarebatchDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RaavarebatchDAOSQLTest {

    final RaavarebatchDAOSQL raavarebatchDAOSQL = new RaavarebatchDAOSQL();
    RaavarebatchDTO testRaavarebatch;
    List<RaavarebatchDTO> listRaavarebatch;

    @Test
    @Order(1)
    void getRaavarebatch() {
        try {
            raavarebatchDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 1;
        try {
            testRaavarebatch = raavarebatchDAOSQL.getRaavarebatch(1);
            assertEquals(expected,testRaavarebatch.getRbId());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getRaavarebatchList() {
        try {
            raavarebatchDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 2;
        int expectedSize = 22;
        try {
            listRaavarebatch = raavarebatchDAOSQL.getRaavarebatchList();
            assertEquals(expected,listRaavarebatch.get(1).getRbId());
            assertEquals(expectedSize,listRaavarebatch.size());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void getAktuelRaavarebatchList() {
        try {
            raavarebatchDAOSQL.db.setDB("cdioTest_2020");

            int expected = 3;
            int expectedSize = 20;

            listRaavarebatch = raavarebatchDAOSQL.getAktuelRaavarebatchList();
            assertEquals(expected,listRaavarebatch.get(2).getRbId());
            assertEquals(expectedSize,listRaavarebatch.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void createRaavarebatch() {
        try {
            raavarebatchDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 99;
        RaavarebatchDTO newRaavarebatch = new RaavarebatchDTO();
        newRaavarebatch.setAktuelMaengde(85.5);
        newRaavarebatch.setRaavareId(2);
        newRaavarebatch.setRbId(99);
        newRaavarebatch.setStartMaengde(100.0);

        try {
            raavarebatchDAOSQL.createRaavarebatch(newRaavarebatch);
            testRaavarebatch = raavarebatchDAOSQL.getRaavarebatch(99);
            assertEquals(expected,testRaavarebatch.getRbId());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Test
    @Order(5)
    void updateRaavarebatch() {
        try {
            raavarebatchDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {


        }
        double expected = 70.5;
        RaavarebatchDTO newRaavarebatch = new RaavarebatchDTO();
        newRaavarebatch.setAktuelMaengde(70.5);
        newRaavarebatch.setRaavareId(2);
        newRaavarebatch.setRbId(99);
        newRaavarebatch.setStartMaengde(100.0);

        try {
            raavarebatchDAOSQL.updateRaavarebatch(newRaavarebatch);
            testRaavarebatch = raavarebatchDAOSQL.getRaavarebatch(99);
            assertEquals(expected,testRaavarebatch.getAktuelMaengde());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    void cleanUP(){
        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioTest_2020.RaavareBatches WHERE rBID = 99");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
