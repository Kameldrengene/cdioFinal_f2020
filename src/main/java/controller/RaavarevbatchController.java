package controller;

import dal.IDALException;
import dal.RaavarebatchDAOSQL;
import dal.dto.RaavarebatchDTO;

import java.util.List;

public class RaavarevbatchController {

    private RaavarebatchDAOSQL DAOSQL;


    //Konstruktør
    public RaavarevbatchController() {
        DAOSQL = new RaavarebatchDAOSQL();
    }

    public List<RaavarebatchDTO> getData() throws IDALException.DALException {
        return DAOSQL.getRaavarebatchList();
    }


    public List<RaavarebatchDTO> getAktuelle() throws IDALException.DALException {
        return DAOSQL.getAktuelRaavarebatchList();
    }

    public RaavarebatchDTO getBatch(String batchID) throws IDALException.DALException{

        int batchIDint = Integer.parseInt(batchID);
        return DAOSQL.getRaavarebatch(batchIDint);
    }

}
