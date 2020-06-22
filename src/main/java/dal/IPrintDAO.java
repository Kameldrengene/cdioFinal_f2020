package dal;

import dal.dto.PrintDTO;

import java.sql.SQLException;
import java.util.List;

public interface IPrintDAO {
    List<PrintDTO> getPrint(int pbId) throws SQLException;
}
