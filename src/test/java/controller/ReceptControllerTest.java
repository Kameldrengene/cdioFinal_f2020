package controller;

import dal.SQLDatabaseIO;
import dal.dto.ReceptDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReceptControllerTest {
    final ReceptController receptController = new ReceptController();
    ReceptDTO testRecept;
    List<ReceptDTO> listRecept;
    @Test
    @Order(1)
    void getData() {
        try {
            receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 11;
        listRecept = receptController.getData();
        assertEquals(expected,listRecept.get(1).getReceptId());
    }

    @Test
    @Order(2)
    void getRecept() {
        try {
            receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 11;
        int RaavarID = 1;
        testRecept= receptController.getRecept(11,RaavarID);
        assertEquals(expected,testRecept.getReceptId());
    }

    @Test
    @Order(3)
    void opretRecept() {
        try {
            receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 99;
        List<ReceptDTO> receptDTOList = new ArrayList<>();
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(9.5);

        receptController.opretRecept(newRecept,0);
        testRecept = receptController.getRecept(99,3);
        assertEquals(expected,testRecept.getReceptId());

        ReceptDTO neRecept = new ReceptDTO();
        neRecept.setNonNetto(5.5);
        neRecept.setRaavareId(2);
        neRecept.setReceptId(99);
        neRecept.setReceptNavn("Morfin");
        neRecept.setTolerance(9.5);
        receptController.opretRecept(neRecept,1);
        testRecept = receptController.getRecept(99,2);
        assertEquals(expected,testRecept.getReceptId());

    }

//    @Test
//    @Order(4)
//    void updateRecept() {
//        receptController.receptDAOSQL.db.setDB("cdioTest_2020");
//        double expected = 3.5;
//        ReceptDTO newRecept = new ReceptDTO();
//        newRecept.setNonNetto(3.5);
//        newRecept.setRaavareId(3);
//        newRecept.setReceptId(99);
//        newRecept.setReceptNavn("Morfin");
//        newRecept.setTolerance(5.5);
//        receptController.updateRecept(newRecept);
//        testRecept = receptController.getRecept(99,3);
//        assertEquals(expected,testRecept.getNonNetto());
//
//    }
    @Test
    @Order(4)
    void cleanUp(){

        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioTest_2020.Recepter WHERE RID = 99");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}