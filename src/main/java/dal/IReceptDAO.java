package dal;

import dal.dto.ReceptDTO;

import java.sql.SQLException;
import java.util.List;

public interface IReceptDAO {
    List<ReceptDTO> getRecepts(int id) throws SQLException;
    ReceptDTO getRecept(int receptId, int raavarID) throws SQLException;
    List<ReceptDTO> getReceptList() throws SQLException;
    void createRecept(ReceptDTO recept) throws SQLException;
    void updateRecept(ReceptDTO recept) throws  SQLException;

}
