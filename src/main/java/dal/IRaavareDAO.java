package dal;

import dal.dto.RaavareDTO;

import java.util.List;

public interface IRaavareDAO {

    RaavareDTO getRV(int RVID) throws IDALException.DALException;
    List<RaavareDTO> getRVList() throws IDALException.DALException;
    void opretRV(RaavareDTO rv) throws IDALException.DALException;
    void opdaterRV(RaavareDTO rv) throws IDALException.DALException;


}
