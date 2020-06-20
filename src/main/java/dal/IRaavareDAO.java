package dal;

import dal.dto.RaavareDTO;

import java.sql.SQLException;
import java.util.List;

public interface IRaavareDAO {

    public List<RaavareDTO> getRaavareList() throws SQLException;
    public RaavareDTO getRaavare(int raavareId) throws SQLException;
    public void createRaavare(RaavareDTO raavare) throws SQLException;
    public void updateRaavare(RaavareDTO raavare) throws SQLException;
    public boolean raavareExists(int raavareID) throws SQLException;



}
