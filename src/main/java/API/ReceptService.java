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




    @Path("getRecepts")
    @GET
    public List<ReceptDTO> getRecepts() {
        return receptController.getData();
    }

    @GET
    @Path("getRecepts/{receptId}")
    public List<ReceptDTO> getReceptlist (@PathParam("receptId") int receptId){
        return receptController.getuniqueRecept(receptId);
    }

    @GET
    @Path("getRecept/{receptId}/{raavareId}")
    public ReceptDTO getrecept(@PathParam("receptId") int receptID,
                               @PathParam("raavareId") int raavareID){
        return receptController.getRecept(receptID,raavareID);
    }

    @POST
    @Path("opretRecept/{check}")
    public ReceptDTO opretRecept (ReceptDTO receptDTO, @PathParam("check") int check){
        return receptController.opretRecept(receptDTO, check);
    }

    @PUT
    @Path("opdaterRecept")
    public ReceptDTO updateRecept (ReceptDTO receptDTO){
        return receptController.updateRecept(receptDTO);
    }
}
