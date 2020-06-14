package API;

import controller.ReceptController;
import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Recept")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptService {
    public ReceptController receptController = new ReceptController();

    @Path("getRecepts/data")
    @GET
    public List<ReceptDTO> getData() {
        return receptController.getData();
    }

    @Path("getRecepts")
    @GET
    public List<ReceptDTO> getRecepts() {
        return receptController.getData();
    }

    @GET
    @Path("getRecept/{receptId}")
    public List<ReceptDTO> getReceptlist (@PathParam("receptId") int receptId){
        return receptController.getuniqueRecept(receptId);
    }

    @POST
    @Path("opretRecept")
    public ReceptDTO opretRecept (ReceptDTO receptDTO){
        return receptController.opretRecept(receptDTO);
    }

    @PUT
    @Path("opdaterRecept")
    public ReceptDTO updateRecept (ReceptDTO receptDTO){
        return receptController.updateRecept(receptDTO);
    }
}
