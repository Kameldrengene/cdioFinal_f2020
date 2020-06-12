package controller;

import dal.IDALException;
import dal.RaavareDAOSQL;
import dal.dto.RaavareDTO;

import java.util.List;

public class RaavareController {
    private RaavareDAOSQL raavareDAOSQL;

    public RaavareController (){
        raavareDAOSQL = new RaavareDAOSQL();
    }

    public List<RaavareDTO> getData()  {
        try {
            return raavareDAOSQL.getRVList();
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO getRaavare(int id) {
        try {
            return raavareDAOSQL.getRV(id);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO opretRaavare (RaavareDTO raavareDTO) {
        try {
            raavareDAOSQL.opretRV(raavareDTO);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }

    public RaavareDTO updateRaavare(RaavareDTO raavareDTO) {
        try {
            raavareDAOSQL.opdaterRV(raavareDTO);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }


}

