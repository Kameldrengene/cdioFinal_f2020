package controller;

import dal.dto.PrintDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintControllerTest {
    final PrintController printController = new PrintController();
    List<PrintDTO> printDTOList;
    @Test
    void getData() {
        try {
            printController.printDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printDTOList  = printController.getData(1,10);
        assertEquals(4,printDTOList.size());
    }
}