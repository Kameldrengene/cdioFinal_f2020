package controller;

import dal.IDALException;
import dal.RaavareDAOSQL;
import dal.dto.RaavareDTO;

import java.util.List;

public class RaavareController {
    private RaavareDAOSQL RVDAO;

    public RaavareController (){
        RVDAO = new RaavareDAOSQL();
    }

    public List<RaavareDTO> getRVList()  {
        try {
            return RVDAO.getRVList();
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO gerRV(int id) {
        try {
            return RVDAO.getRV(id);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO opretRV(RaavareDTO rv) {
        try {
            RVDAO.opretRV(rv);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return rv;
    }

    public RaavareDTO opdaterRV(RaavareDTO rv) {
        try {
            RVDAO.opdaterRV(rv);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return rv;
    }


}

