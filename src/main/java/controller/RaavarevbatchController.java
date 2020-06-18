package controller;

import Funktionalitet.RaavarebatchFunc;
import dal.IDALException;
import dal.RaavarebatchDAOSQL;
import dal.dto.RaavarebatchDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class RaavarevbatchController {

    public RaavarebatchDAOSQL DAOSQL;
    private RaavarebatchFunc func;
    private String SQLErrorMsg = "ERROR: Fejl i forsøg på at kontakte databasen. Prøv igen senere";

    //Konstruktør
    public RaavarevbatchController() {
        DAOSQL = new RaavarebatchDAOSQL();
        func = new RaavarebatchFunc();
    }

    // -Mikkel
    public List<RaavarebatchDTO> getData() throws WebApplicationException {
        try {
            return DAOSQL.getRaavarebatchList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    };

    // -Mikkel
    public List<RaavarebatchDTO> getAktuelle() throws WebApplicationException {
        try {
            return DAOSQL.getAktuelRaavarebatchList();
        } catch (Exception e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    };

    // -Mikkel
    public RaavarebatchDTO opretRaavarebatch(RaavarebatchDTO dto) throws WebApplicationException {
        //Valider startmængde
        String startMaengdeMsg = func.startMaengdeOk(dto);
        if( !startMaengdeMsg.equals("OK") ){
            throw buildError(Response.Status.NOT_ACCEPTABLE, startMaengdeMsg);
        }

        try {

            //Valider batch ID
            String batchIdMsg = func.batchIdOk(dto);
            if( !batchIdMsg.equals("OK") )
                throw buildError(Response.Status.NOT_ACCEPTABLE, batchIdMsg);

            DAOSQL.createRaavarebatch(dto);

        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }

        return dto;
    };

    public List<RaavarebatchDTO> getRVIDBatch(String RVID) throws IDALException.DALException{

        int RVIDint = Integer.parseInt(RVID);
        return DAOSQL.getRVIDBatch(RVIDint);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raavarebatchDTO;
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
