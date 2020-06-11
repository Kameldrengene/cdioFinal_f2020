package API;

import controller.RaavareController;
import dal.IDALException;
import dal.dto.RaavareDTO;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavare")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareService {
    public RaavareController raavareController = new RaavareController();
    @Path("getRaavarer")
    @GET
    public List<RaavareDTO> getData() throws IDALException.DALException {
        return raavareController.getData();
    }

    @Path("createRaavare")
    @POST
    public RaavareDTO createUser(RaavareDTO raavare){
        raavareController.createRaavare(raavare);
        return raavare;
    }

    @Path("updateUser")
    @PUT
    public RaavareDTO updateUser(RaavareDTO raavare){
        return raavareController.updateRaavare(raavare);
    }

}
