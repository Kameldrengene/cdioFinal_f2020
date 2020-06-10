package API;

import controller.ProduktbatchController;
import controller.RaavarevbatchController;
import dal.IDALException;
import dal.dto.ProduktbatchDTO;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("produktbatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProduktbatchService {
    public ProduktbatchController produktbatchController = new ProduktbatchController();

    @Path("getProduktbatch")
    @GET
    public List<ProduktbatchDTO> getData() throws IDALException.DALException {
        return produktbatchController.getData();
    }
}
