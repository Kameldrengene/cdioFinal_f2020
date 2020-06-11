package dal;

import dal.dto.ProduktbatchDTO;

import java.util.List;

public interface IProduktbatchDAO {
    ProduktbatchDTO getPB(int pbId) throws IDALException.DALException;
    List<ProduktbatchDTO> getPBList() throws IDALException.DALException;
    void opretPB(ProduktbatchDTO produktbatch) throws IDALException.DALException;
    void opdaterPB(ProduktbatchDTO produktbatch) throws IDALException.DALException;

}
