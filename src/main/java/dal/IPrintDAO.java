package dal;

import dal.dto.*;

import java.util.List;

public interface IPrintDAO {
    List<PrintDTO> getPrint(int pbId,int receptID) throws IDALException.DALException;
}
