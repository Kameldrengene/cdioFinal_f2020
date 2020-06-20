package API;

import Controllers.ReceptController;
import Data.dto.ReceptDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Recept")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptService {

    public final ReceptController receptController = new ReceptController();

    @Path("getRecepts")
    @GET
    public List<ReceptDTO> getRecepts() throws WebApplicationException{
        return receptController.getData();
    }


    @GET
    @Path("getRecept/{receptId}/{raavareId}")
    public ReceptDTO getrecept(@PathParam("receptId") int receptID, @PathParam("raavareId") int raavareID) throws WebApplicationException{
        return receptController.getRecept(receptID,raavareID);
    }

    @GET
    @Path("getRecepts/{receptId}")
    public List<ReceptDTO> getReceptlist (@PathParam("receptId") int receptId) throws WebApplicationException{
        return receptController.getuniqueRecept(receptId);
    }

    @POST
    @Path("opretRecept/{check}")
    public ReceptDTO opretRecept (ReceptDTO receptDTO, @PathParam("check") int check) throws WebApplicationException{
        return receptController.opretRecept(receptDTO, check);
    }

    @POST
    @Path("OpretRecept")
    public List<ReceptDTO> OpretRecept (List<ReceptDTO> receptDTO) throws WebApplicationException{
        return receptController.opretRecept(receptDTO);
    }

    @PUT
    @Path("opdaterRecept")
    public ReceptDTO updateRecept (ReceptDTO receptDTO) throws WebApplicationException{
        return receptController.updateRecept(receptDTO);
    }
}
