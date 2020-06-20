package API;

import Data.dto.PrintDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintServiceTest {
 final PrintService printService = new PrintService();
 List<PrintDTO> printDTOList;
    @Test
    void getData() {
        try {
            printService.printController.printDAOSQL.db.setDB("cdioTest_2020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printDTOList = printService.getData("1-10");
        assertEquals(4,printDTOList.size());

    }
}