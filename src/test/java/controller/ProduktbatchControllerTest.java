package controller;

import dal.SQLDatabaseIO;
import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ProduktbatchControllerTest {

    //Initialize variables for easier use
    private final ProduktbatchController Controller;

    private ProduktbatchDTO DTO;
    private List<ProduktbatchDTO> listDTO;

    private ProduktbatchKompDTO DTOKomp;

    //Constructor. Change to testdatabase
    public ProduktbatchControllerTest() throws SQLException {
        Controller = new ProduktbatchController();
        Controller.DAOSQL.db.setDB("cdioTest_2020");
    }

    @Test
    @Order(1)
    void getAlle() {

        listDTO = Controller.getAlle();

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
    void getAktuelle() {

        listDTO = Controller.getAktuelle();
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
    @Order(3)
    void getBatchLine() {

        DTO = Controller.getBatchLine("1");

        //Check product batch line
        assertEquals(1, DTO.getPbId());
        assertEquals(10, DTO.getReceptId());
        assertEquals("Afsluttet", DTO.getStatus());
        assertEquals("2020-06-12", DTO.getDato());

    }

    @Test
    @Order(4)
    void getBatchComponent() {

        DTOKomp = Controller.getBatchComponent("1", "3");

        //Check component lines
        assertEquals(1, DTOKomp.getPbId());
        assertEquals(3, DTOKomp.getRbID());
        assertEquals(14, DTOKomp.getUserId());
        assertEquals(4.00, DTOKomp.getTara());
        assertEquals(5.00, DTOKomp.getNetto());

    }

    @Test
    @Order(5)
    void getBatchComponents() {

        List<ProduktbatchKompDTO> listDTOKomp = Controller.getBatchComponents("1");

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

    @Test
    @Order(6)
    void getMaxPDID() {

        int max = Controller.getMaxPDID();
        assertEquals(3, max);

    }

    @Test
    @Order(7)
    void opretProduktbatch() throws SQLException {

        //Create batch
        ProduktbatchDTO toSend = new ProduktbatchDTO();
        toSend.setPbId(4);
        toSend.setReceptId(10);
        toSend.setStatus("Ikke påbegyndt");

        //Send and get batch
        Controller.opretProduktbatch(toSend);
        DTO = Controller.getBatchLine("4");

        //Check product batch line
        assertEquals(4, DTO.getPbId());
        assertEquals(10, DTO.getReceptId());
        assertEquals("Ikke påbegyndt", DTO.getStatus());

        //Database cleanup
        SQLDatabaseIO sqlIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
        sqlIO.connect();
        sqlIO.update("delete from cdioTest_2020.ProduktBatches where PBID=4");

    }

    @Test
    @Order(8)
    void opdaterProduktbatch() {

        //Create batch
        ProduktbatchDTO toSend = new ProduktbatchDTO();
        toSend.setPbId(1);
        toSend.setReceptId(10);
        toSend.setStatus("Under Produktion");
        toSend.setDato("2019-05-11");

        //Send and get batch
        Controller.opdaterProduktbatch(toSend);
        DTO = Controller.getBatchLine("1");

        //Check product batch line
        assertEquals(1, DTO.getPbId());
        assertEquals(10, DTO.getReceptId());
        assertEquals("Under Produktion", DTO.getStatus());

        //Database cleanup
        toSend.setPbId(1);
        toSend.setReceptId(10);
        toSend.setStatus("Afsluttet");
        toSend.setDato("2020-06-12");

        Controller.opdaterProduktbatch(toSend);

    }

    @Test
    @Order(9)
    void opdaterProduktbatchLine() {

        //Create component
        ProduktbatchKompDTO toSend = new ProduktbatchKompDTO();
        toSend.setPbId(1);
        toSend.setRbID(1);

        toSend.setUserId(10);
        toSend.setTara(3);
        toSend.setNetto(5.4321);
        toSend.setStatus("Afsluttet");

        //Send and get batch
        Controller.opdaterProduktbatchLine(toSend);
        DTOKomp = Controller.getBatchComponent("1","1");

        assertEquals(10, DTOKomp.getUserId());
        assertEquals(3.0000, DTOKomp.getTara());
        assertEquals(5.4321, DTOKomp.getNetto());

        //Database cleanup
        toSend.setUserId(14);
        toSend.setTara(4);
        toSend.setNetto(5);

        Controller.opdaterProduktbatchLine(toSend);

    }

    @Test
    @Order(10)
    void opdaterNewProduktbatch() throws SQLException {

        //Create batch
        ProduktbatchKompDTO toSend = new ProduktbatchKompDTO();
        toSend.setPbId(3);

        toSend.setUserId(17);
        toSend.setTara(3);
        toSend.setNetto(5);
        toSend.setRbID(3);
        toSend.setStatus("Ikke påbegyndt");

        //Send and get batch
        Controller.opdaterNewProduktbatch(toSend);
        DTOKomp = Controller.getBatchComponent("3","3");

        assertEquals(17, DTOKomp.getUserId());
        assertEquals(3.0000, DTOKomp.getTara());
        assertEquals(5.0000, DTOKomp.getNetto());

        //Database cleanup
        SQLDatabaseIO sqlIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
        sqlIO.connect();
        sqlIO.update("update cdioTest_2020.ProduktBatches set UserID=16, Tara=null, Netto=null, RBID=0 where PBID=3 and RBID=3");

    }

}