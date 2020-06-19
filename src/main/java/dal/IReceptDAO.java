package dal;

import dal.dto.ReceptDTO;

import java.sql.SQLException;
import java.util.List;

public interface IReceptDAO {
    List<ReceptDTO> getRecepts(int id) throws IDALException.DALException, SQLException;
    ReceptDTO getRecept(int receptId, int raavarID) throws IDALException.DALException, SQLException;
    List<ReceptDTO> getReceptList() throws IDALException.DALException, SQLException;
    void createRecept(ReceptDTO recept) throws IDALException.DALException, SQLException;
    void updateRecept(ReceptDTO recept) throws IDALException.DALException, SQLException;

}
