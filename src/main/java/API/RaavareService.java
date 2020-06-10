package API;

import controller.RaavareController;
import dal.IDALException;
import dal.dto.RaavareDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavare")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareService {
    public RaavareController raavareController = new RaavareController();
    @Path("getRaavare")
    @GET
    public List<RaavareDTO> getData() throws IDALException.DALException {
        return raavareController.getData();
    }

}
