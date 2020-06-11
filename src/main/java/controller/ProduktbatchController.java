package controller;

import dal.IDALException;
import dal.ProduktbatchDAOSQL;
import dal.RaavareDAOSQL;
import dal.dto.ProduktbatchDTO;

import java.util.List;

public class ProduktbatchController {

    private ProduktbatchDAOSQL PBDAO;

    public ProduktbatchController (){
        PBDAO = new ProduktbatchDAOSQL();
    }

    public List<ProduktbatchDTO> getPBList() throws IDALException.DALException {
        return PBDAO.getPBList();
    }
}
