package Data;

import Data.dto.RaavarebatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface IRaavarebatchDAO<getAktuelRaavarebatchList> {

    public List<RaavarebatchDTO> getRaavarebatchList() throws SQLException;



}
