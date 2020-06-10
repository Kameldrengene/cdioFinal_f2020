package dal;

import dal.dto.ProduktbatchDTO;
import dal.dto.RaavareDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProduktbatchDAOSQL implements IProduktbatchDAO {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public ProduktbatchDTO getProduktBatch(int pbId) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + pbId); //Select all columns from recept where receptID is input
        ProduktbatchDTO pb = new ProduktbatchDTO();
        try {
            rs.next();
            pb.setPbId(rs.getInt("PBID"));
            pb.setReceptId(rs.getInt("RID"));
            pb.setStatus(rs.getString("Standing"));
            pb.setUserId(rs.getInt("UserID"));
            pb.setRbID(rs.getInt("RBID"));
            pb.setTara(rs.getDouble("Tara"));
            pb.setNetto(rs.getDouble("Netto"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return pb;
    }

    @Override
    public List<ProduktbatchDTO> getProduktBatchList() throws IDALException.DALException{

    }

    @Override
    public void createProduktBatch(ProduktbatchDTO produktbatch) throws IDALException.DALException{

    }

    @Override
    public void updateProduktBatch(ProduktbatchDTO produktbatch) throws IDALException.DALException{

    }
}
