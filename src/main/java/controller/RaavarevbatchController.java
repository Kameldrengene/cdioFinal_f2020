package controller;

import Funktionalitet.RaavarebatchFunc;
import dal.IDALException;
import dal.RaavarebatchDAOSQL;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class RaavarevbatchController {

    public RaavarebatchDAOSQL DAOSQL;
    private RaavarebatchFunc func;


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

    //todo slet?
    public RaavarebatchDTO getBatch(String batchID) throws IDALException.DALException{

        int batchIDint = Integer.parseInt(batchID);
        return DAOSQL.getRaavarebatch(batchIDint);
    }

    //todo slet?
    public RaavarebatchDTO updateRaavarebatch(RaavarebatchDTO raavarebatchDTO) {
        try {
            DAOSQL.updateRaavarebatch(raavarebatchDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return raavarebatchDTO;
    }

    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO dto) throws WebApplicationException{


        //Valider startmængde
        String startMaengdeCheck = func.startMaengdeOk(dto);
        if( !startMaengdeCheck.equals("OK") ){
            throw buildError(Response.Status.INTERNAL_SERVER_ERROR, startMaengdeCheck);
        }

        //Valider batch ID
        String batchIdCheck = func.batchIdOk(dto);
        if( !batchIdCheck.equals("OK") )
            throw buildError(Response.Status.INTERNAL_SERVER_ERROR, batchIdCheck);

        try {
            DAOSQL.createRaavarebatch(dto);
        } catch (IDALException.DALException e) {
            throw buildError(Response.Status.INTERNAL_SERVER_ERROR, "ERROR: Fejl i forsøg på at kontakte databasen");
        }

        return dto;

    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
