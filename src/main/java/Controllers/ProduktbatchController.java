package Controllers;

import dal.ProduktbatchDAOSQL;
import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class ProduktbatchController {

    private final ProduktbatchDAOSQL DAOSQL;
    private final String SQLErrorMsg = "ERROR: Fejl i forsøg på at kontakte databasen. Prøv igen senere";

    public ProduktbatchController(){
        DAOSQL = new ProduktbatchDAOSQL();
    }

    // -Mikkel
    public List<ProduktbatchDTO> getAlle(){
        try {
            return DAOSQL.getProduktBatchList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel
    public List<ProduktbatchDTO> getAktuelle(){
        try {
            return DAOSQL.getAktuelProduktBatchList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel
    public int getMaxPDID(){
        try{
            return DAOSQL.getMaxPDID();
        } catch(SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel
    public ProduktbatchDTO opretProduktbatch(ProduktbatchDTO produktbatchDTO){

        try {
            DAOSQL.createProduktBatch(produktbatchDTO);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return produktbatchDTO;
    }

    // -Mikkel
    public ProduktbatchDTO getBatchLine(String batchID){
        int batchIDint = Integer.parseInt(batchID);

        try {
            return DAOSQL.getBatchLine(batchIDint);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public ProduktbatchKompDTO getBatchComponent(String batchID, String RBID){
        int batchIDint = Integer.parseInt(batchID);
        int RBIDint = Integer.parseInt(RBID);

        try {
            return DAOSQL.getBatchkomponent(batchIDint, RBIDint);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public List<ProduktbatchKompDTO> getBatchComponents(String batchID){
        int batchIDint = Integer.parseInt(batchID);

        try {
            return DAOSQL.getBatchkomponents(batchIDint);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public ProduktbatchKompDTO opdaterProduktbatchLine(ProduktbatchKompDTO produktbatchKompDTO){
        try {
            DAOSQL.updateProduktBatchLine(produktbatchKompDTO);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return produktbatchKompDTO;
    }

    public ProduktbatchDTO opdaterProduktbatch(ProduktbatchDTO produktbatchDTO){
        try {
            DAOSQL.updateProduktBatch(produktbatchDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            throw DAOSQL.getdb().buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return produktbatchDTO;
    }

    public ProduktbatchKompDTO opdaterNewProduktbatch(ProduktbatchKompDTO produktbatchKompDTO){
        try {
            DAOSQL.updateNewpb(produktbatchKompDTO);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return produktbatchKompDTO;
    }


    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
