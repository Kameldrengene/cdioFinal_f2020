package dal;

import dal.dto.RaavarebatchDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//todo aktiver interface!
public class RaavarebatchDAOSQL /*implements IRaavarebatchDAO*/ {
    public final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    private RaavarebatchDTO setRb(ResultSet rs, RaavarebatchDTO rb){
        try {
            rb.setRbId(rs.getInt("rBID"));
            rb.setRaavareNavn(rs.getString("raavareNavn"));
            rb.setLeverandoer(rs.getString("leverandoer"));
            rb.setRaavareId(rs.getInt("raavareID"));
            rb.setStartMaengde(rs.getDouble("maengde"));
            rb.setAktuelMaengde(rs.getDouble("aktuelMaengde"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rb;
    }

    // -Mikkel
//    @Override
    public List<RaavarebatchDTO> getRaavarebatchList() throws SQLException{

        db.connect();
        ResultSet rs = db.query("SELECT * FROM raavarebatchview ORDER BY rbId");
        List<RaavarebatchDTO> RBList = new ArrayList<>();

        //We do as in getUser, except we make new user until rs is empty
        while (rs.next()) {
            RaavarebatchDTO rb = new RaavarebatchDTO();
            setRb(rs, rb);
            RBList.add(rb);
        }
        rs.close();

        db.close();
        return RBList;
    }

    public List<RaavarebatchDTO> getRVIDBatch(int RVID) throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM raavarebatchview WHERE raavareID = " + RVID + " AND aktuelMaengde > 0");
        List<RaavarebatchDTO> RVBList = new ArrayList<>();

        //We do as in getUser, except we make new user until rs is empty
        while (rs.next()) {
            RaavarebatchDTO rb = new RaavarebatchDTO();
            setRb(rs, rb);
            RVBList.add(rb);
        }
        rs.close();
        db.close();
        return RVBList;
    }

    // -Mikkel
//    @Override
    public List<RaavarebatchDTO> getAktuelRaavarebatchList() throws SQLException{

        List<RaavarebatchDTO> oldRBList = getRaavarebatchList();
        List<RaavarebatchDTO> newRBList = new ArrayList<>();

        for (int i = 0; i < oldRBList.size(); i++) {
            if(oldRBList.get(i).getAktuelMaengde() > 0){
                newRBList.add(oldRBList.get(i));
                System.out.println(oldRBList.get(i).getAktuelMaengde());
            }
        }

        return newRBList;
    }

    // -Mikkel
//    @Override
    public void createRaavarebatch(RaavarebatchDTO raavarebatch) throws SQLException{
        db.connect();
        db.update("insert into RaavareBatches (rBID, raavareID, maengde, aktuelMaengde) VALUE ('" + raavarebatch.getRbId() + "','" + raavarebatch.getRaavareId() + "','" + raavarebatch.getStartMaengde() + "','" + raavarebatch.getAktuelMaengde()  + "')");
        db.close();
    }

    //todo slet?
//    @Override
    public RaavarebatchDTO getRaavarebatch(int rbId) throws SQLException{

        db.connect();
        ResultSet rs = db.query("SELECT * FROM RaavareBatches where rBID=" + rbId); //Select all columns from recept where receptID is input
        RaavarebatchDTO rb = new RaavarebatchDTO();
        rs.next();
        rb.setRbId(rs.getInt("rBID"));
        rb.setRaavareId(rs.getInt("raavareID"));
        rb.setStartMaengde(rs.getDouble("maengde"));
        rb.setAktuelMaengde(rs.getDouble("aktuelMaengde"));
        rs.close();
        db.close();
        return rb;
    }

    //todo slet?
//    @Override
    public void updateRaavarebatch(RaavarebatchDTO raavarebatch) throws SQLException {

        db.connect();
        ResultSet rs = db.query("SELECT * FROM RaavareBatches where rBID=" + raavarebatch.getRbId());
        rs.next();
        if (rs.getInt("rBID") == raavarebatch.getRbId()) {
            db.update("UPDATE RaavareBatches SET raavareID = '" + raavarebatch.getRaavareId() + "' WHERE (rBID = '" + raavarebatch.getRbId() + "');");
            db.update("UPDATE RaavareBatches SET maengde = '" + raavarebatch.getStartMaengde() + "' WHERE (rBID = '" + raavarebatch.getRbId() + "');");
            db.update("UPDATE RaavareBatches SET aktuelMaengde = '" + raavarebatch.getAktuelMaengde() + "' WHERE (rBID = '" + raavarebatch.getRbId() + "');");
        }
        rs.close();
        db.close();

    }

}
