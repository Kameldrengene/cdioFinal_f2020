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
    public List<ReceptDTO> getData() {
        try {
            return receptController.getData();
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }
}
