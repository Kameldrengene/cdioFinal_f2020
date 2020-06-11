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
    public ReceptController receptController = new ReceptController();

    @Path("getRecepts")
    @GET
    public List<ReceptDTO> getData() {
       return receptController.getRCList();
    }


    @GET
    @Path("getRecept/{receptId}")
    public ReceptDTO getRecept (@PathParam("receptId") int receptId){
        return receptController.getRC(receptId);
    }

    @POST
    @Path("opretRecept")
    public ReceptDTO opretRecept (ReceptDTO receptDTO){
        return receptController.opretRC(receptDTO);
    }

    @PUT
    @Path("opdaterRecept")
    public ReceptDTO updateRecept (ReceptDTO receptDTO){
        return receptController.opdaterRC(receptDTO);
    }
}
