package controller;

import dal.IDALException;
import dal.RaavarebatchDAOSQL;
import dal.dto.RaavarebatchDTO;

import java.util.List;

public class RaavarevbatchController {
    public List<RaavarebatchDTO> getData() throws IDALException.DALException {
        return new RaavarebatchDAOSQL().getRaavarebatchList();
    }
}
