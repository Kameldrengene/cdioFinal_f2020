package API;

import dal.dto.PrintDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintServiceTest {
 PrintService printService = new PrintService();
 List<PrintDTO> printDTOList;
    @Test
    void getData() {
        printDTOList = printService.getData("1-10");
        assertEquals(4,printDTOList.size());

    }
}