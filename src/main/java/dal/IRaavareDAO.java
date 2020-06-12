package dal;

import dal.dto.RaavareDTO;

import java.util.List;

public interface IRaavareDAO {

    RaavareDTO getRV(int raavareId) throws IDALException.DALException;
    List<RaavareDTO> getRVList() throws IDALException.DALException;
    void opretRV(RaavareDTO raavare) throws IDALException.DALException;
    void opdaterRV(RaavareDTO raavare) throws IDALException.DALException;


}
