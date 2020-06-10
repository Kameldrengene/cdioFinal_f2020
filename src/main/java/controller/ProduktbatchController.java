package controller;

import dal.IDALException;
import dal.ProduktbatchDAOSQL;
import dal.RaavarebatchDAOSQL;
import dal.dto.ProduktbatchDTO;
import dal.dto.RaavarebatchDTO;

import java.util.List;

public class ProduktbatchController {
    public List<ProduktbatchDTO> getData() throws IDALException.DALException {
        return new ProduktbatchDAOSQL().getProduktBatchList();
    }
}
