package controller;

import dal.SQLDatabaseIO;
import dal.dto.RaavareDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RaavareControllerTest {
    RaavareController raavareController = new RaavareController();
    RaavareDTO testraavare;
    List<RaavareDTO> listRaavare;

    @Test
    @Order(1)
    void getData() {
        raavareController.raavareDAOSQL.db.setDB("cdioTest_2020");
        int expected = 2;
        listRaavare = raavareController.getData();
        assertEquals(expected,listRaavare.get(1).getRaavareID());

    }

    @Test
    @Order(2)
    void getRaavare() {
        raavareController.raavareDAOSQL.db.setDB("cdioTest_2020");
        int expected = 1;
        testraavare = raavareController.getRaavare(1);
        assertEquals(expected,testraavare.getRaavareID());
    }

    @Test
    @Order(3)
    void opretRaavare() {
        raavareController.raavareDAOSQL.db.setDB("cdioTest_2020");
        int expected = 99;
        RaavareDTO newRaavare = new RaavareDTO();
        newRaavare.setLagerBeholdning(10.5);
        newRaavare.setLeverandoer("Leo");
        newRaavare.setRaavareID(99);
        newRaavare.setRaavareNavn("Vodka");
        raavareController.opretRaavare(newRaavare);
        testraavare=raavareController.getRaavare(99);
        assertEquals(expected,testraavare.getRaavareID());
    }

    @Test
    @Order(4)
    void updateRaavare() {
        raavareController.raavareDAOSQL.db.setDB("cdioTest_2020");
        String expected = "Novo";
        RaavareDTO newRaavare = new RaavareDTO();
        newRaavare.setLagerBeholdning(10.5);
        newRaavare.setLeverandoer("Novo");
        newRaavare.setRaavareID(99);
        newRaavare.setRaavareNavn("Vodka");
        raavareController.updateRaavare(newRaavare);
        testraavare=raavareController.getRaavare(99);
        assertEquals(expected,testraavare.getLeverandoer());
    }

    @Test
    @Order(5)
    void cleanUp(){
        try {
            SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("DELETE FROM cdioTest_2020.Raavarer WHERE raavareID = 99");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}