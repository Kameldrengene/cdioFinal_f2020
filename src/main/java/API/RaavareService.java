package API;

import controller.RaavareController;

import dal.dto.RaavareDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavare")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareService {

    public final RaavareController raavareController = new RaavareController();

    /**
     * Get all raavarer
     * @return Liste over alle raavare der findes
     * @throws WebApplicationException
     */
    @Path("getRaavarer")
    @GET
    public List<RaavareDTO> getData() throws WebApplicationException {
        return raavareController.getData();
    }

    /**
     * Get specific raavare
     * @param raavareID Raavare ID
     * @return Raavare objekt ifølge ID
     * @throws WebApplicationException
     */
    @GET
    @Path("getRaavare/{raavareID}")
    public RaavareDTO getRaavare (@PathParam("raavareID") int raavareID) throws WebApplicationException{
        return raavareController.getRaavare(raavareID);
    }

    /**
     * Create raavare
     * @param raavareDTO Raavare Data Transfer Objekt
     * @return Den oprettede Raavare
     * @throws WebApplicationException
     */
    @POST
    @Path("opretRaavare")
    public RaavareDTO opretRaavare (RaavareDTO raavareDTO) throws WebApplicationException{
        return raavareController.opretRaavare(raavareDTO);
    }

    /**
     * Update on raavare
     * @param raavareDTO Raavare Data Transfer Objekt
     * @return Den opdaterede Raavare
     * @throws WebApplicationException
     */
    @PUT
    @Path("updaterRaavare")
    public RaavareDTO updateRaavare (RaavareDTO raavareDTO) throws WebApplicationException{
        return raavareController.updateRaavare(raavareDTO);
    }

}
