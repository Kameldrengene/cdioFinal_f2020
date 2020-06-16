package controller;

import dal.IDALException;
import dal.ProduktbatchDAOSQL;
import dal.RaavarebatchDAOSQL;
import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;
import dal.dto.RaavarebatchDTO;

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

    public List<ProduktbatchKompDTO> getBatch(String batchID){
        int batchIDint = Integer.parseInt(batchID);

        try {
            return DAOSQL.getProduktBatch(batchIDint);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProduktbatchDTO getBatchLine(String batchID, String RBID){
        int batchIDint = Integer.parseInt(batchID);
        int RBIDint = Integer.parseInt(RBID);

        try {
            return DAOSQL.getProduktBatchLine(batchIDint, RBIDint);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProduktbatchKompDTO opdaterProduktbatch(ProduktbatchKompDTO produktbatchKompDTO){
        try {
            DAOSQL.updateProduktBatch(produktbatchKompDTO);
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
