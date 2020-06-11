package dal;

import dal.dto.ReceptDTO;

import java.util.List;

public interface IReceptDAO {
    ReceptDTO getRC(int receptId) throws IDALException.DALException;
    List<ReceptDTO> getRCList() throws IDALException.DALException;
    void opretRC(ReceptDTO recept) throws IDALException.DALException;
    void opdaterRC(ReceptDTO recept) throws IDALException.DALException;

}
