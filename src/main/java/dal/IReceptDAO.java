package dal;

import dal.dto.ReceptDTO;

import java.sql.SQLException;
import java.util.List;

public interface IReceptDAO {
    List<ReceptDTO> getReceptKomponents(int id) throws SQLException;
    ReceptDTO getRecept(int receptId, int raavarID) throws SQLException;
    List<ReceptDTO> getReceptList() throws SQLException;
    void createReceptList(List<ReceptDTO> receptDTOList) throws SQLException;

}
