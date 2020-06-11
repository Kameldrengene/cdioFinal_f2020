package controller;

import dal.IDALException;
import dal.RaavareDAOSQL;
import dal.dto.RaavareDTO;

import java.util.List;

public class RaavareController {
    public List<RaavareDTO> getData() throws IDALException.DALException {
        return new RaavareDAOSQL().getRaavareList();
    }

    public RaavareDTO getRaavare(int id) {
        return new RaavareDTO();
    }

    public RaavareDTO createRaavare(RaavareDTO raavare) {
        return new RaavareDTO();
    }

    public RaavareDTO updateRaavare(RaavareDTO raavare) {
        return new RaavareDTO();
    }


}

