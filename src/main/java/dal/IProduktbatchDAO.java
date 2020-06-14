package dal;

import dal.dto.ProduktbatchDTO;

import java.util.List;

public interface IProduktbatchDAO {
    ProduktbatchDTO getProduktBatch(int pbId) throws IDALException.DALException;
    List<ProduktbatchDTO> getProduktBatchList() throws IDALException.DALException;
    List<ProduktbatchDTO> getAktuelProduktBatchList() throws IDALException.DALException;
    void createProduktBatch(ProduktbatchDTO produktbatch) throws IDALException.DALException;
    void updateProduktBatch(ProduktbatchDTO produktbatch) throws IDALException.DALException;

}
