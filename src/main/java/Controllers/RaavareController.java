package Controllers;

import Funktionalitet.RaavareFunc;

import Data.RaavareDAOSQL;
import Data.dto.RaavareDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class RaavareController {

    public final RaavareDAOSQL raavareDAOSQL;
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    public RaavareController (){
        raavareDAOSQL = new RaavareDAOSQL();
    }

    public List<RaavareDTO> getData()  {
        try {
            return raavareDAOSQL.getRaavareList();
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public RaavareDTO getRaavare(int id) {
        try {
            return raavareDAOSQL.getRaavare(id);
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public RaavareDTO opretRaavare (RaavareDTO raavareDTO) {
        RaavareFunc rvFunc = new RaavareFunc();
        try {
            if (rvFunc.isNewRaavareOk(raavareDTO,getData())) {
                raavareDAOSQL.createRaavare(raavareDTO);
            }
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return raavareDTO;
    }

    public RaavareDTO updateRaavare(RaavareDTO raavareDTO) {
        RaavareFunc rvFunc = new RaavareFunc();
        try {
            if (rvFunc.isUpdateRaavareOk(raavareDTO,getData())) {
                raavareDAOSQL.updateRaavare(raavareDTO);
            }
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return raavareDTO;
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }


}

