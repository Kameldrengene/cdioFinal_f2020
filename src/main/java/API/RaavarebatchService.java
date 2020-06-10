package API;

import controller.RaavarevbatchController;
import dal.IDALException;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavarebatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavarebatchService {
    public RaavarevbatchController raavarebatchController = new RaavarevbatchController();
    @Path("getRaavarebatch")
    @GET
    public List<RaavarebatchDTO> getData() throws IDALException.DALException {
        return raavarebatchController.getData();
    }
}
