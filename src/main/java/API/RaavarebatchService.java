package API;

import controller.RaavarevbatchController;
import dal.IDALException;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Raavarebatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavarebatchService {
    public RaavarevbatchController raavarebatchController = new RaavarevbatchController();

    @Path("getAlle")
    @GET
    public List<RaavarebatchDTO> getData() throws IDALException.DALException {
        return raavarebatchController.getRVBList();
    }

    @Path("getAktuelle")
    @GET
    public List<RaavarebatchDTO> getAktuelle() throws IDALException.DALException {
        return raavarebatchController.getAktuelRVBList();
    }

    @Path("getBatch/{RVBID}")
    @GET
    public RaavarebatchDTO getBatch(@PathParam("RVBID") int RVBID) throws IDALException.DALException {
        return raavarebatchController.getRVB(RVBID);
    }


}
