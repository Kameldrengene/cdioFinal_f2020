package API;

import controller.RaavarevbatchController;
import dal.IDALException;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavarebatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavarebatchService {

    public RaavarevbatchController raavarebatchController = new RaavarevbatchController();

    @Path("getAlle")
    @GET
    public List<RaavarebatchDTO> getData() throws IDALException.DALException {
        return raavarebatchController.getData();
    }

    @Path("getAktuelle")
    @GET
    public List<RaavarebatchDTO> getAktuelle() throws IDALException.DALException {
        return raavarebatchController.getAktuelle();
    }

    @Path("getBatch/{batchID}")
    @GET
    public RaavarebatchDTO getBatch(@PathParam("batchID") String batchID) throws IDALException.DALException {
        return raavarebatchController.getBatch(batchID);
    }

    @Path("opdaterRaavarebatch")
    @POST
    public RaavarebatchDTO updateRaavarebatch(RaavarebatchDTO raavarebatchDTO) {
        return raavarebatchController.updateRaavarebatch(raavarebatchDTO);
    }

    @Path("opretRaavarebatch")
    @POST
    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO raavarebatchDTO) throws WebApplicationException{
        return raavarebatchController.opretRaavarebatch(raavarebatchDTO);
    }

}
