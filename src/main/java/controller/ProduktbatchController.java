package controller;

import dal.IDALException;
import dal.ProduktbatchDAOSQL;
import dal.RaavarebatchDAOSQL;
import dal.dto.ProduktbatchDTO;
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

    public ProduktbatchDTO getBatch(String batchID){
        int batchIDint = Integer.parseInt(batchID);

        try {
            return DAOSQL.getProduktBatch(batchIDint);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProduktbatchDTO opdaterProduktbatch(ProduktbatchDTO produktbatchDTO){
        try {
            DAOSQL.updateProduktBatch(produktbatchDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return produktbatchDTO;
    }

    public ProduktbatchDTO opretProduktbatch(ProduktbatchDTO produktbatchDTO){
        try {
            DAOSQL.createProduktBatch(produktbatchDTO);
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return produktbatchDTO;
    }


}
