package API;

import controller.RaavarevbatchController;
import dal.IDALException;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("RVB")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavarebatchService {
    public RaavarevbatchController RVBController = new RaavarevbatchController();

    @Path("getRVBList")
    @GET
    public List<RaavarebatchDTO> getRVBList() throws IDALException.DALException {
        return RVBController.getRVBList();
    }

    @Path("getAktuelRVBList")
    @GET
    public List<RaavarebatchDTO> getAktuelRVBList() throws IDALException.DALException {
        return RVBController.getAktuelRVBList();
    }

    @Path("getRVB/{RVBID}")
    @GET
    public RaavarebatchDTO getRVB(@PathParam("RVBID") int RVBID) throws IDALException.DALException {
        return RVBController.getRVB(RVBID);
    }


}
