package controller;

import dal.IDALException;
import dal.PrintDAOSQL;
import dal.dto.PrintDTO;

import java.util.List;

public class PrintController {
    public final PrintDAOSQL printDAOSQL;
    public PrintController(){
        printDAOSQL = new PrintDAOSQL();
    }

    public List<PrintDTO> getData(int pbID, int receptID){
        try {
            return printDAOSQL.getPrint(pbID,receptID);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }
}
