package controller;

import Funktionalitet.ReceptFunc;
import dal.SQLDatabaseIO;
import dal.dto.ReceptDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.WebApplicationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReceptControllerTest {
    final ReceptController receptController = new ReceptController();
    ReceptDTO testRecept;
    List<ReceptDTO> listRecept;
    ReceptFunc receptFunc;
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
        int expectedID = 99;
        int expectedRaavarID = 2;
        String expectedReceptNavn = "Morfin";
        List<ReceptDTO> receptDTOList = new ArrayList<>();
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(9.5);

        ReceptDTO neRecept = new ReceptDTO();
        neRecept.setNonNetto(5.5);
        neRecept.setRaavareId(2);
        neRecept.setReceptId(99);
        neRecept.setReceptNavn("Morfin");
        neRecept.setTolerance(9.5);

        receptDTOList.add(newRecept);
        receptDTOList.add(neRecept);

        receptController.opretRecept(receptDTOList);
        testRecept = receptController.getRecept(99,2);
        assertEquals(expectedID,testRecept.getReceptId());
        assertEquals(expectedReceptNavn,testRecept.getReceptNavn());
        assertEquals(expectedRaavarID,testRecept.getRaavareId());
    }

    @Test
    @Order(4)
    void opretReceptfunc(){
        try {
            receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String expectedMsg = "Recept ID er optaget\n" + "Vælge en anden";
        List<ReceptDTO> receptDTOList1 = new ArrayList<>();
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Test");
        newRecept.setTolerance(9.5);

        ReceptDTO neRecept = new ReceptDTO();
        neRecept.setNonNetto(5.5);
        neRecept.setRaavareId(2);
        neRecept.setReceptId(99);
        neRecept.setReceptNavn("Test");
        neRecept.setTolerance(9.5);

        receptDTOList1.add(newRecept);
        receptDTOList1.add(neRecept);

        WebApplicationException WebException = assertThrows(WebApplicationException.class,()->{
            receptController.opretRecept(receptDTOList1);
        });
        assertEquals(expectedMsg, WebException.getResponse().getEntity().toString());
    }

    @Test
    @Order(5)
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