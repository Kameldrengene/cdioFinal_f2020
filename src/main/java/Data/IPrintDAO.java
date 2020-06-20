package Data;

import Data.dto.*;

import java.sql.SQLException;
import java.util.List;

public interface IPrintDAO {
    List<PrintDTO> getPrint(int pbId,int receptID) throws SQLException;
}
