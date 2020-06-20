package Data;

import Data.dto.ProduktbatchDTO;
import Data.dto.ProduktbatchKompDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduktbatchDAOSQL implements IProduktbatchDAO {

    //Makes new SQLDatabaseIO object.
    final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);
    public SQLDatabaseIO getdb(){return db;}


    //Get list of all productbatches
    @Override
    public List<ProduktbatchDTO> getProduktBatchList() throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches GROUP BY PBID ORDER BY PBID"); //Select all data from raavarer
        List<ProduktbatchDTO> pbList = new ArrayList<>();

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

        db.close();
        return pbList;
    }

    //Get list of all active productbatches
    @Override
    public List<ProduktbatchDTO> getAktuelProduktBatchList() throws SQLException {

        List<ProduktbatchDTO> oldRBList = getProduktBatchList();
        List<ProduktbatchDTO> newRBList = new ArrayList<>();

        for (int i = 0; i < oldRBList.size(); i++) {
            if(!(oldRBList.get(i).getStatus().equals("Afsluttet")))
                newRBList.add(oldRBList.get(i));
        }

        return newRBList;
    }

    //Get one productbatch
    @Override
    public ProduktbatchDTO getBatchLine(int pbId) throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID = " + pbId + " GROUP BY PBID ORDER BY PBID");
        ProduktbatchDTO pb = new ProduktbatchDTO();
        rs.next();
        pb.setPbId(pbId);
        pb.setReceptId(rs.getInt("RID"));
        pb.setStatus(rs.getString("Standing"));
        pb.setDato(rs.getString("Dato"));
        rs.close();
        db.close();
        return pb;
    }

    //Get one productbatch component
    @Override
    public ProduktbatchKompDTO getBatchkomponent(int pbId, int rbID) throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID = " + pbId + " AND RBID = " + rbID); //Select all columns from recept where receptID is input
        ProduktbatchKompDTO pb = new ProduktbatchKompDTO();
        rs.next();
        setPB(rs, pb, pbId);
        rs.close();
        db.close();
        return pb;
    }

    //Get all productbatch components of one productbatch
    @Override
    public List<ProduktbatchKompDTO> getBatchkomponents(int pbId)  throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID = " + pbId + " ORDER BY PBID"); //Select all columns from recept where receptID is input
        List<ProduktbatchKompDTO> pbList = new ArrayList<>();
        while(rs.next()){
            ProduktbatchKompDTO pb = new ProduktbatchKompDTO();
            setPB(rs, pb, pbId);
            pbList.add(pb);
        }
        rs.close();
        db.close();
        return pbList;
    }

    // -Mikkel
    //Get the heighest productbatch ID-number
    @Override
    public int getMaxPDID() throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT MAX(PBID) AS max FROM ProduktBatches");
        rs.next();
        int out = rs.getInt("max");
        return out;
    }

    private ProduktbatchKompDTO setPB(ResultSet rs, ProduktbatchKompDTO pb, int pbId){
        pb.setPbId(pbId);
        try {
            pb.setStatus(rs.getString("Standing"));
            pb.setUserId(rs.getInt("UserID"));
            pb.setRbID(rs.getInt("RBID"));
            pb.setTara(rs.getDouble("Tara"));
            pb.setNetto(rs.getDouble("Netto"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pb;
    }

    //Create product batch
    @Override
    public void createProduktBatch(ProduktbatchDTO produktbatchDTO) throws SQLException{
        db.connect();
        db.update("insert into ProduktBatches (PBID, RID, Standing) VALUES ('" + produktbatchDTO.getPbId() + "','" + produktbatchDTO.getReceptId() + "','" + produktbatchDTO.getStatus() + "')");
        db.close();
    }

    //Update a whole product batch
    @Override
    public void updateProduktBatch(ProduktbatchDTO Produktbatch) throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + Produktbatch.getPbId());
        rs.next();
        if (rs.getInt("PBID") == Produktbatch.getPbId()) {
            db.update("UPDATE ProduktBatches SET Standing = '" + Produktbatch.getStatus() + "' WHERE PBID = " + Produktbatch.getPbId() );
            db.update("UPDATE ProduktBatches SET RID = '" + Produktbatch.getReceptId() + "' WHERE PBID = " + Produktbatch.getPbId());
            db.update("UPDATE ProduktBatches SET Dato = '" + Produktbatch.getDato() + "' WHERE PBID = " + Produktbatch.getPbId());
        }
        rs.close();
        db.close();
    }

    //Update one productbatch component
    @Override
    public void updateProduktBatchkomponent(ProduktbatchKompDTO ProduktbatchKomp) throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + ProduktbatchKomp.getPbId() + " AND RBID = " + ProduktbatchKomp.getRbID());
        rs.next();
        if (rs.getInt("PBID") == ProduktbatchKomp.getPbId()) {

            db.update("UPDATE ProduktBatches SET Standing = '" + ProduktbatchKomp.getStatus() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = '" + ProduktbatchKomp.getRbID() + "');");
            db.update("UPDATE ProduktBatches SET UserID = '" + ProduktbatchKomp.getUserId() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = '" + ProduktbatchKomp.getRbID() + "');");
            db.update("UPDATE ProduktBatches SET Tara = '" + ProduktbatchKomp.getTara() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = '" + ProduktbatchKomp.getRbID() + "');");
            db.update("UPDATE ProduktBatches SET Netto = '" + ProduktbatchKomp.getNetto() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = '" + ProduktbatchKomp.getRbID() + "');");
        }
        rs.close();
        db.close();
    }

    //todo comment
    @Override
    public void updateNewpb(ProduktbatchKompDTO ProduktbatchKomp) throws SQLException{
        db.connect();
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
        db.close();
    }

    //todo slet - også i rapport og test
    public void eraseProduktBatch(int pbId, int RBID) throws SQLException{
        db.connect();
        db.update("DELETE FROM ProduktBatches WHERE PBID = " + pbId + " AND RBID = " + RBID);
        db.close();
    }
}
