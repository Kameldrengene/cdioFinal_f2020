package dal;

import dal.dto.RaavarebatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface IRaavarebatchDAO<getAktuelRaavarebatchList> {

    List<RaavarebatchDTO> getRaavarebatchList() throws SQLException;
    List<RaavarebatchDTO> getAktuelRaavarebatchList() throws SQLException;
    void createRaavarebatch(RaavarebatchDTO raavarebatch) throws SQLException;

    //todo slet?
    RaavarebatchDTO getRaavarebatch(int rbId) throws IDALException.DALException;
    void updateRaavarebatch(RaavarebatchDTO raavarebatch) throws SQLException;


}
