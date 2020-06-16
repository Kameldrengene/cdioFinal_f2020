package dal;

import dal.dto.ProduktbatchDTO;

import java.util.List;

public interface IProduktbatchDAO {
    List<ProduktbatchDTO> getProduktBatch(int pbId) throws IDALException.DALException;
    ProduktbatchDTO getProduktBatchLine(int pbId, int RBID) throws IDALException.DALException;
    List<ProduktbatchDTO> getProduktBatchList() throws IDALException.DALException;
    List<ProduktbatchDTO> getAktuelProduktBatchList() throws IDALException.DALException;
    void createProduktBatch(ProduktbatchDTO produktbatch) throws IDALException.DALException;
    void updateProduktBatch(ProduktbatchDTO produktbatch) throws IDALException.DALException;
    void eraseProduktBatch(int pbId, int RBID) throws IDALException.DALException;

}
