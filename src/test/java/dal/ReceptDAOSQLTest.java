package dal;


import Data.ReceptDAOSQL;
import Data.SQLDatabaseIO;
import Data.dto.ReceptDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReceptDAOSQLTest {
    final ReceptDAOSQL receptDAOSQL = new ReceptDAOSQL();
    ReceptDTO testRecept;
    List<ReceptDTO> listRecept;

    @Test
    @Order(1)
    void getRecept() {
        receptDAOSQL.db.setDB("cdioTest_2020");
        try {
            testRecept = receptDAOSQL.getReceptKomponent(10,1);
            assertEquals(10,testRecept.getReceptId());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getReceptList() {
        receptDAOSQL.db.setDB("cdioTest_2020");
        try {
            listRecept = receptDAOSQL.getReceptList();
            assertEquals(11,listRecept.get(1).getReceptId());
        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Test
    @Order(3)
    void createRecept() {
        receptDAOSQL.db.setDB("cdioTest_2020");
        int expected = 99;
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(9.5);
        try {
            receptDAOSQL.createRecept(newRecept);
            testRecept = receptDAOSQL.getReceptKomponent(99,3);
            assertEquals(expected,testRecept.getReceptId());


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    @Order(4)
    void updateRecept() {
        receptDAOSQL.db.setDB("cdioTest_2020");
        double expected = 3.5;
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(3.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(5.5);
        try {
            receptDAOSQL.updateRecept(newRecept);
            testRecept = receptDAOSQL.getReceptKomponent(99,3);
            assertEquals(expected,testRecept.getNonNetto());


        }catch (SQLException e){
            e.printStackTrace();
        }
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