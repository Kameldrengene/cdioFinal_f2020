package API;

import controller.ProduktbatchController;
import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("produktbatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProduktbatchService {

    public final ProduktbatchController produktbatchController = new ProduktbatchController();

    /**
     * Get list of all productbatches
     * @return Liste over alle produktbatches
     * @throws WebApplicationException
     */
    @Path("getAlle")
    @GET
    public List<ProduktbatchDTO> getAlle() throws WebApplicationException {
        return produktbatchController.getAlle();
    }

    /**
     * Get list of all active productbatches
     * @return Liste over alle aktive produktbatches
     * @throws WebApplicationException
     */
    @Path("getAktuelle")
    @GET
    public List<ProduktbatchDTO> getAktuelle() throws WebApplicationException {
        return produktbatchController.getAktuelle();
    }

    /**
     * Get one productbatch
     * @param batchID Produktbatch ID
     * @return Produktbatchet som svarer til produktbatch ID
     * @throws WebApplicationException
     */
    @Path("getBatchLine/{batchID}") //TODO Make Test!
    @GET
    public ProduktbatchDTO getBatchLine(@PathParam("batchID") String batchID) throws WebApplicationException {
        return produktbatchController.getBatchLine(batchID);
    }

    /**
     * Get one productbatch component
     * @param batchID Produktbatch ID
     * @param RBID Produktbatchkomp ID
     * @return Produktbatchkomp svarer til den angivet ID
     * @throws WebApplicationException
     */
    @Path("getBatchComponent/{batchID}/{RBID}")
    @GET
    public ProduktbatchKompDTO getBatchComponent(@PathParam("batchID") String batchID, @PathParam("RBID") String RBID) throws WebApplicationException {
        return produktbatchController.getBatchComponent(batchID, RBID);
    }

    /**
     * Get all productbatch components of one productbatch
     * @param batchID Produktbatch ID
     * @return Liste over alle komponenter i en produktbatch
     * @throws WebApplicationException
     */
    //todo Fix test
    @Path("getBatchComponents/{batchID}")
    @GET
    public List<ProduktbatchKompDTO> getBatchComponents(@PathParam("batchID") String batchID) throws WebApplicationException {
        return produktbatchController.getBatchComponents(batchID);
    }

    // -Mikkel

    /**
     * Get the heighest productbatch ID-number
     * @return Den højeste produktbatch nummer
     * @throws WebApplicationException
     */
    @Path("getMaxPBID")
    @GET
    public int getMaxPBID() throws WebApplicationException {
        return produktbatchController.getMaxPDID();
    }

    /**
     * Create product batch
     * @param produktbatchDTO Produktbatch Data transfer Objekt
     * @return Den oprettede produktbatch objekt
     * @throws WebApplicationException
     */
    @Path("opretProduktbatch")
    @POST
    public ProduktbatchDTO opretProduktbatch(ProduktbatchDTO produktbatchDTO) throws WebApplicationException{
        return produktbatchController.opretProduktbatch(produktbatchDTO);
    }

    /**
     * Update a whole product batch
     * @param produktbatchDTO Produktbatch Data transfer Objekt
     * @return Den opdaterede produktbatch objekt
     * @throws WebApplicationException
     */
    @Path("opdaterProduktbatch") //TODO make test
    @POST
    public ProduktbatchDTO updateProduktbatch(ProduktbatchDTO produktbatchDTO) throws WebApplicationException{
        return produktbatchController.opdaterProduktbatch(produktbatchDTO);
    }

    /**
     * Update one productbatch component
     * @param produktbatchKompDTO Produktbatch Data transfer Objekt
     * @return Den opdaterede Produktbatch række
     * @throws WebApplicationException
     */
    @Path("opdaterProduktbatchLine")
    @POST
    public ProduktbatchKompDTO updateProduktbatchLine(ProduktbatchKompDTO produktbatchKompDTO) throws WebApplicationException{
        return produktbatchController.opdaterProduktbatchLine(produktbatchKompDTO);
    }

    /**
     * Opdater ny produktbatch
     * @param produktbatchKompDTO Produktbatch Data transfer Objekt
     * @return Den opdaterede Produktbatch række
     * @throws WebApplicationException
     */
    @Path("opdaterNewProduktbatch") //TODO make test
    @POST
    public ProduktbatchKompDTO updateNewProduktbatch(ProduktbatchKompDTO produktbatchKompDTO) throws WebApplicationException{
        return produktbatchController.opdaterNewProduktbatch(produktbatchKompDTO);
    }

}
