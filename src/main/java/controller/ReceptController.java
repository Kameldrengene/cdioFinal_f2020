package controller;

import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;

import java.util.List;

public class ReceptController {
    private ReceptDAOSQL receptDAOSQL;

    public ReceptController(){
        receptDAOSQL = new ReceptDAOSQL();
    }
    public List<ReceptDTO> getData()  {
        try {
            return receptDAOSQL.getRCList();
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public ReceptDTO getRecept (int receptId) {
        try {
            return receptDAOSQL.getRC(receptId);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public ReceptDTO opretRecept (ReceptDTO recept){
        try {
             receptDAOSQL.opretRC(recept);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return recept;
    }

    public ReceptDTO updateRecept (ReceptDTO recept){
        try {
            receptDAOSQL.opdaterRC(recept);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return recept;
    }
}



