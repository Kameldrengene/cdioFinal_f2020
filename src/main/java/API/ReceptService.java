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

    //Get all recepter
    @Path("getRecepts")
    @GET
    public List<ReceptDTO> getRecepts() throws WebApplicationException{
        return receptController.getData();
    }

    //Get one recept component
    @GET
    @Path("getRecept/{receptId}/{raavareId}")
    public ReceptDTO getrecept(@PathParam("receptId") int receptID, @PathParam("raavareId") int raavareID) throws WebApplicationException{
        return receptController.getRecept(receptID,raavareID);
    }

    //todo comment
    @GET
    @Path("getRecepts/{receptId}")
    public List<ReceptDTO> getReceptlist (@PathParam("receptId") int receptId) throws WebApplicationException{
        return receptController.getuniqueRecept(receptId);
    }

    //Create a recept
    @POST
    @Path("opretRecept/{check}")
    public ReceptDTO opretRecept (ReceptDTO receptDTO, @PathParam("check") int check) throws WebApplicationException{
        return receptController.opretRecept(receptDTO, check);
    }

    //Create multiple recepts at once
    @POST
    @Path("OpretRecept")
    public List<ReceptDTO> OpretRecept (List<ReceptDTO> receptDTO) throws WebApplicationException{
        return receptController.opretRecept(receptDTO);
    }

    //Update a recept
    @PUT
    @Path("opdaterRecept")
    public ReceptDTO updateRecept (ReceptDTO receptDTO) throws WebApplicationException{
        return receptController.updateRecept(receptDTO);
    }
}
