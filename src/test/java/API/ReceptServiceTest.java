package API;

import dal.SQLDatabaseIO;
import dal.dto.ReceptDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
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
        receptService.receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        int expected = 10;
        listRecept = receptService.getRecepts();
        assertEquals(expected,listRecept.get(0).getReceptId());
    }

    @Test
    @Order(2)
    void getRecept() {
        receptService.receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        int expected = 11;
        listRecept = receptService.getReceptlist(11);
        assertEquals(expected,listRecept.get(0).getReceptId());
    }

    @Test
    @Order(3)
    void opretRecept() {
        receptService.receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        int expected = 99;
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(9.5);

        receptService.opretRecept(newRecept,0);
        testRecept=receptService.getrecept(99,3);
        assertEquals(expected,testRecept.getReceptId());

    }

    @Test
    @Order(4)
    void opretReceptList() {
        receptService.receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        int expected = 99;
        List<ReceptDTO> receptDTOList = new ArrayList<>();
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(9.5);

        ReceptDTO newRecept2 = new ReceptDTO();
        newRecept2.setNonNetto(5.5);
        newRecept2.setRaavareId(4);
        newRecept2.setReceptId(99);
        newRecept2.setReceptNavn("Morfin");
        newRecept2.setTolerance(9.5);

        receptDTOList.add(newRecept);
        receptDTOList.add(newRecept2);

        receptService.OpretRecept(receptDTOList);
        testRecept=receptService.getrecept(99,3);
        assertEquals(expected,testRecept.getReceptId());

    }

//    @Test
//    @Order(4)
//    void updateRecept() {
//        receptService.receptController.receptDAOSQL.db.setDB("cdioTest_2020");
//        double expected = 3.5;
//        ReceptDTO newRecept = new ReceptDTO();
//        newRecept.setNonNetto(3.5);
//        newRecept.setRaavareId(3);
//        newRecept.setReceptId(99);
//        newRecept.setReceptNavn("Morfin");
//        newRecept.setTolerance(5.5);
//
//        receptService.updateRecept(newRecept);
//        testRecept = receptService.getrecept(99,3);
//        assertEquals(expected,testRecept.getNonNetto());
//    }
    @Test
    @Order(4)
    void cleanUp(){

        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioTest_2020.Recepter WHERE RID = 99 AND raavareID = 3");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}