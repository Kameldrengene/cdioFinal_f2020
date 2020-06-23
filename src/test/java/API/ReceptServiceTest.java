package API;

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
class ReceptServiceTest {
    final ReceptService receptService = new ReceptService();
    ReceptDTO testRecept;
    List<ReceptDTO> listRecept;

    @Test
    @Order(1)
    void getData() {
        try {
            receptService.receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 10;
        listRecept = receptService.getRecepts();
        assertEquals(expected,listRecept.get(0).getReceptId());
    }

    @Test
    @Order(2)
    void getRecept() {
        try {
            receptService.receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 11;
        listRecept = receptService.getReceptKomponents(11);
        assertEquals(expected,listRecept.get(0).getReceptId());
    }


    @Test
    @Order(3)
    void opretReceptList() {
        try {
            receptService.receptController.receptDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 99;
        List<ReceptDTO> receptDTOList = new ArrayList<>();
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(5);
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
        testRecept=receptService.getrecept(99,4);
        assertEquals(expected,testRecept.getReceptId());

    }


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