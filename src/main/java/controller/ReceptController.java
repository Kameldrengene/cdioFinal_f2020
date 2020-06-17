package controller;

import Funktionalitet.ReceptFunc;
import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.UserDAOSQL;
import dal.dto.ReceptDTO;
import dal.dto.UserDTO;

import java.util.List;

public class ReceptController {
    private ReceptDAOSQL receptDAOSQL;

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

    public ReceptDTO opretRecept (ReceptDTO recept){
        try {
            ReceptFunc receptFunc = new ReceptFunc();
            if(receptFunc.isReceptOk(recept) && !receptFunc.doesIdExist(recept, getData())){
                receptDAOSQL.createRecept(recept);
            }
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



