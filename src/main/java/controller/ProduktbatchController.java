package controller;

import dal.IDALException;
import dal.ProduktbatchDAOSQL;
import dal.dto.ProduktbatchDTO;

import java.util.List;

public class ProduktbatchController {
    public List<ProduktbatchDTO> getData() throws IDALException.DALException {
        return new ProduktbatchDAOSQL().getPBList();
    }
}
