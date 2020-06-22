package controller;

import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;
import Funktionalitet.ReceptFunc;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class ReceptController {

    public final ReceptDAOSQL receptDAOSQL;
    private ReceptFunc receptFunc;
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    public ReceptController() {
        receptDAOSQL = new ReceptDAOSQL();
        receptFunc = new ReceptFunc();

    }

    public ReceptDTO getRecept(int receptID, int raavareID) {
        try {
            return receptDAOSQL.getRecept(receptID, raavareID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ReceptDTO> getData() {
        try {
            return receptDAOSQL.getReceptList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }

    }

    public List<ReceptDTO> getuniqueRecept(int receptId) {
        try {
            return receptDAOSQL.getRecepts(receptId);
        }catch(SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);

        }

    }


    public ReceptDTO opretRecept (ReceptDTO recept,int check){
        try {
            ReceptFunc receptFunc = new ReceptFunc();
            if (!(check != 0 || receptFunc.isReceptOk(recept,getData()))) {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error! Tilføje venligst rigtig størrelser: \n navn: >2 og <3\n" + "nonNetto: >= 0,05 og <20\n" + "tolerance: >= 0.1 og <20").build());
            }
            if (!(check != 0 || !receptFunc.doesIdExist(recept, getData()))) {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("ID existerer allerede").build());
            } else {
                receptDAOSQL.createRecept(recept);
            }
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return recept;
    }

    public List<ReceptDTO> opretRecept (List < ReceptDTO > recept) {

        try {
            int i = 0;
            while (i < recept.size()) {
                if (!(receptFunc.isReceptOk(recept.get(i),getData()))) {
                    throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity(receptFunc.receptmsg(recept.get(i),getData())).build());
                }
                i++;
            }
            receptDAOSQL.createReceptList(recept);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recept;
    }


    public ReceptDTO updateRecept (ReceptDTO recept){
        try {
            if (receptFunc.isReceptOk(recept,getData()) && receptFunc.doesIdExist(recept, getData())) {
                receptDAOSQL.updateRecept(recept);
            }
        }catch(SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return recept;
    }

    public WebApplicationException buildError (Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }


}




