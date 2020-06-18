package dal;

import dal.dto.RaavarebatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface IRaavarebatchDAO<getAktuelRaavarebatchList> {
    RaavarebatchDTO getRaavarebatch(int rbId) throws IDALException.DALException;
    List<RaavarebatchDTO> getRaavarebatchList() throws IDALException.DALException;
    List<RaavarebatchDTO> getAktuelRaavarebatchList() throws IDALException.DALException;
    void createRaavarebatch(RaavarebatchDTO raavarebatch) throws SQLException;
    void updateRaavarebatch(RaavarebatchDTO raavarebatch) throws SQLException;




}
