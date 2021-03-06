package API;


import dal.SQLDatabaseIO;
import dal.dto.RaavareDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RaavareServiceTest {
    final RaavareService raavareService = new RaavareService();
    RaavareDTO testraavare;
    List<RaavareDTO> listRaavare;

    @Test
    @Order(1)
    void getData() {
        try {
            raavareService.raavareController.raavareDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 2;
        listRaavare = raavareService.getData();
    }

    @Test
    @Order(2)
    void getRaavare() {
        try {
            raavareService.raavareController.raavareDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 1;
        testraavare = raavareService.getRaavare(1);
        assertEquals(expected,testraavare.getRaavareID());
    }

    @Test
    @Order(3)
    void opretRaavare() {
        try {
            raavareService.raavareController.raavareDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 99;
        RaavareDTO newRaavare = new RaavareDTO();
        newRaavare.setLagerBeholdning(10.5);
        newRaavare.setLeverandoer("Leo");
        newRaavare.setRaavareID(99);
        newRaavare.setRaavareNavn("Vodka");

        raavareService.opretRaavare(newRaavare);
        testraavare = raavareService.getRaavare(99);
        assertEquals(expected,testraavare.getRaavareID());
    }

    @Test
    @Order(4)
    void updateRaavare() {
        try {
            raavareService.raavareController.raavareDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String expected = "Novo";
        RaavareDTO newRaavare = new RaavareDTO();
        newRaavare.setLagerBeholdning(10.5);
        newRaavare.setLeverandoer("Novo");
        newRaavare.setRaavareID(99);
        newRaavare.setRaavareNavn("Vodka");

        raavareService.updateRaavare(newRaavare);
        testraavare=raavareService.getRaavare(99);
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