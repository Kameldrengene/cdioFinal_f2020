package dal;

import dal.dto.ProduktbatchDTO;
import dal.dto.RaavareDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches"); //Select all data from raavarer
        List<ProduktbatchDTO> pbList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                ProduktbatchDTO pb = new ProduktbatchDTO();
                pb.setPbId(rs.getInt("PBID"));
                pb.setReceptId(rs.getInt("RID"));
                pb.setStatus(rs.getString("Standing"));
                pb.setUserId(rs.getInt("UserID"));
                pb.setRbID(rs.getInt("RBID"));
                pb.setTara(rs.getDouble("Tara"));
                pb.setNetto(rs.getDouble("Netto"));
                pbList.add(pb);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return pbList;
    }

    @Override
    public void createProduktBatch(ProduktbatchDTO produktbatch) throws IDALException.DALException{
        db.connect();
        db.update("insert into ProduktBatches (PBID, RID, Standing, UserID, RBID, Tara, Netto) VALUE ('" + produktbatch.getPbId() + "','" + produktbatch.getReceptId() + "','" + produktbatch.getStatus() + "','" + produktbatch.getUserId() + "','" + produktbatch.getRbID() + "','" + produktbatch.getTara() + "','" + produktbatch.getNetto()  + "')");
        db.close();
    }

    @Override
    public void updateProduktBatch(ProduktbatchDTO produktbatch) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + produktbatch.getPbId());
            rs.next();
            if (rs.getInt("PBID") == produktbatch.getPbId()) {
                db.update("UPDATE ProduktBatches SET RID = '" + produktbatch.getReceptId() + "' WHERE (PBID = '" + produktbatch.getPbId() + "');");
                db.update("UPDATE ProduktBatches SET Standing = '" + produktbatch.getStatus() + "' WHERE (PBID = '" + produktbatch.getPbId() + "');");
                db.update("UPDATE ProduktBatches SET UserID = '" + produktbatch.getUserId() + "' WHERE (PBID = '" + produktbatch.getPbId() + "');");
                db.update("UPDATE ProduktBatches SET RBID = '" + produktbatch.getRbID() + "' WHERE (PBID = '" + produktbatch.getPbId() + "');");
                db.update("UPDATE ProduktBatches SET Tara = '" + produktbatch.getTara() + "' WHERE (PBID = '" + produktbatch.getPbId() + "');");
                db.update("UPDATE ProduktBatches SET Netto = '" + produktbatch.getNetto() + "' WHERE (PBID = '" + produktbatch.getPbId() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
