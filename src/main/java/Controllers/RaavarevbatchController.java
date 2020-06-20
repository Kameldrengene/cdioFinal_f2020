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
    private final String SQLErrorMsg = "ERROR: Fejl i forsøg på at kontakte databasen. Prøv igen senere";

    //Konstruktør
    public RaavarevbatchController() {
        DAOSQL = new RaavarebatchDAOSQL();
        func = new RaavarebatchFunc();
    }

    // -Mikkel
    public List<RaavarebatchDTO> getData(){
        try {
            return DAOSQL.getRaavarebatchList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel
    public List<RaavarebatchDTO> getAktuelle(){
        try {
            return DAOSQL.getAktuelRaavarebatchList();
        } catch (Exception e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel
    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO dto) {

        //Valider startmængde
        String startMaengdeMsg = func.startMaengdeOk(dto);
        if( !startMaengdeMsg.equals("OK") ){
            throw buildError(Response.Status.NOT_ACCEPTABLE, startMaengdeMsg);
        }

        try {

            //Valider batch ID
            String batchIdMsg = func.batchIdOk(dto);
            if( !batchIdMsg.equals("OK") )
                throw buildError(Response.Status.NOT_ACCEPTABLE, batchIdMsg);

            DAOSQL.createRaavarebatch(dto);

        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }

        return dto;
    }

    public List<RaavarebatchDTO> getRVIDBatch(String RVID){

        int RVIDint = Integer.parseInt(RVID);

        try{
            return DAOSQL.getRVIDBatch(RVIDint);
        } catch(SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }

    }

    //todo slet?
    public RaavarebatchDTO getBatch(String batchID){

        int batchIDint = Integer.parseInt(batchID);
        try {
            return DAOSQL.getRaavarebatch(batchIDint);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    //todo slet?
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