package Data;

import Data.dto.ProduktbatchDTO;
import Data.dto.ProduktbatchKompDTO;

import java.sql.SQLException;
import java.util.List;

public interface IProduktbatchDAO {

    public List<ProduktbatchDTO> getProduktBatchList() throws SQLException;
    public List<ProduktbatchDTO> getAktuelProduktBatchList() throws SQLException;
    public ProduktbatchDTO getBatchLine(int pbId) throws SQLException;
    public ProduktbatchKompDTO getBatchkomponent(int pbId, int rbID) throws SQLException;
    public List<ProduktbatchKompDTO> getBatchkomponents(int pbId)  throws SQLException;
    public int getMaxPDID() throws SQLException;

    public void createProduktBatch(ProduktbatchDTO produktbatchDTO) throws SQLException;

    public void updateProduktBatch(ProduktbatchDTO Produktbatch) throws SQLException;
    public void updateProduktBatchkomponent(ProduktbatchKompDTO ProduktbatchKomp) throws SQLException;
    public void updateNewpb(ProduktbatchKompDTO ProduktbatchKomp) throws SQLException;

}