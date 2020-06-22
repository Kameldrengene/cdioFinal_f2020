package API;

import controller.ReceptController;
import dal.dto.ReceptDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Recept")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptService {

    public final ReceptController receptController = new ReceptController();

    //henter alle recepter
    @Path("getRecepts")
    @GET
    public List<ReceptDTO> getRecepts() throws WebApplicationException{
        return receptController.getData();
    }

    //henter en recept med ens bestemte komponent
    @GET
    @Path("getRecept/{receptId}/{raavareId}")
    public ReceptDTO getrecept(@PathParam("receptId") int receptID, @PathParam("raavareId") int raavareID) throws WebApplicationException{
        return receptController.getRecept(receptID,raavareID);
    }

    //henter alle komponenter af en recept.
    @GET
    @Path("getRecepts/{receptId}")
    public List<ReceptDTO> getReceptKomponents (@PathParam("receptId") int receptId) throws WebApplicationException{
        return receptController.getuniqueReceptkomponents(receptId);
    }

    //opretter en recept med alle sine komponenter.
    @POST
    @Path("OpretRecept")
    public List<ReceptDTO> OpretRecept (List<ReceptDTO> receptDTO) throws WebApplicationException{
        return receptController.opretRecept(receptDTO);
    }
}
