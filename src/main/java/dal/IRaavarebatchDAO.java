package dal;

import dal.dto.RaavarebatchDTO;

import java.util.List;

public interface IRaavarebatchDAO<getAktuelRaavarebatchList> {
    RaavarebatchDTO getRBV(int rbId) throws IDALException.DALException;
    List<RaavarebatchDTO> getRVBList() throws IDALException.DALException;
    List<RaavarebatchDTO> getAktuelRVBList() throws IDALException.DALException;
    void opretRVB(RaavarebatchDTO raavarebatch) throws IDALException.DALException;
    void opdaterRVB(RaavarebatchDTO raavarebatch) throws IDALException.DALException;




}
