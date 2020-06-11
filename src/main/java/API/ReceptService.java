package API;

import controller.ReceptController;
import dal.dto.ReceptDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("RC")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptService {
    public ReceptController receptController = new ReceptController();

    @Path("getRCList")
    @GET
    public List<ReceptDTO> getRCList() {
       return receptController.getRCList();
    }


    @GET
    @Path("getRC/{RCID}")
    public ReceptDTO getRC(@PathParam("RCID") int RCID){
        return receptController.getRC(RCID);
    }

    @POST
    @Path("opretRC")
    public ReceptDTO opretRC(ReceptDTO rc){
        return receptController.opretRC(rc);
    }

    @PUT
    @Path("opdaterRC")
    public ReceptDTO opdaterRC(ReceptDTO rc){
        return receptController.opdaterRC(rc);
    }
}
