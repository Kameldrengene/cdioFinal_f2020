package dal;

import dal.dto.ProduktbatchDTO;
import dal.dto.ProduktbatchKompDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduktbatchDAOSQL implements IProduktbatchDAO {

    //Makes new SQLDatabaseIO object.
    public final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);


    public SQLDatabaseIO getdb(){return db;}


    /**
     * Get list of all productbatches
     * @return Get list of all productbatches
     * @throws SQLException
     */
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

    /**
     * Get list of all active productbatches
     * @return Get list of all active productbatches
     * @throws SQLException
     */
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

    /**
     * Get one productbatch
     * @param pbId Produktbatch ID
     * @return Get one productbatch
     * @throws SQLException
     */
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

    /**
     * Get one productbatch component
     * @param pbId Produktbatch ID
     * @param rbID Recept ID
     * @return Get one productbatch component
     * @throws SQLException
     */
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

    /**
     * Get all productbatch components of one productbatch
     * @param pbId Produktbatch ID
     * @return Get all productbatch components of one productbatch
     * @throws SQLException
     */
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

    /**
     * Get the heighest productbatch ID-number
     * @return Get the heighest productbatch ID-number
     * @throws SQLException
     */
    @Override
    public int getMaxPDID() throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT MAX(PBID) AS max FROM ProduktBatches");
        rs.next();
        return rs.getInt("max");
    }

    /**
     *
     * @param produktbatchDTO Create product batch
     * @throws SQLException
     */
    @Override
    public void createProduktBatch(ProduktbatchDTO produktbatchDTO) throws SQLException{
        db.connect();
        db.update("insert into ProduktBatches (PBID, RID, Standing) VALUES ('" + produktbatchDTO.getPbId() + "','" + produktbatchDTO.getReceptId() + "','" + produktbatchDTO.getStatus() + "')");
        db.close();
    }

    /**
     * Update a whole product batch
     * @param Produktbatch Produktbatch Data transfer objekt
     * @throws SQLException
     */
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

    /**
     * Update one productbatch component
     * @param ProduktbatchKomp Produktbatchkomp Data transfer objekt
     * @throws SQLException
     */
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

    /**
     * Opdater ny ProduktBatchKomp
     * @param ProduktbatchKomp Produktbatchkomp Data transfer objekt
     * @throws SQLException
     */
    @Override
    public void updateNewpb(ProduktbatchKompDTO ProduktbatchKomp) throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM ProduktBatches where PBID=" + ProduktbatchKomp.getPbId() + " AND RBID = 0");
        rs.next();
        if (rs.getInt("PBID") == ProduktbatchKomp.getPbId()) {

            db.update("UPDATE ProduktBatches SET Standing = '" + ProduktbatchKomp.getStatus() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
            db.update("UPDATE ProduktBatches SET UserID = '" + ProduktbatchKomp.getUserId() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
            db.update("UPDATE ProduktBatches SET Tara = '" + ProduktbatchKomp.getTara() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
            db.update("UPDATE ProduktBatches SET Netto = '" + ProduktbatchKomp.getNetto() + "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
            db.update("UPDATE ProduktBatches SET RBID = '" + ProduktbatchKomp.getRbID()+ "' WHERE (PBID = '" + ProduktbatchKomp.getPbId() + "' AND RBID = 0);");
        }
        rs.close();
        db.close();
    }

    /**
     * Set produktBatch
     * @param rs Rækker fra databasen
     * @param pb Produktbatch Data transfer objekt
     * @param pbId Produktbatch ID
     */
    private void setPB(ResultSet rs, ProduktbatchKompDTO pb, int pbId){
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
    }

}
