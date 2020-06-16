package controller;

import dal.dto.PrintDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintControllerTest {
    PrintController printController = new PrintController();
    List<PrintDTO> printDTOList;
    @Test
    void getData() {
        printDTOList  = printController.getData(1,10);
        assertEquals(4,printDTOList.size());
    }
}