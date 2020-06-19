package API;

import Controllers.ProduktbatchController;
import Data.dto.ProduktbatchDTO;
import Data.dto.ProduktbatchKompDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("produktbatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProduktbatchService {

    public final ProduktbatchController produktbatchController = new ProduktbatchController();

    //Hent alle batches
    @Path("getAlle")
    @GET
    public List<ProduktbatchDTO> getAlle() throws WebApplicationException {
        return produktbatchController.getAlle();
    }

    //Hent alle aktuelle batches
    @Path("getAktuelle")
    @GET
    public List<ProduktbatchDTO> getAktuelle() throws WebApplicationException {
        return produktbatchController.getAktuelle();
    }

    //Hent alle linjer fra ét enkelt batch
    //todo Fix test
    @Path("getBatchComponents/{batchID}")
    @GET
    public List<ProduktbatchKompDTO> getBatchComponents(@PathParam("batchID") String batchID) throws WebApplicationException {
        return produktbatchController.getBatchComponents(batchID);
    }

    @Path("getBatchComponent/{batchID}/{RBID}")
    @GET
    public ProduktbatchKompDTO getBatchComponent(@PathParam("batchID") String batchID, @PathParam("RBID") String RBID) throws WebApplicationException {
        return produktbatchController.getBatchComponent(batchID, RBID);
    }

    @Path("getBatchLine/{batchID}") //TODO Make Test!
    @GET
    public ProduktbatchDTO getBatchLine(@PathParam("batchID") String batchID) throws WebApplicationException {
        return produktbatchController.getBatchLine(batchID);
    }

    //Opdater én enkelt linje i ét enkelt batch
    @Path("opdaterProduktbatchLine")
    @POST
    public ProduktbatchKompDTO updateProduktbatchLine(ProduktbatchKompDTO produktbatchKompDTO) throws WebApplicationException{
        return produktbatchController.opdaterProduktbatchLine(produktbatchKompDTO);
    }

    @Path("opdaterProduktbatch") //TODO make test
    @POST
    public ProduktbatchDTO updateProduktbatch(ProduktbatchDTO produktbatchDTO) throws WebApplicationException{
        return produktbatchController.opdaterProduktbatch(produktbatchDTO);
    }

    @Path("opdaterNewProduktbatch") //TODO make test
    @POST
    public ProduktbatchKompDTO updateNewProduktbatch(ProduktbatchKompDTO produktbatchKompDTO) throws WebApplicationException{
        return produktbatchController.opdaterNewProduktbatch(produktbatchKompDTO);
    }

    // -Mikkel
    @Path("getMaxPBID")
    @GET
    public int getMaxPBID() throws WebApplicationException {
        return produktbatchController.getMaxPDID();
    }

    //Oprettet et produktbatch
    @Path("opretProduktbatch")
    @POST
    public ProduktbatchDTO opretProduktbatch(ProduktbatchDTO produktbatchDTO) throws WebApplicationException{
        return produktbatchController.opretProduktbatch(produktbatchDTO);
    }

    //TODO Erase? Mikkel

}
