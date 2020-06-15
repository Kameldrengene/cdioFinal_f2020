package dal;

import dal.dto.ReceptDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceptDAOSQLTest {
    ReceptDAOSQL receptDAOSQL = new ReceptDAOSQL();
    ReceptDTO testRecept;
    List<ReceptDTO> listRecept;

    @Test
    void getRecept() {
        try {
            testRecept = receptDAOSQL.getRecept(10);
            assertEquals(10,testRecept.getReceptId());
        } catch (IDALException.DALException e){
            e.printStackTrace();
        }
    }

    @Test
    void getReceptList() {
        try {
            listRecept = receptDAOSQL.getReceptList();
            assertEquals(11,listRecept.get(4).getReceptId());
        } catch (IDALException.DALException e){
            e.printStackTrace();
        }


    }

    @Test
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
    void updateRecept() {
    }
}