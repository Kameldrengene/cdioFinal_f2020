package API;

import Controllers.RaavareController;

import Data.dto.RaavareDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavare")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareService {


    public final RaavareController raavareController = new RaavareController();
    @Path("getRaavarer")
    @GET
    public List<RaavareDTO> getData() throws WebApplicationException {
        return raavareController.getData();
    }

    @GET
    @Path("getRaavare/{raavareID}")
    public RaavareDTO getRaavare (@PathParam("raavareID") int raavareID){
        return raavareController.getRaavare(raavareID);
    }

    @POST
    @Path("opretRaavare")
    public RaavareDTO opretRaavare (RaavareDTO raavareDTO){
        return raavareController.opretRaavare(raavareDTO);
    }

    @PUT
    @Path("updaterRaavare")
    public RaavareDTO updateRaavare (RaavareDTO raavareDTO){
        return raavareController.updateRaavare(raavareDTO);
    }

}
