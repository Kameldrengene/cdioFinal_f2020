package dal;

import dal.dto.ReceptDTO;

import java.util.List;

public interface IReceptDAO {
    ReceptDTO getRC(int RCID) throws IDALException.DALException;
    List<ReceptDTO> getRCList() throws IDALException.DALException;
    void opretRC(ReceptDTO rc) throws IDALException.DALException;
    void opdaterRC(ReceptDTO rc) throws IDALException.DALException;

}
