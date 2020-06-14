package API;

import controller.ProduktbatchController;
import controller.RaavarevbatchController;
import dal.IDALException;
import dal.dto.ProduktbatchDTO;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("produktbatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProduktbatchService {

    public ProduktbatchController produktbatchController = new ProduktbatchController();

    @Path("getAlle")
    @GET
    public List<ProduktbatchDTO> getData() throws IDALException.DALException {
        return produktbatchController.getData();
    }

//    @Path("getAktuelle")
//    @GET
//    public List<ProduktbatchDTO> getAktuelle() throws IDALException.DALException {
//        return produktbatchController.getAktuelle();
//    }
//
//    @Path("getBatch/{batchID}")
//    @GET
//    public ProduktbatchDTO getBatch(@PathParam("batchID") String batchID) throws IDALException.DALException {
//        return produktbatchController.getBatch(batchID);
//    }
//
//    @Path("opdaterProduktbatch")
//    @POST
//    public ProduktbatchDTO updateRaavarebatch(ProduktbatchDTO produktbatchDTO) {
//        return produktbatchController.updateRaavarebatch(produktbatchDTO);
//    }
//
//    @Path("opretProduktbatch")
//    @POST
//    public RaavarebatchDTO opretRaavarebatch(ProduktbatchDTO produktbatchDTO){
//        return produktbatchController.opretRaavarebatch(produktbatchDTO);
//    }

}
