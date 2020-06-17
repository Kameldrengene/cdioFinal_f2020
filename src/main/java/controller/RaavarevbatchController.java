package controller;

import dal.IDALException;
import dal.RaavarebatchDAOSQL;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class RaavarevbatchController {

    public RaavarebatchDAOSQL DAOSQL;


    //Konstruktør
    public RaavarevbatchController() {
        DAOSQL = new RaavarebatchDAOSQL();
    }

    public List<RaavarebatchDTO> getData() throws IDALException.DALException {
        return DAOSQL.getRaavarebatchList();
    }


    public List<RaavarebatchDTO> getAktuelle() throws IDALException.DALException {
        return DAOSQL.getAktuelRaavarebatchList();
    }

    public RaavarebatchDTO getBatch(String batchID) throws IDALException.DALException{

        int batchIDint = Integer.parseInt(batchID);
        return DAOSQL.getRaavarebatch(batchIDint);
    }

    public RaavarebatchDTO updateRaavarebatch(RaavarebatchDTO raavarebatchDTO) {
        try {
            DAOSQL.updateRaavarebatch(raavarebatchDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return raavarebatchDTO;
    }

    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO raavarebatchDTO) throws WebApplicationException{


//        throw buildError(Response.Status.INTERNAL_SERVER_ERROR, "Mike test");

//        try {
//            DAOSQL.createRaavarebatch(raavarebatchDTO);
//        } catch (IDALException.DALException e) {
//            e.printStackTrace();
//        }
        System.out.println(raavarebatchDTO);
        return raavarebatchDTO;
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
