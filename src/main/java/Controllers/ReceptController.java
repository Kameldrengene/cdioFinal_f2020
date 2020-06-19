package Controllers;

import Data.ReceptDAOSQL;
import Data.dto.ReceptDTO;
import Funktionalitet.ReceptFunc;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class ReceptController {

    public final ReceptDAOSQL receptDAOSQL;
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    public ReceptController(){
        receptDAOSQL = new ReceptDAOSQL();
    }

    public ReceptDTO getRecept (int receptID, int raavareID){
        try {
            return receptDAOSQL.getReceptKomponent(receptID,raavareID);
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }
    public List<ReceptDTO> getData()  {
        try {
            return receptDAOSQL.getReceptList();
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    public List<ReceptDTO> getuniqueRecept (int receptId) {
        try {
            return receptDAOSQL.getRecepts(receptId);
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    //todo beskeder skal komme fra func-lag
    public ReceptDTO opretRecept (ReceptDTO recept, int check){

        try {
            ReceptFunc receptFunc = new ReceptFunc();

            if(!(check !=0 || receptFunc.isReceptOk(recept))){

                throw buildError(Response.Status.NOT_ACCEPTABLE, "Error! Tilføje venligst rigtig størrelser: \n navn: >2 og <3\n"+"nonNetto: >= 0,05 og <20\n"+"tolerance: >= 0.1 og <20");
            }

            if(!(check != 0 || !receptFunc.doesIdExist(recept, getData()))){
                throw buildError(Response.Status.NOT_ACCEPTABLE, "ID existerer allerede");
            } else {
                receptDAOSQL.createRecept(recept);
            }

        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return recept;
    }

    public List<ReceptDTO> opretRecept (List<ReceptDTO> recept){

        try {
            int i = 0;
            int j = 0;
                while (i < recept.size()){
                    ReceptFunc receptFunc = new ReceptFunc();
                    if (!(receptFunc.isReceptOk(recept.get(i)))) {
                        throw buildError(Response.Status.NOT_ACCEPTABLE, "Error! Tilføje venligst rigtig størrelser: \n navn: => 2 og < 30\n" + "nonNetto: => 0.05 og < 20\n" + "tolerance: => 0.1 og < 20");
                    }
                    i++;
                }
                while (j < recept.size()) {
                    ReceptFunc receptFunc = new ReceptFunc();
                    if ((receptFunc.doesIdExist(recept.get(j), getData()))) {
                        throw buildError(Response.Status.NOT_ACCEPTABLE, "ID existerer allerede \n Vælg en anden!");
                    }
                    j++;
                }

                for (int k = 0; k < recept.size(); k++) {
                    ReceptFunc receptFunc = new ReceptFunc();
                    if ((receptFunc.doesNameExist(recept.get(k),getData()))) {
                        throw buildError(Response.Status.NOT_ACCEPTABLE, "Navn existerer allerede \n Vælg en anden!");
                    }
                }
                receptDAOSQL.createReceptList(recept);
        }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return recept;
    }


    public ReceptDTO updateRecept (ReceptDTO recept){
        try {
            ReceptFunc receptFunc = new ReceptFunc();
            if(receptFunc.isReceptOk(recept) && receptFunc.doesIdExist(recept,getData())){
                receptDAOSQL.updateRecept(recept);
            }
            }catch (SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return recept;
    }

    public WebApplicationException buildError(Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}



