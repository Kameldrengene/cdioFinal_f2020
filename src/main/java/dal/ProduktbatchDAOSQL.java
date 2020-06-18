package dal;

import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;
import dal.dto.RaavareDTO;

import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduktbatchDAOSQL /*implements IProduktbatchDAO*/ {

    //Makes new SQLDatabaseIO object.
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
    public SQLDatabaseIO getdb(){return db;}

//    @Override
public ProduktbatchKompDTO getBatchkomponent(int pbId, int rbID) throws IDALException.DALException{
    db.connect();
    ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID = " + pbId + " AND RBID = " + rbID); //Select all columns from recept where receptID is input
    ProduktbatchKompDTO pb = new ProduktbatchKompDTO();
    try {
        rs.next();
        pb.setPbId(pbId);
        pb.setStatus(rs.getString("Standing"));
        pb.setUserId(rs.getInt("UserID"));
        pb.setRbID(rs.getInt("RBID"));
        pb.setTara(rs.getDouble("Tara"));
        pb.setNetto(rs.getDouble("Netto"));
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
        throw db.buildError(Response.Status.NOT_ACCEPTABLE, "error getting from db: " + "SELECT * FROM ProduktBatches where PBID = " + pbId + " AND RBID = " + rbID);
    }
    db.close();
    return pb;
}

    public List<ProduktbatchKompDTO> getBatchkomponents(int pbId) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID = " + pbId + " ORDER BY PBID"); //Select all columns from recept where receptID is input
        List<ProduktbatchKompDTO> pbList = new ArrayList<>();
        try {
            while(rs.next()){
                ProduktbatchKompDTO pb = new ProduktbatchKompDTO();
                pb.setPbId(pbId);
                pb.setStatus(rs.getString("Stading"));
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
    public ProduktbatchDTO getBatchLine(int pbId) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID = " + pbId + " GROUP BY PBID ORDER BY PBID");
        ProduktbatchDTO pb = new ProduktbatchDTO();
        try {
            rs.next();
            pb.setPbId(pbId);
            pb.setReceptId(rs.getInt("RID"));
            pb.setStatus(rs.getString("Standing"));
            pb.setDato(rs.getString("Dato"));
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
        ResultSet rs = db.query("SELECT * FROM ProduktBatches GROUP BY PBID ORDER BY PBID"); //Select all data from raavarer
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
public void updateNewpb(ProduktbatchKompDTO ProduktbatchKomp) throws IDALException.DALException{
    db.connect();
    try {
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + ProduktbatchKomp.getPbId() + " AND RBID = 0");
        rs.next();
        if (rs.getInt("PBID") == ProduktbatchKomp.getPbId()) {

            db.update("UPDATE ProduktBatches SET Standing = '" + ProduktbatchKomp.getStatus() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
            db.update("UPDATE ProduktBatches SET RBID = '" + ProduktbatchKomp.getRbID()+ "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
            db.update("UPDATE ProduktBatches SET UserID = '" + ProduktbatchKomp.getUserId() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
            db.update("UPDATE ProduktBatches SET Tara = '" + ProduktbatchKomp.getTara() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
            db.update("UPDATE ProduktBatches SET Netto = '" + ProduktbatchKomp.getNetto() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
        }
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
        throw db.buildError(Response.Status.NOT_ACCEPTABLE, "Error sending it to db");
    }
    db.close();
}

    public void updateProduktBatch(ProduktbatchDTO Produktbatch) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + Produktbatch.getPbId());
            rs.next();
            if (rs.getInt("PBID") == Produktbatch.getPbId()) {
                db.update("UPDATE ProduktBatches SET Standing = '" + Produktbatch.getStatus() + "' WHERE PBID = " + Produktbatch.getPbId() );
                db.update("UPDATE ProduktBatches SET RID = '" + Produktbatch.getReceptId() + "' WHERE PBID = " + Produktbatch.getPbId());
                db.update("UPDATE ProduktBatches SET Dato = '" + Produktbatch.getDato() + "' WHERE PBID = " + Produktbatch.getPbId());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw db.buildError(Response.Status.NOT_ACCEPTABLE, "Error updating: wrong input");
        }
        db.close();
    }

    public void updateProduktBatchLine(ProduktbatchKompDTO ProduktbatchKomp) throws IDALException.DALException{
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

    public void updateProduktBatchLine(ProduktbatchDTO produktbatchDTO) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + produktbatchDTO.getPbId());
            rs.next();
            if (rs.getInt("PBID") == produktbatchDTO.getPbId()) {

                db.update("UPDATE ProduktBatches SET Standing = '" + produktbatchDTO.getStatus() + "' WHERE (PBID = '" + produktbatchDTO.getPbId() + "');");
                db.update("UPDATE ProduktBatches SET RID = '" + produktbatchDTO.getReceptId() + "' WHERE (PBID = '" + produktbatchDTO.getPbId() + "');");
                db.update("UPDATE ProduktBatches SET Dato = '" + produktbatchDTO.getDato() + "' WHERE (PBID = '" + produktbatchDTO.getPbId() + "');");
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
