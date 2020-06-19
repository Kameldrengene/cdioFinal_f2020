package dal;


import Data.PrintDAOSQL;
import Data.dto.PrintDTO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintDAOSQLTest {
    final PrintDAOSQL printDAOSQL = new PrintDAOSQL();
    List<PrintDTO> printDTOList;

    @Test
    void getPrint() {
        try {
            printDAOSQL.db.setDB("cdioTest_2020");
            printDTOList = printDAOSQL.getPrint(1, 10);
            assertEquals(4, printDTOList.size());
            printDTOList = printDAOSQL.getPrint(1, 10);
            assertEquals(4, printDTOList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}