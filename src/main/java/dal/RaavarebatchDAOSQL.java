package dal;

import dal.dto.RaavarebatchDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaavarebatchDAOSQL implements IRaavarebatchDAO {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public RaavarebatchDTO getRBV(int RVBID) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM RaavareBatches where RVBID=" + RVBID); //Select all columns from recept where receptID is input
        RaavarebatchDTO rb = new RaavarebatchDTO();
        try {
            rs.next();
            rb.setRVBID(rs.getInt("RVBID"));
            rb.setRVBNummer(rs.getInt("RVBNummer"));
            rb.setRVID(rs.getInt("RVID"));
            rb.setMaengde(rs.getDouble("maengde"));
            rb.setAktuelMaengde(rs.getDouble("aktuelMaengde"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(rs);

        db.close();
        return rb;
    }

    @Override
    public List<RaavarebatchDTO> getRVBList() throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM RaavareBatches"); //Select all data from raavarer
        List<RaavarebatchDTO> rvbList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                RaavarebatchDTO rb = new RaavarebatchDTO();
                rb.setRVBID(rs.getInt("RVBID"));
                rb.setRVBNummer(rs.getInt("RVBNummer"));
                rb.setRVID(rs.getInt("RVID"));
                rb.setMaengde(rs.getDouble("maengde"));
                rb.setAktuelMaengde(rs.getDouble("aktuelMaengde"));
                rvbList.add(rb);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return rvbList;
    }

    @Override
    public List<RaavarebatchDTO> getAktuelRVBList() throws IDALException.DALException{

        List<RaavarebatchDTO> oldRVBList = getRVBList();
        List<RaavarebatchDTO> newRVBList = new ArrayList<>();


        for (int i = 0; i < oldRVBList.size(); i++) {
            if(oldRVBList.get(i).getAktuelMaengde() > 0)
                newRVBList.add(oldRVBList.get(i));
        }

        return newRVBList;
    }

    @Override
    public void opretRVB(RaavarebatchDTO rb) throws IDALException.DALException{
        db.connect();
        db.update("insert into RaavareBatches (RVBID, RVBNummer, RVID, maengde, aktuelMaengde) VALUE ('" + rb.getRVBID() + "','" + rb.getRVBNummer() + "','" + rb.getRVID() + "','" + rb.getMaengde() + "','" + rb.getAktuelMaengde()  + "')");
        db.close();
    }

    @Override
    public void opdaterRVB(RaavarebatchDTO rb) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM RaavareBatches where rBID=" + rb.getRVBID());
            rs.next();
            if (rs.getInt("rBID") == rb.getRVBID()) {
                db.update("UPDATE RaavareBatches SET RVBNummer = '" + rb.getRVBNummer() + "' WHERE (RVBID = '" + rb.getRVBID() + "');");
                db.update("UPDATE RaavareBatches SET RVID = '" + rb.getRVID() + "' WHERE (RVBID = '" + rb.getRVBID() + "');");
                db.update("UPDATE RaavareBatches SET maengde = '" + rb.getMaengde() + "' WHERE (RVBID = '" + rb.getRVBID() + "');");
                db.update("UPDATE RaavareBatches SET aktuelMaengde = '" + rb.getAktuelMaengde() + "' WHERE (RVBID = '" + rb.getRVBID() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

}
