package controller;

import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;

import java.util.List;

public class ReceptController {
    private ReceptDAOSQL RCDAO;

    public ReceptController(){
        RCDAO = new ReceptDAOSQL();
    }

    public List<ReceptDTO> getRCList()  {
        try {
            return RCDAO.getRCList();
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public ReceptDTO getRC(int RCID) {
        try {
            return RCDAO.getRC(RCID);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public ReceptDTO opretRC(ReceptDTO rc){
        try {
             RCDAO.opretRC(rc);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return rc;
    }

    public ReceptDTO opdaterRC(ReceptDTO rc){
        try {
            RCDAO.opdaterRC(rc);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return rc;
    }
}



