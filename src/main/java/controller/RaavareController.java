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

    // kontrutøren initialisere råvareDaoSql og råvarefunc.
    public RaavareController (){
        raavareDAOSQL = new RaavareDAOSQL();
        rvFunc = new RaavareFunc();
    }
    //henter alle råvare fra databasen
    public List<RaavareDTO> getData()  {
        try {
            return raavareDAOSQL.getRaavareList();
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }
    // henter en råvare fra databasen
    public RaavareDTO getRaavare(int id) {
        try {
            return raavareDAOSQL.getRaavare(id);
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }
    //opretter råvare hvis alle kravene er opfyldt
    public RaavareDTO opretRaavare (RaavareDTO raavareDTO) {
        String str = "POST";
        try {
            if (!rvFunc.isNewRaavareOk(raavareDTO,getData())) {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity(rvFunc.raavaremsg(raavareDTO,getData(),str)).build());
            }
            raavareDAOSQL.createRaavare(raavareDTO);
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return raavareDTO;
    }
    //opdatere råvare hvis alle kravene er opfyldt.
    public RaavareDTO updateRaavare(RaavareDTO raavareDTO) {
        String str = "PUT";
        try {
            if (!rvFunc.isUpdateRaavareOk(raavareDTO,getData())) {
                throw buildError(Response.Status.NOT_ACCEPTABLE,rvFunc.raavaremsg(raavareDTO,getData(),str));
            }
            raavareDAOSQL.updateRaavare(raavareDTO);
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }

        return raavareDTO;
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }


}

