package controller;

import Funktionalitet.RaavareFunc;

import dal.RaavareDAOSQL;
import dal.dto.RaavareDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class RaavareController {

    public final RaavareDAOSQL raavareDAOSQL;
    private RaavareFunc rvFunc;
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    public RaavareController (){
        raavareDAOSQL = new RaavareDAOSQL();
        rvFunc = new RaavareFunc();
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
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO opretRaavare (RaavareDTO raavareDTO) {
        try {
            if (!rvFunc.isNewRaavareOk(raavareDTO,getData())) {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity(rvFunc.raavaremsg(raavareDTO,getData())).build());
            }
            raavareDAOSQL.createRaavare(raavareDTO);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }

    public RaavareDTO updateRaavare(RaavareDTO raavareDTO) {  //todo kraven er ikke opfyldt!

        try {
            if (rvFunc.isUpdateRaavareOk(raavareDTO,getData())) {
                raavareDAOSQL.updateRaavare(raavareDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }


}

