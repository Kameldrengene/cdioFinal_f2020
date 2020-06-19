package dal;


import Data.RaavareDAOSQL;
import Data.SQLDatabaseIO;
import Data.dto.RaavareDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RaavareDAOSQLTest {
    final RaavareDAOSQL raavareDAOSQL = new RaavareDAOSQL();
    RaavareDTO testRaavare;
    List<RaavareDTO> listRaavare;

    @Test
    @Order(1)
    void getRaavare() {
        try {
            raavareDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 1;
        try {
            testRaavare = raavareDAOSQL.getRaavare(1);
            assertEquals(expected,testRaavare.getRaavareID());

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getRaavareList() {
        try {
            raavareDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 2;
        try{
            listRaavare = raavareDAOSQL.getRaavareList();
            assertEquals(expected,listRaavare.get(1).getRaavareID());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void createRaavare() {
        try {
            raavareDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int expected = 99;
        RaavareDTO newRaavare = new RaavareDTO();
        newRaavare.setLagerBeholdning(10.5);
        newRaavare.setLeverandoer("Leo");
        newRaavare.setRaavareID(99);
        newRaavare.setRaavareNavn("Vodka");
        try{
            raavareDAOSQL.createRaavare(newRaavare);
            testRaavare = raavareDAOSQL.getRaavare(99);
            assertEquals(expected,testRaavare.getRaavareID());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void updateRaavare() {
        try {
            raavareDAOSQL.db.setDB("cdioTest_2020");
            String expected = "Novo";
            RaavareDTO newRaavare = new RaavareDTO();
            newRaavare.setLagerBeholdning(10.5);
            newRaavare.setLeverandoer("Novo");
            newRaavare.setRaavareID(99);
            newRaavare.setRaavareNavn("Vodka");
            raavareDAOSQL.updateRaavare(newRaavare);
            testRaavare = raavareDAOSQL.getRaavare(99);
            assertEquals(expected,testRaavare.getLeverandoer());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    void raavareExists() {

        boolean aktual = false;
        try {
            raavareDAOSQL.db.setDB("cdioTest_2020");
            aktual = raavareDAOSQL.raavareExists(99);
            assertTrue(aktual);
            aktual= raavareDAOSQL.raavareExists(69);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertFalse(aktual);
    }

    @Test
    @Order(6)
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