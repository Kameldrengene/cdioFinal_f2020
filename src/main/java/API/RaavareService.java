package API;

import controller.*;
import dal.IDALException;
import dal.dto.RaavareDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("RV")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareService {

    private RaavareController RVController = new RaavareController();

    @Path("getRVList")
    @GET
    public List<RaavareDTO> getRVList() throws IDALException.DALException {
        return RVController.getRVList();
    }

    @GET
    @Path("getRV/{RVID}")
    public RaavareDTO getRV (@PathParam("RVID") int RVID){
        return RVController.gerRV(RVID);
    }

    @POST
    @Path("opretRV")
    public RaavareDTO opretRV(RaavareDTO rv){
        return RVController.opretRV(rv);
    }

    @PUT
    @Path("opdaterRV")
    public RaavareDTO opdaterRV(RaavareDTO rv){
        return RVController.opdaterRV(rv);
    }

}
