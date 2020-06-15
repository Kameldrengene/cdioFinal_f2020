package dal;

import dal.dto.ReceptDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReceptDAOSQLTest {
    ReceptDAOSQL receptDAOSQL = new ReceptDAOSQL();
    ReceptDTO testRecept;
    List<ReceptDTO> listRecept;

    @Test
    @Order(1)
    void getRecept() {
        try {
            testRecept = receptDAOSQL.getRecept(10);
            assertEquals(10,testRecept.getReceptId());
        } catch (IDALException.DALException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getReceptList() {
        try {
            listRecept = receptDAOSQL.getReceptList();
            assertEquals(11,listRecept.get(4).getReceptId());
        } catch (IDALException.DALException e){
            e.printStackTrace();
        }


    }

    @Test
    @Order(3)
    void createRecept() {
        int expected = 99;
        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(5.5);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(99);
        newRecept.setReceptNavn("Morfin");
        newRecept.setTolerance(9.5);
        try {
            receptDAOSQL.createRecept(newRecept);
            testRecept = receptDAOSQL.getRecept(99);
            assertEquals(expected,testRecept.getReceptId());


        }catch (IDALException.DALException e){
            e.printStackTrace();
        }

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
        try {
            receptDAOSQL.updateRecept(newRecept);
            testRecept = receptDAOSQL.getRecept(99);
            assertEquals(expected,testRecept.getNonNetto());


        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
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