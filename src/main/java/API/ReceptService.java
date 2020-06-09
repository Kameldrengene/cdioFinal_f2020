package API;

import controller.ReceptController;
import dal.IDALException;
import dal.ReceptDAOSQL;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("Recept")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptService {/*
    public ReceptController receptController = new ReceptController();
    @GET
    public ReceptDAOSQL getData() {
        try {
            return receptController.getData();
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
    }*/
}
