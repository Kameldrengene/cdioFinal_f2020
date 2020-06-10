package dal;

import dal.dto.RaavarebatchDTO;

import java.util.List;

public interface IRaavarebatchDAO {
    RaavarebatchDTO getRaavarebatch(int rbId) throws IDALException.DALException;
    List<RaavarebatchDTO> getRaavarebatchList() throws IDALException.DALException;
    void createRaavarebatch(RaavarebatchDTO raavarebatch) throws IDALException.DALException;
    void updateRaavarebatch(RaavarebatchDTO raavarebatch) throws IDALException.DALException;
}
