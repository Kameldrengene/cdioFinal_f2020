package controller;

import Funktionalitet.ReceptFunc;
import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.UserDAOSQL;
import dal.dto.ReceptDTO;
import dal.dto.UserDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class ReceptController {
    public ReceptDAOSQL receptDAOSQL;

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
            boolean checkError = true;
                for (int i = 0; i < recept.size(); i++) {
                    ReceptFunc receptFunc = new ReceptFunc();
                    if (!(receptFunc.isReceptOk(recept.get(i)))) {
                        throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error! Tilføje venligst rigtig størrelser: \n navn: >2 og <3\n" + "nonNetto: >= 0,05 og <20\n" + "tolerance: >= 0.1 og <20").build());
                    }
                    if ((receptFunc.doesIdExist(recept.get(i), getData()))) {
                        throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity("ID existerer allerede").build());
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



