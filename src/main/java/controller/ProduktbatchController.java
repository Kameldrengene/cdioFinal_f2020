package controller;

import dal.IDALException;
import dal.ProduktbatchDAOSQL;
import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;

import java.util.List;

public class ProduktbatchController {

    private ProduktbatchDAOSQL DAOSQL;

    public ProduktbatchController(){
        DAOSQL = new ProduktbatchDAOSQL();
    }

    public List<ProduktbatchDTO> getAlle() throws IDALException.DALException {
        return DAOSQL.getProduktBatchList();
    }

    public List<ProduktbatchDTO> getAktuelle(){
        try {
            return DAOSQL.getAktuelProduktBatchList();
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProduktbatchKompDTO> getBatchComponents(String batchID){
        int batchIDint = Integer.parseInt(batchID);

        try {
            return DAOSQL.getBatchkomponents(batchIDint);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProduktbatchDTO getBatchLine(String batchID){
        int batchIDint = Integer.parseInt(batchID);


        try {
            return DAOSQL.getBatchLine(batchIDint);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProduktbatchKompDTO opdaterProduktbatchLine(ProduktbatchKompDTO produktbatchKompDTO){
        try {
            DAOSQL.updateProduktBatchLine(produktbatchKompDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return produktbatchKompDTO;
    }

    public ProduktbatchDTO opdaterProduktbatch(ProduktbatchDTO produktbatchDTO){
        try {
            DAOSQL.updateProduktBatch(produktbatchDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return produktbatchDTO;
    }

    public ProduktbatchKompDTO opdaterNewProduktbatch(ProduktbatchKompDTO produktbatchKompDTO){
        try {
            DAOSQL.updateNewpb(produktbatchKompDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return produktbatchKompDTO;
    }

    public ProduktbatchDTO opretProduktbatch(ProduktbatchDTO produktbatchDTO){
        try {
            DAOSQL.createProduktBatch(produktbatchDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return produktbatchDTO;
    }

    public void eraseProduktBatch(String pbId, String RBID) throws IDALException.DALException{
        int pbIdint = Integer.parseInt(pbId);
        int RBIDint = Integer.parseInt(RBID);
        DAOSQL.eraseProduktBatch(pbIdint, RBIDint);
    }



}
