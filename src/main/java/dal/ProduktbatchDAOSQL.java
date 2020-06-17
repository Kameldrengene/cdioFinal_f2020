package dal;

import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;
import dal.dto.RaavareDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduktbatchDAOSQL /*implements IProduktbatchDAO*/ {

    //Makes new SQLDatabaseIO object.
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);

//    @Override
    public List<ProduktbatchKompDTO> getProduktBatch(int pbId) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID = " + pbId);
        List<ProduktbatchKompDTO> pbList = new ArrayList<>();
        try {
            while(rs.next()){
                ProduktbatchKompDTO pb = new ProduktbatchKompDTO();
                pb.setUserId(rs.getInt("UserID"));
                pb.setRbID(rs.getInt("RBID"));
                pb.setTara(rs.getDouble("Tara"));
                pb.setNetto(rs.getDouble("Netto"));
                pbList.add(pb);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return pbList;
    }

//    @Override
    public ProduktbatchDTO getProduktBatchLine(int pbId, int RBID) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID = " + pbId + " AND RBID = " + RBID); //Select all columns from recept where receptID is input
        ProduktbatchDTO pb = new ProduktbatchDTO();
        try {
            rs.next();
            pb.setPbId(rs.getInt("PBID"));
            pb.setReceptId(rs.getInt("RID"));
            pb.setStatus(rs.getString("Standing"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return pb;
    }

//    @Override
    public List<ProduktbatchDTO> getProduktBatchList() throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches GROUP BY PBID"); //Select all data from raavarer
        List<ProduktbatchDTO> pbList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                ProduktbatchDTO pb = new ProduktbatchDTO();
                pb.setPbId(rs.getInt("PBID"));
                pb.setReceptId(rs.getInt("RID"));
                pb.setStatus(rs.getString("Standing"));
                pb.setDato((rs.getString("Dato")));
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

//    @Override
    public List<ProduktbatchDTO> getAktuelProduktBatchList() throws IDALException.DALException {

        List<ProduktbatchDTO> oldRBList = getProduktBatchList();
        List<ProduktbatchDTO> newRBList = new ArrayList<>();


        for (int i = 0; i < oldRBList.size(); i++) {
            if(!(oldRBList.get(i).getStatus().equals("Afsluttet")))
                newRBList.add(oldRBList.get(i));
        }

        return newRBList;
    }


//    @Override
    public void createProduktBatch(ProduktbatchDTO produktbatchDTO) throws IDALException.DALException{
        db.connect();
        db.update("insert into ProduktBatches (PBID, RID, Standing) VALUE ('" + produktbatchDTO.getPbId() + "','" + produktbatchDTO.getReceptId() + "','" + produktbatchDTO.getStatus() + "')");
        db.close();
    }

//    @Override
    public void updateProduktBatch(ProduktbatchKompDTO ProduktbatchKomp) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + ProduktbatchKomp.getPbId() + " AND RBID = " + ProduktbatchKomp.getRbID());
            rs.next();
            if (rs.getInt("PBID") == ProduktbatchKomp.getPbId()) {

                db.update("UPDATE ProduktBatches SET Standing = '" + ProduktbatchKomp.getStatus() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = '" + ProduktbatchKomp.getRbID() + "');");
                db.update("UPDATE ProduktBatches SET UserID = '" + ProduktbatchKomp.getUserId() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = '" + ProduktbatchKomp.getRbID() + "');");
                db.update("UPDATE ProduktBatches SET Tara = '" + ProduktbatchKomp.getTara() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = '" + ProduktbatchKomp.getRbID() + "');");
                db.update("UPDATE ProduktBatches SET Netto = '" + ProduktbatchKomp.getNetto() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = '" + ProduktbatchKomp.getRbID() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    //todo slet
    // -Mikkel
//    @Override
    public void eraseProduktBatch(int pbId, int RBID) throws IDALException.DALException{

        db.connect();
        db.update("DELETE FROM ProduktBatches WHERE PBID = " + pbId + " AND RBID = " + RBID);
        db.close();

    }

}
