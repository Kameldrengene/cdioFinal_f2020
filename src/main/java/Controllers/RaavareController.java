package Controllers;

import Funktionalitet.RaavareFunc;
import Data.IDALException;
import Data.RaavareDAOSQL;
import Data.dto.RaavareDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class RaavareController {
    public final RaavareDAOSQL raavareDAOSQL;

    public RaavareController (){
        raavareDAOSQL = new RaavareDAOSQL();
    }

    public List<RaavareDTO> getData()  {
        try {
            return raavareDAOSQL.getRaavareList();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO getRaavare(int id) {
        try {
            return raavareDAOSQL.getRaavare(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO opretRaavare (RaavareDTO raavareDTO) {
        RaavareFunc rvFunc = new RaavareFunc();
        try {
            if (!rvFunc.isNewRaavareOk(raavareDTO,getData())) {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error! Tilføje venligst rigtig størrelser: \n ID: => 1 og <= 99999999(8 cifre max)\n" + "RåvareNavn: > 1 og < 21\n" + "Leverandør: > 1 og < 21").build());
            }
            if(rvFunc.IDExists(raavareDTO,getData())){
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error! ID existere allerede i System\nVælge en anden").build());
            }
            if(rvFunc.NavnExists(raavareDTO,getData())){
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error! Navn er optaget\nVælge en anden").build());
            }
            raavareDAOSQL.createRaavare(raavareDTO);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }

    public RaavareDTO updateRaavare(RaavareDTO raavareDTO) {  //todo kraven er ikke opfyldt!
        RaavareFunc rvFunc = new RaavareFunc();
        try {
            if (rvFunc.isUpdateRaavareOk(raavareDTO,getData())) {
                raavareDAOSQL.updateRaavare(raavareDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }


}

