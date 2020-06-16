package API;

import dal.IDALException;
import dal.SQLDatabaseIO;
import dal.dto.RaavarebatchDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RaavarebatchServiceTest {
    RaavarebatchService raavarebatchService = new RaavarebatchService();
    RaavarebatchDTO testRaavarebatch;
    List<RaavarebatchDTO> listRaavarebatch;

    @Test
    @Order(1)
    void getData() {
        int expected = 2;
        int expectedSize = 19;

        try {
            listRaavarebatch = raavarebatchService.getData();
            assertEquals(expected,listRaavarebatch.get(1).getRbId());
        //    assertEquals(expectedSize,listRaavarebatch.size());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getAktuelle() {
        int expected = 3;
        int expectedSize = 17;
        try {
            listRaavarebatch = raavarebatchService.getAktuelle();
            assertEquals(expected,listRaavarebatch.get(2).getRbId());
       //     assertEquals(expectedSize,listRaavarebatch.size());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void getBatch() {
        int expected = 1;
        try {
            testRaavarebatch = raavarebatchService.getBatch("1");
            assertEquals(expected,testRaavarebatch.getRbId());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void opretRaavarebatch() {
        int expected = 99;
        RaavarebatchDTO newRaavarebatch = new RaavarebatchDTO();
        newRaavarebatch.setAktuelMaengde(85.5);
        newRaavarebatch.setRaavareId(2);
        newRaavarebatch.setRbId(99);
        newRaavarebatch.setStartMaengde(100.0);

        raavarebatchService.opretRaavarebatch(newRaavarebatch);

        try {
            testRaavarebatch = raavarebatchService.getBatch("99");
            assertEquals(expected,testRaavarebatch.getRbId());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    void updateRaavarebatch() {
        double expected = 70.5;
        RaavarebatchDTO newRaavarebatch = new RaavarebatchDTO();
        newRaavarebatch.setAktuelMaengde(70.5);
        newRaavarebatch.setRaavareId(2);
        newRaavarebatch.setRbId(99);
        newRaavarebatch.setStartMaengde(100.0);

        raavarebatchService.updateRaavarebatch(newRaavarebatch);

        try {
            testRaavarebatch = raavarebatchService.getBatch("99");
            assertEquals(expected,testRaavarebatch.getAktuelMaengde());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
    }
    @Test
    @Order(6)
    void cleanUP(){
        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioFinal_2020.RaavareBatches WHERE rBID = 99");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}