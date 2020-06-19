package dal;

import Data.IDALException;
import Data.PrintDAOSQL;
import Data.dto.PrintDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintDAOSQLTest {
    final PrintDAOSQL printDAOSQL = new PrintDAOSQL();
    List<PrintDTO> printDTOList;

    @Test
    void getPrint() {
        printDAOSQL.db.setDB("cdioTest_2020");
        try {
            printDTOList = printDAOSQL.getPrint(1, 10);
            assertEquals(4, printDTOList.size());
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
    }
}