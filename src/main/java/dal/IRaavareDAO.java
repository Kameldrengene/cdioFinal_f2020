package dal;

import dal.dto.RaavareDTO;

import java.util.List;

public interface IRaavareDAO {

    RaavareDTO getRaavare(int raavareId) throws IDALException.DALException;
    List<RaavareDTO> getRaavareList() throws IDALException.DALException;
    void createRaavare(RaavareDTO raavare) throws IDALException.DALException;
    void updateRaavare(RaavareDTO raavare) throws IDALException.DALException;


}
