package dal;

import dal.dto.ProduktbatchDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduktbatchDAOSQL implements IProduktbatchDAO {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public ProduktbatchDTO getPB(int pbId) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + pbId); //Select all columns from recept where receptID is input
        ProduktbatchDTO pb = new ProduktbatchDTO();
        try {
            rs.next();
            pb.setPBID(rs.getInt("PBID"));
            pb.setRCID(rs.getInt("RVID"));
            pb.setStatus(rs.getString("Standing"));
            pb.setUserID(rs.getInt("UserID"));
            pb.setRVBID(rs.getInt("RVBID"));
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
    public List<ProduktbatchDTO> getPBList() throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches"); //Select all data from raavarer
        List<ProduktbatchDTO> pbList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                ProduktbatchDTO pb = new ProduktbatchDTO();
                pb.setPBID(rs.getInt("PBID"));
                pb.setRCID(rs.getInt("RCID"));
                pb.setStatus(rs.getString("Standing"));
                pb.setUserID(rs.getInt("UserID"));
                pb.setRVBID(rs.getInt("RVBID"));
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
    public void opretPB(ProduktbatchDTO pb) throws IDALException.DALException{
        db.connect();
        db.update("insert into ProduktBatches (PBID, RCID, Standing, UserID, RVBID, Tara, Netto) VALUE ('" + pb.getPBID() + "','" + pb.getRCID() + "','" + pb.getStatus() + "','" + pb.getUserID() + "','" + pb.getRVBID() + "','" + pb.getTara() + "','" + pb.getNetto()  + "')");
        db.close();
    }

    @Override
    public void opdaterPB(ProduktbatchDTO pb) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + pb.getPBID());
            rs.next();
            if (rs.getInt("PBID") == pb.getPBID()) {
                db.update("UPDATE ProduktBatches SET RCID = '" + pb.getRCID() + "' WHERE (PBID = '" + pb.getPBID() + "');");
                db.update("UPDATE ProduktBatches SET Standing = '" + pb.getStatus() + "' WHERE (PBID = '" + pb.getPBID() + "');");
                db.update("UPDATE ProduktBatches SET UserID = '" + pb.getUserID() + "' WHERE (PBID = '" + pb.getPBID() + "');");
                db.update("UPDATE ProduktBatches SET RVBID = '" + pb.getRVBID() + "' WHERE (PBID = '" + pb.getPBID() + "');");
                db.update("UPDATE ProduktBatches SET Tara = '" + pb.getTara() + "' WHERE (PBID = '" + pb.getPBID() + "');");
                db.update("UPDATE ProduktBatches SET Netto = '" + pb.getNetto() + "' WHERE (PBID = '" + pb.getPBID() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
