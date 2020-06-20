package API;

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
class RaavarebatchServiceTest {
    final RaavarebatchService raavarebatchService = new RaavarebatchService();
    RaavarebatchDTO testRaavarebatch;
    List<RaavarebatchDTO> listRaavarebatch;

    @Test
    @Order(1)
    void getData() {
        try {
            raavarebatchService.raavarebatchController.DAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 2;
        int expectedSize = 22;

        listRaavarebatch = raavarebatchService.getData();
        assertEquals(expected,listRaavarebatch.get(1).getRbId());
        assertEquals(expectedSize,listRaavarebatch.size());
    }

    @Test
    @Order(2)
    void getAktuelle() {
        try {
            raavarebatchService.raavarebatchController.DAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 3;
        int expectedSize = 20;
        listRaavarebatch = raavarebatchService.getAktuelle();
        assertEquals(expected,listRaavarebatch.get(2).getRbId());
        assertEquals(expectedSize,listRaavarebatch.size());
    }

    @Test
    @Order(3)
    void getBatch() {
        try {
            raavarebatchService.raavarebatchController.DAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 1;
        testRaavarebatch = raavarebatchService.getBatch("1");
        assertEquals(expected,testRaavarebatch.getRbId());
    }

    @Test
    @Order(4)
    void opretRaavarebatch() {
        try {
            raavarebatchService.raavarebatchController.DAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 99;
        RaavarebatchDTO newRaavarebatch = new RaavarebatchDTO();
        newRaavarebatch.setAktuelMaengde(85.5);
        newRaavarebatch.setRaavareId(2);
        newRaavarebatch.setRbId(99);
        newRaavarebatch.setStartMaengde(100.0);

        raavarebatchService.opretRaavarebatch(newRaavarebatch);

        testRaavarebatch = raavarebatchService.getBatch("99");
        assertEquals(expected,testRaavarebatch.getRbId());
    }

    @Test
    @Order(5)
    void updateRaavarebatch() {
        try {
            raavarebatchService.raavarebatchController.DAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        double expected = 70.5;
        RaavarebatchDTO newRaavarebatch = new RaavarebatchDTO();
        newRaavarebatch.setAktuelMaengde(70.5);
        newRaavarebatch.setRaavareId(2);
        newRaavarebatch.setRbId(99);
        newRaavarebatch.setStartMaengde(100.0);

        raavarebatchService.updateRaavarebatch(newRaavarebatch);

        testRaavarebatch = raavarebatchService.getBatch("99");
        assertEquals(expected,testRaavarebatch.getAktuelMaengde());
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