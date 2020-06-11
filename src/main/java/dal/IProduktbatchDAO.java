package dal;

import dal.dto.ProduktbatchDTO;

import java.util.List;

public interface IProduktbatchDAO {
    ProduktbatchDTO getPB(int PBID) throws IDALException.DALException;
    List<ProduktbatchDTO> getPBList() throws IDALException.DALException;
    void opretPB(ProduktbatchDTO pb) throws IDALException.DALException;
    void opdaterPB(ProduktbatchDTO pb) throws IDALException.DALException;

}
