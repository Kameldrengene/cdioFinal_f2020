package Controllers;


import Data.PrintDAOSQL;
import Data.dto.PrintDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class PrintController {

    public final PrintDAOSQL printDAOSQL;
    public PrintController(){
        printDAOSQL = new PrintDAOSQL();
    }
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    public List<PrintDTO> getData(int pbID, int receptID){
        try {
            return printDAOSQL.getPrint(pbID,receptID);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
