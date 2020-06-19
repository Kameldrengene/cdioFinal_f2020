package Controllers;

import Data.IDALException;
import Data.ReceptDAOSQL;
import Data.dto.ReceptDTO;
import Funktionalitet.ReceptFunc;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class ReceptController {
    public final ReceptDAOSQL receptDAOSQL;

    public ReceptController(){
        receptDAOSQL = new ReceptDAOSQL();
    }

    public ReceptDTO getRecept (int receptID, int raavareID){
        try {
            return receptDAOSQL.getRecept(receptID,raavareID);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<ReceptDTO> getData()  {
        try {
            return receptDAOSQL.getReceptList();
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ReceptDTO> getuniqueRecept (int receptId) {
        try {
            return receptDAOSQL.getRecepts(receptId);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return null;
    }

    public ReceptDTO opretRecept (ReceptDTO recept, int check){
        try {
            ReceptFunc receptFunc = new ReceptFunc();
            if(!(check !=0 || receptFunc.isReceptOk(recept))){
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error! Tilføje venligst rigtig størrelser: \n navn: >2 og <3\n"+"nonNetto: >= 0,05 og <20\n"+"tolerance: >= 0.1 og <20").build());
            }
            if(!(check != 0 || !receptFunc.doesIdExist(recept, getData()))){
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("ID existerer allerede").build());
            } else {
                receptDAOSQL.createRecept(recept);
            }
        }catch (IDALException.DALException e){
            e.printStackTrace();
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
                        throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error! Tilføje venligst rigtig størrelser: \n navn: => 2 og < 30\n" + "nonNetto: => 0.05 og < 20\n" + "tolerance: => 0.1 og < 20").build());
                    }
                    i++;
                }
                while (j < recept.size()) {
                    ReceptFunc receptFunc = new ReceptFunc();
                    if ((receptFunc.doesIdExist(recept.get(j), getData()))) {
                        throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("ID existerer allerede \n Vælg en anden!").build());
                    }
                    j++;
                }

                for (int k = 0; k < recept.size(); k++) {
                    ReceptFunc receptFunc = new ReceptFunc();
                    if ((receptFunc.doesNameExist(recept.get(k),getData()))) {
                        throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Navn existerer allerede \n Vælg en anden!").build());
                    }
                }
                receptDAOSQL.createReceptList(recept);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return recept;
    }


    public ReceptDTO updateRecept (ReceptDTO recept){
        try {
            ReceptFunc receptFunc = new ReceptFunc();
            if(receptFunc.isReceptOk(recept) && receptFunc.doesIdExist(recept,getData())){
                receptDAOSQL.updateRecept(recept);
            }
            }catch (IDALException.DALException e){
            e.printStackTrace();
        }
        return recept;
    }
}



