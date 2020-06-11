package controller;

import dal.IDALException;
import dal.RaavarebatchDAOSQL;
import dal.dto.RaavarebatchDTO;

import java.util.List;

public class RaavarevbatchController {

    private RaavarebatchDAOSQL RVBDAO;


    //Konstruktør
    public RaavarevbatchController() {
        RVBDAO = new RaavarebatchDAOSQL();
    }

    public List<RaavarebatchDTO> getRVBList() throws IDALException.DALException {
        return RVBDAO.getRVBList();
    }


    public List<RaavarebatchDTO> getAktuelRVBList() throws IDALException.DALException {
        return RVBDAO.getAktuelRVBList();
    }

    public RaavarebatchDTO getRVB(int RVBID) throws IDALException.DALException{
        return RVBDAO.getRBV(RVBID);
    }

}
