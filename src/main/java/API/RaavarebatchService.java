package API;

import controller.RaavarevbatchController;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavarebatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavarebatchService {

    public final RaavarevbatchController raavarebatchController = new RaavarevbatchController();

    // -Mikkel
    @Path("getAlle")
    @GET
    public List<RaavarebatchDTO> getData() throws WebApplicationException{
        return raavarebatchController.getData();
    }

    // -Mikkel
    @Path("getAktuelle")
    @GET
    public List<RaavarebatchDTO> getAktuelle() throws WebApplicationException {
        return raavarebatchController.getAktuelle();
    }

    @Path("getRVIDBatch/{batchID}")
    @GET
    public List<RaavarebatchDTO> getRVIDBatch(@PathParam("batchID") String raavareID) throws WebApplicationException {
        return raavarebatchController.getRVIDBatch(raavareID);
    }

    // -Mikkel
    @Path("opretRaavarebatch")
    @POST
    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO raavarebatchDTO) throws WebApplicationException{
        return raavarebatchController.opretRaavarebatch(raavarebatchDTO);
    }

    //todo slet?
    @Path("getBatch/{batchID}")
    @GET
    public RaavarebatchDTO getBatch(@PathParam("batchID") String batchID) throws WebApplicationException {
        return raavarebatchController.getBatch(batchID);
    }

    //todo slet?
    @Path("opdaterRaavarebatch")
    @POST
    public RaavarebatchDTO updateRaavarebatch(RaavarebatchDTO raavarebatchDTO) {
        return raavarebatchController.updateRaavarebatch(raavarebatchDTO);
    }

}
