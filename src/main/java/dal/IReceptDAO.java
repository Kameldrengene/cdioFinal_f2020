package dal;

import dal.dto.ReceptDTO;

import java.sql.SQLException;
import java.util.List;

public interface IReceptDAO {

    public List<ReceptDTO> getReceptList() throws SQLException;
    public ReceptDTO getReceptKomponent(int receptId, int RaavareID) throws SQLException;
    public List<ReceptDTO> getRecepts(int id) throws SQLException;
    public void createRecept(ReceptDTO recept) throws SQLException;
    public void createReceptList(List<ReceptDTO> recept) throws SQLException;

}
