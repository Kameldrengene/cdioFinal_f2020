package API;

import controller.*;
import dal.IDALException;
import dal.dto.RaavareDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavare")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareService {


    private RaavareController raavareController = new RaavareController();
    @Path("getRaavarer")
    @GET
    public List<RaavareDTO> getData() throws IDALException.DALException {
        return raavareController.getRVList();
    }

    @GET
    @Path("getRaavare/{raavareID}")
    public RaavareDTO getRaavare (@PathParam("raavareID") int raavareID){
        return raavareController.gerRV(raavareID);
    }

    @POST
    @Path("opretRaavare")
    public RaavareDTO opretRaavare (RaavareDTO raavareDTO){
        return raavareController.opretRV(raavareDTO);
    }

    @PUT
    @Path("updaterRaavare")
    public RaavareDTO updateRaavare (RaavareDTO raavareDTO){
        return raavareController.opdaterRV(raavareDTO);
    }

}
