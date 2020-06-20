package Data;

import Data.dto.ProduktbatchDTO;
import Data.dto.ProduktbatchKompDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ProduktbatchDAOSQLTest {

    //Initialize variables for easier use
    private ProduktbatchDAOSQL DAO;

    private ProduktbatchDTO DTO;
    private List<ProduktbatchDTO> listDTO;

    private ProduktbatchKompDTO DTOKomp;
    private List<ProduktbatchKompDTO> listDTOKomp;

    //Constructor. Change to testdatabase
    public ProduktbatchDAOSQLTest() throws SQLException {
        DAO = new ProduktbatchDAOSQL();
        DAO.db.setDB("cdioTest_2020");
    }

    @Test
    @Order(1)
    void getProduktBatchList() throws SQLException {

        listDTO = DAO.getProduktBatchList();

        assertEquals(3, listDTO.size());

        //Check ID is correct
        assertEquals(1, listDTO.get(0).getPbId());
        assertEquals(2, listDTO.get(1).getPbId());
        assertEquals(3, listDTO.get(2).getPbId());

        //Check status is correct
        assertEquals("Afsluttet",listDTO.get(0).getStatus());
        assertEquals("Under Produktion",listDTO.get(1).getStatus());
        assertEquals("Ikke påbegyndt",listDTO.get(2).getStatus());

        //Check recept ID is correct
        assertEquals(10, listDTO.get(0).getReceptId());
        assertEquals(11, listDTO.get(1).getReceptId());
        assertEquals(10, listDTO.get(2).getReceptId());

        //Check data is correct
        assertEquals("2020-06-12", listDTO.get(0).getDato());
        assertEquals("2020-06-12", listDTO.get(1).getDato());
        assertEquals("2020-06-18", listDTO.get(2).getDato());

    }

    @Test
    @Order(2)
    void getAktuelProduktBatchList() throws SQLException {

        listDTO = DAO.getAktuelProduktBatchList();
        assertEquals(2, listDTO.size());

        //Check ID is correct
        assertEquals(2, listDTO.get(0).getPbId());
        assertEquals(3, listDTO.get(1).getPbId());

        //Check status is correct
        assertEquals("Under Produktion",listDTO.get(0).getStatus());
        assertEquals("Ikke påbegyndt",listDTO.get(1).getStatus());

        //Check recept ID is correct
        assertEquals(11, listDTO.get(0).getReceptId());
        assertEquals(10, listDTO.get(1).getReceptId());

        //Check data is correct
        assertEquals("2020-06-12", listDTO.get(0).getDato());
        assertEquals("2020-06-18", listDTO.get(1).getDato());

    }

    @Test
    void getBatchLine() throws SQLException {

        DTO = DAO.getBatchLine(1);

        //Check product batch line
        assertEquals(1, DTO.getPbId());
        assertEquals(10, DTO.getReceptId());
        assertEquals("Afsluttet", DTO.getStatus());
        assertEquals("2020-06-12", DTO.getDato());

    }

    @Test
    void getBatchkomponent() throws SQLException {

        DTOKomp = DAO.getBatchkomponent(1, 3);

        //Check component lines
        assertEquals(1, DTOKomp.getPbId());
        assertEquals(3, DTOKomp.getRbID());
        assertEquals(14, DTOKomp.getUserId());
        assertEquals(4.00, DTOKomp.getTara());
        assertEquals(5.00, DTOKomp.getNetto());

    }

    @Test
    void getBatchkomponents() throws SQLException {

        listDTOKomp = DAO.getBatchkomponents(1);

        //Check first component
        assertEquals(1, listDTOKomp.get(0).getPbId());
        assertEquals(1, listDTOKomp.get(0).getRbID());
        assertEquals(14, listDTOKomp.get(0).getUserId());
        assertEquals(4.00, listDTOKomp.get(0).getTara());
        assertEquals(5.00, listDTOKomp.get(0).getNetto());

        //Check second component
        assertEquals(1, listDTOKomp.get(1).getPbId());
        assertEquals(3, listDTOKomp.get(1).getRbID());
        assertEquals(14, listDTOKomp.get(1).getUserId());
        assertEquals(4.00, listDTOKomp.get(1).getTara());
        assertEquals(5.00, listDTOKomp.get(1).getNetto());

    }


//    @Test
//    void updateProduktBatch() {
//    }
//
//    @Test
//    void updateProduktBatchkomponent() {
//    }
//
//    @Test
//    void updateNewpb() {
//    }
//
//    @Test
//    void eraseProduktBatch() {
//    }
}