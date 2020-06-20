package Controllers;

import Funktionalitet.RaavarebatchFunc;
import Data.RaavarebatchDAOSQL;
import Data.dto.RaavarebatchDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class RaavarevbatchController {

    public final RaavarebatchDAOSQL DAOSQL;
    private final RaavarebatchFunc func;
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    //Konstruktør
    public RaavarevbatchController() {
        DAOSQL = new RaavarebatchDAOSQL();
        func = new RaavarebatchFunc();
    }

    // -Mikkel
    //Get list of all raavarebatches
    public List<RaavarebatchDTO> getData(){
        try {
            return DAOSQL.getRaavarebatchList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel
    //Get list of all active raavarebatches
    public List<RaavarebatchDTO> getAktuelle(){
        try {
            return DAOSQL.getAktuelRaavarebatchList();
        } catch (Exception e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    //Get one specific raavarebatch
    public RaavarebatchDTO getBatch(String batchID){

        int batchIDint = Integer.parseInt(batchID);
        try {
            return DAOSQL.getRaavarebatch(batchIDint);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    //todo comment
    public List<RaavarebatchDTO> getRVIDBatch(String RVID){

        int RVIDint = Integer.parseInt(RVID);

        try{
            return DAOSQL.getRVIDBatch(RVIDint);
        } catch(SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }

    }

    // -Mikkel
    //Create raavarebatch
    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO dto) {

        try {
            String validationMsg = func.raavarebatchOk(dto);

            if(validationMsg.equals("OK"))
                DAOSQL.createRaavarebatch(dto);
            else
                throw buildError(Response.Status.NOT_ACCEPTABLE, validationMsg);

        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }

        return dto;

    }

    //Update raavarebatch
    public RaavarebatchDTO updateRaavarebatch(RaavarebatchDTO raavarebatchDTO) {
        try {
            DAOSQL.updateRaavarebatch(raavarebatchDTO);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return raavarebatchDTO;
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
