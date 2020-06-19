package Data;

import Data.dto.RaavareDTO;

import java.sql.SQLException;
import java.util.List;

public interface IRaavareDAO {

    RaavareDTO getRaavare(int raavareId) throws IDALException.DALException, SQLException;
    List<RaavareDTO> getRaavareList() throws IDALException.DALException, SQLException;
    void createRaavare(RaavareDTO raavare) throws IDALException.DALException, SQLException;
    void updateRaavare(RaavareDTO raavare) throws IDALException.DALException, SQLException;
    boolean raavareExists(int raavareID) throws SQLException;


}
