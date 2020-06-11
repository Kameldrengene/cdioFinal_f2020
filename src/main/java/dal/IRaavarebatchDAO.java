package dal;

import dal.dto.RaavarebatchDTO;

import java.util.List;

public interface IRaavarebatchDAO<getAktuelRaavarebatchList> {
    RaavarebatchDTO getRBV(int RVBID) throws IDALException.DALException;
    List<RaavarebatchDTO> getRVBList() throws IDALException.DALException;
    List<RaavarebatchDTO> getAktuelRVBList() throws IDALException.DALException;
    void opretRVB(RaavarebatchDTO rvb) throws IDALException.DALException;
    void opdaterRVB(RaavarebatchDTO rvb) throws IDALException.DALException;




}
