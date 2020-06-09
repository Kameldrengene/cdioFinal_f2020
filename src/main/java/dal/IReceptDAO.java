package dal;

import dal.dto.ReceptDTO;

import java.util.List;

public interface IReceptDAO {
    ReceptDTO getRecept(int receptId) throws IDALException.DALException;
    List<ReceptDTO> getReceptList() throws IDALException.DALException;
    void createRecept(ReceptDTO recept) throws IDALException.DALException;
    void updateRecept(ReceptDTO recept) throws IDALException.DALException;

}
