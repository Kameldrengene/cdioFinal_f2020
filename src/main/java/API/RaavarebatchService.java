package API;

import controller.RaavarevbatchController;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavarebatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class  RaavarebatchService {

    public final RaavarevbatchController raavarebatchController = new RaavarevbatchController();

    // -Mikkel

    /**
     * Get list of all raavarebatches
     * @return Liste over alle Raavarebatches
     * @throws WebApplicationException
     */
    @Path("getAlle")
    @GET
    public List<RaavarebatchDTO> getData() throws WebApplicationException{
        return raavarebatchController.getData();
    }

    // -Mikkel

    /**
     * Get list of all active raavarebatches
     * @return Liste over de akruelle raavarebatches
     * @throws WebApplicationException
     */
    @Path("getAktuelle")
    @GET
    public List<RaavarebatchDTO> getAktuelle() throws WebApplicationException {
        return raavarebatchController.getAktuelle();
    }

    /**
     * Get one specific raavarebatch
     * @param batchID raavarebatch ID
     * @return Raavarebatch objekt ifølge ID
     * @throws WebApplicationException
     */
    @Path("getBatch/{batchID}")
    @GET
    public RaavarebatchDTO getBatch(@PathParam("batchID") String batchID) throws WebApplicationException {
        return raavarebatchController.getBatch(batchID);
    }

    /**
     * Return liste med RaavareBatchDTO'er
     * @param raavareID RaavareBatch ID
     * @return Return liste af RaavareBatchDTO'er
     * @throws WebApplicationException
     */
    @Path("getRVIDBatch/{batchID}")
    @GET
    public List<RaavarebatchDTO> getRVIDBatch(@PathParam("batchID") String raavareID) throws WebApplicationException {
        return raavarebatchController.getRVIDBatch(raavareID);
    }

    // -Mikkel

    /**
     * Create raavarebatch
     * @param raavarebatchDTO Raavarebatch Data Transfer Objekt
     * @return Den oprettede raavarebatch
     * @throws WebApplicationException
     */
    @Path("opretRaavarebatch")
    @POST
    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO raavarebatchDTO) throws WebApplicationException{
        return raavarebatchController.opretRaavarebatch(raavarebatchDTO);
    }

    /**
     * Update raavarebatch
     * @param raavarebatchDTO Raavarebatch Data Transfer Objekt
     * @return Den opdaterede Raavarebatch
     * @throws WebApplicationException
     */
    @Path("opdaterRaavarebatch")
    @POST
    public RaavarebatchDTO updateRaavarebatch(RaavarebatchDTO raavarebatchDTO) throws WebApplicationException{
        return raavarebatchController.updateRaavarebatch(raavarebatchDTO);
    }

}
