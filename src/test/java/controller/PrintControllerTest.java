package controller;

import dal.dto.PrintDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintControllerTest {
    final PrintController printController = new PrintController();
    List<PrintDTO> printDTOList;
    @Test
    void getData() {
        printController.printDAOSQL.db.setDB("cdioTest_2020");
        printDTOList  = printController.getData(1,10);
        assertEquals(4,printDTOList.size());
    }
}