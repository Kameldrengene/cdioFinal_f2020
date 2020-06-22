package Data;


import dal.ReceptDAOSQL;
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
class ReceptDAOSQLTest {
    final ReceptDAOSQL receptDAOSQL = new ReceptDAOSQL();
    ReceptDTO testRecept;
    List<ReceptDTO> listRecept;

    @Test
    @Order(1)
    void getRecept() {
        try {
            receptDAOSQL.db.setDB("cdioTest_2020");
            testRecept = receptDAOSQL.getRecept(10,1);
            assertEquals(10,testRecept.getReceptId());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getReceptList() {
        try {
            receptDAOSQL.db.setDB("cdioTest_2020");
            listRecept = receptDAOSQL.getReceptList();
            assertEquals(11,listRecept.get(1).getReceptId());
        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Test
    @Order(3)
    void createRecept() {
        try {
            receptDAOSQL.db.setDB("cdioTest_2020");
            int expected = 99;
            List<ReceptDTO> receptDTOList = new ArrayList<>();
            ReceptDTO newRecept = new ReceptDTO();
            newRecept.setNonNetto(5.5);
            newRecept.setRaavareId(3);
            newRecept.setReceptId(99);
            newRecept.setReceptNavn("Morfin");
            newRecept.setTolerance(9.5);
            ReceptDTO newRecept2 = new ReceptDTO();
            newRecept2.setNonNetto(10);
            newRecept2.setRaavareId(4);
            newRecept2.setReceptId(99);
            newRecept2.setReceptNavn("Morfin");
            newRecept2.setTolerance(1.5);

            receptDTOList.add(newRecept);
            receptDTOList.add(newRecept2);

            receptDAOSQL.createReceptList(receptDTOList);
            testRecept = receptDAOSQL.getRecept(99,3);
            assertEquals(expected,testRecept.getReceptId());
        }catch (SQLException e){
            e.printStackTrace();
        }

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