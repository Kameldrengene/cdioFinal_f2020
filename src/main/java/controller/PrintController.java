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
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    public List<PrintDTO> getData(int pbID){
        try {
            return printDAOSQL.getPrint(pbID);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
