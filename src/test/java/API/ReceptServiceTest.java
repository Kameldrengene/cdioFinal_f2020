package API;

import dal.SQLDatabaseIO;
import dal.dto.ReceptDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReceptServiceTest {
    ReceptService receptService = new ReceptService();
    ReceptDTO testRecept;
    List<ReceptDTO> listRecept;

    @Test
    @Order(1)
    void getData() {
        int expected = 11;
        listRecept = receptService.getData();
        assertEquals(expected,listRecept.get(4).getReceptId());
    }

    @Test
    @Order(2)
    void getRecept() {
        int expected = 11;
        testRecept = receptService.getRecept(11);
        assertEquals(expected,testRecept.getReceptId());
    }

    @Test
    @Order(3)
    void opretRecept() {
        int expected = 99;
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(9.5);

        receptService.opretRecept(newRecept);
        testRecept=receptService.getRecept(99);
        assertEquals(expected,testRecept.getReceptId());

    }

    @Test
    @Order(4)
    void updateRecept() {
        double expected = 3.5;
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(3.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(5.5);

        receptService.updateRecept(newRecept);
        testRecept = receptService.getRecept(99);
        assertEquals(expected,testRecept.getNonNetto());
    }
    @Test
    @Order(5)
    void cleanUp(){

        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioFinal_2020.Recepter WHERE RID = 99 AND raavareID = 3");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}