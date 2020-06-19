package controller;

import Funktionalitet.RaavareFunc;
import dal.IDALException;
import dal.RaavareDAOSQL;
import dal.dto.RaavareDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class RaavareController {
    public RaavareDAOSQL raavareDAOSQL;

    public RaavareController (){
        raavareDAOSQL = new RaavareDAOSQL();
    }

    public List<RaavareDTO> getData()  {
        try {
            return raavareDAOSQL.getRaavareList();
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO getRaavare(int id) {
        try {
            return raavareDAOSQL.getRaavare(id);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO opretRaavare (RaavareDTO raavareDTO) {
        RaavareFunc rvFunc = new RaavareFunc();
        try {
            if (!rvFunc.isNewRaavareOk(raavareDTO,getData())) {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error! Tilføje venligst rigtig størrelser: \n navn: => 2 og < 30\n" + "nonNetto: => 0.05 og < 20\n" + "tolerance: => 0.1 og < 20").build());
            }
            raavareDAOSQL.createRaavare(raavareDTO);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }

    public RaavareDTO updateRaavare(RaavareDTO raavareDTO) {
        RaavareFunc rvFunc = new RaavareFunc();
        try {
            if (rvFunc.isUpdateRaavareOk(raavareDTO,getData())) {
                raavareDAOSQL.updateRaavare(raavareDTO);
            }
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }


}

