package API;

import Controllers.RaavarevbatchController;
import Data.dto.RaavarebatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavarebatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class  RaavarebatchService {

    public final RaavarevbatchController raavarebatchController = new RaavarevbatchController();

    // -Mikkel
    //Get list of all raavarebatches
    @Path("getAlle")
    @GET
    public List<RaavarebatchDTO> getData() throws WebApplicationException{
        return raavarebatchController.getData();
    }

    // -Mikkel
    //Get list of all active raavarebatches
    @Path("getAktuelle")
    @GET
    public List<RaavarebatchDTO> getAktuelle() throws WebApplicationException {
        return raavarebatchController.getAktuelle();
    }

    //Get one specific raavarebatch
    @Path("getBatch/{batchID}")
    @GET
    public RaavarebatchDTO getBatch(@PathParam("batchID") String batchID) throws WebApplicationException {
        return raavarebatchController.getBatch(batchID);
    }

    //todo comment
    @Path("getRVIDBatch/{batchID}")
    @GET
    public List<RaavarebatchDTO> getRVIDBatch(@PathParam("batchID") String raavareID) throws WebApplicationException {
        return raavarebatchController.getRVIDBatch(raavareID);
    }

    // -Mikkel
    //Create raavarebatch
    @Path("opretRaavarebatch")
    @POST
    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO raavarebatchDTO) throws WebApplicationException{
        return raavarebatchController.opretRaavarebatch(raavarebatchDTO);
    }

    //Update raavarebatch
    @Path("opdaterRaavarebatch")
    @POST
    public RaavarebatchDTO updateRaavarebatch(RaavarebatchDTO raavarebatchDTO) throws WebApplicationException{
        return raavarebatchController.updateRaavarebatch(raavarebatchDTO);
    }

}
