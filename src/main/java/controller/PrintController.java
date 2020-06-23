package controller;


import dal.PrintDAOSQL;
import dal.dto.PrintDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class PrintController {

    public final PrintDAOSQL printDAOSQL;
    public PrintController(){
        printDAOSQL = new PrintDAOSQL();
    }

    /**
     * Indenholder de informationer der skal printes
     * @param pbID Produktbatch ID
     * @return Liste over informationer på en produktbatch
     */
    public List<PrintDTO> getData(int pbID){
        try {
            return printDAOSQL.getPrint(pbID);
        } catch (SQLException e) {
            String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }
    /**
     * Kaster en Exception videre hvis der sker en fejl i systemet
     * @param status Web status
     * @param msg Besked der skal stå i fejl meddelelse
     * @return returner en Webapplication Exception
     */
    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
