package controller;

import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;

import java.util.List;

public class ReceptController {

    public List<ReceptDTO> getData() throws IDALException.DALException {
        return new ReceptDAOSQL().getReceptList();
    }
}
