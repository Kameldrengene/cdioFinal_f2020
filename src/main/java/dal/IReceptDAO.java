package dal;

import dal.dto.ReceptDTO;

import java.util.List;

public interface IReceptDAO {
    public List<ReceptDTO> getRecepts(int id) throws IDALException.DALException;
    ReceptDTO getRecept(int receptId) throws IDALException.DALException;
    List<ReceptDTO> getReceptList() throws IDALException.DALException;
    void createRecept(ReceptDTO recept) throws IDALException.DALException;
    void updateRecept(ReceptDTO recept) throws IDALException.DALException;

}
