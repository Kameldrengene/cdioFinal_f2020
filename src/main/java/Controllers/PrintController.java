package Controllers;


import Data.PrintDAOSQL;
import Data.dto.PrintDTO;

import java.sql.SQLException;
import java.util.List;

public class PrintController {
    public final PrintDAOSQL printDAOSQL;
    public PrintController(){
        printDAOSQL = new PrintDAOSQL();
    }

    public List<PrintDTO> getData(int pbID, int receptID){
        try {
            return printDAOSQL.getPrint(pbID,receptID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
