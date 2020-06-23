package controller;

import dal.ProduktbatchDAOSQL;
import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class ProduktbatchController {

    public final ProduktbatchDAOSQL DAOSQL;
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    public ProduktbatchController(){
        DAOSQL = new ProduktbatchDAOSQL();
    }

    // -Mikkel

    /**
     * Get list of all productbatches
     * @return Liste over alle produktbatches
     */
    public List<ProduktbatchDTO> getAlle(){
        try {
            return DAOSQL.getProduktBatchList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel

    /**
     * Get list of all active productbatches
     * @return LIste over alle aktive produktbatches
     */
    public List<ProduktbatchDTO> getAktuelle(){
        try {
            return DAOSQL.getAktuelProduktBatchList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel

    /**
     * Get one productbatch
     * @param batchID Produktbatch ID
     * @return Produktbatchet som svarer til produktbatch ID
     */
    public ProduktbatchDTO getBatchLine(String batchID){
        int batchIDint = Integer.parseInt(batchID);

        try {
            return DAOSQL.getBatchLine(batchIDint);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    /**
     * Get one productbatch component
     * @param batchID Produktbatch ID
     * @param RBID Produktbatchkomp ID
     * @return Produktbatchkomp svarer til den angivet ID
     */
    public ProduktbatchKompDTO getBatchComponent(String batchID, String RBID){
        int batchIDint = Integer.parseInt(batchID);
        int RBIDint = Integer.parseInt(RBID);

        try {
            return DAOSQL.getBatchkomponent(batchIDint, RBIDint);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    /**
     * Get all productbatch components of one productbatch
     * @param batchID Produktbatch ID
     * @return LIste over alle komponenter i en produktbatch
     */
    public List<ProduktbatchKompDTO> getBatchComponents(String batchID){
        int batchIDint = Integer.parseInt(batchID);

        try {
            return DAOSQL.getBatchkomponents(batchIDint);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel

    /**
     * Get the heighest productbatch ID-number
     * @return Den højeste produktbatch nummer
     */
    public int getMaxPDID(){
        try{
            return DAOSQL.getMaxPDID();
        } catch(SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    // -Mikkel

    /**
     * Create product batch
     * @param produktbatchDTO Produktbatch Data transfer Objekt
     * @return Den oprettede produktbatch objekt
     */
    public ProduktbatchDTO opretProduktbatch(ProduktbatchDTO produktbatchDTO){

        try {
            DAOSQL.createProduktBatch(produktbatchDTO);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return produktbatchDTO;
    }

    /**
     * Update a whole product batch
     * @param produktbatchDTO Produktbatch Data transfer Objekt
     * @return Den opdaterede produktbatch objekt
     */
    public ProduktbatchDTO opdaterProduktbatch(ProduktbatchDTO produktbatchDTO){
        try {
            DAOSQL.updateProduktBatch(produktbatchDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            throw DAOSQL.getdb().buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return produktbatchDTO;
    }

    /**
     * Update one productbatch component
     * @param produktbatchKompDTO Produktbatch Data transfer Objekt
     * @return Den opdaterede Produktbatch række
     */
    public ProduktbatchKompDTO opdaterProduktbatchLine(ProduktbatchKompDTO produktbatchKompDTO){
        try {
            DAOSQL.updateProduktBatchkomponent(produktbatchKompDTO);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return produktbatchKompDTO;
    }

    /**
     * Opdater ny produktbatch
     * @param produktbatchKompDTO Produktbatch Data transfer Objekt
     * @return Den opretede produktbatch række
     */
    public ProduktbatchKompDTO opdaterNewProduktbatch(ProduktbatchKompDTO produktbatchKompDTO){
        try {
            DAOSQL.updateNewpb(produktbatchKompDTO);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return produktbatchKompDTO;
    }
    /**
     * Kaster en Exception videre hvis der sker en fejl i systemet
     * @param status Web status
     * @param msg Besked der skal stå i fejl meddelelse
     * @return returner en Webapplication Exception
     */
    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
