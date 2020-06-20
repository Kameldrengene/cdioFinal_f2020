package Data;

import Data.dto.RaavarebatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface IRaavarebatchDAO<getAktuelRaavarebatchList> {

    public List<RaavarebatchDTO> getRaavarebatchList() throws SQLException;
    public List<RaavarebatchDTO> getAktuelRaavarebatchList() throws SQLException;
    public RaavarebatchDTO getRaavarebatch(int rbId) throws SQLException;
    public List<RaavarebatchDTO> getRVIDBatch(int RVID) throws SQLException;
    public void createRaavarebatch(RaavarebatchDTO raavarebatch) throws SQLException;
    public void updateRaavarebatch(RaavarebatchDTO raavarebatch) throws SQLException;






}
