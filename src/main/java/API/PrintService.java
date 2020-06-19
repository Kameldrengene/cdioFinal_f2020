package API;

import controller.PrintController;
import dal.dto.PrintDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Print")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrintService {
   public final PrintController printController = new PrintController();

    @GET
    @Path("getData/{ID}")
    public List<PrintDTO> getData(@PathParam("ID") String ID){
        String[] Ids =ID.split("-");
        int receptID = Integer.parseInt(Ids[1]);
        int pbID = Integer.parseInt(Ids[0]);

       return printController.getData(pbID,receptID);
   }
}
