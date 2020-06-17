package API;

import controller.ProduktbatchController;
import controller.RaavarevbatchController;
import dal.IDALException;
import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("produktbatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProduktbatchService {

    public ProduktbatchController produktbatchController = new ProduktbatchController();

    //Hent alle batches
    @Path("getAlle")
    @GET
    public List<ProduktbatchDTO> getAlle() throws IDALException.DALException {
        return produktbatchController.getAlle();
    }

    //Hent alle aktuelle batches
    @Path("getAktuelle")
    @GET
    public List<ProduktbatchDTO> getAktuelle() throws IDALException.DALException {
        return produktbatchController.getAktuelle();
    }

    //Hent alle linjer fra ét enkelt batch
    //todo ændres til -komp
    @Path("getBatch/{batchID}")
    @GET
    public List<ProduktbatchKompDTO> getBatch(@PathParam("batchID") String batchID) throws IDALException.DALException {
        return produktbatchController.getBatch(batchID);
    }

    //Opdater én enkelt linje i ét enkelt batch
    //todo ændres til -komp
    @Path("opdaterProduktbatch")
    @POST
    public ProduktbatchKompDTO updateRaavarebatch(ProduktbatchKompDTO produktbatchKompDTO) {
        return produktbatchController.opdaterProduktbatch(produktbatchKompDTO);
    }

    //Oprettet et produktbatch
    @Path("opretProduktbatch")
    @POST
    public ProduktbatchDTO opretRaavarebatch(ProduktbatchDTO produktbatchDTO){
        return produktbatchController.opretProduktbatch(produktbatchDTO);
    }

}
