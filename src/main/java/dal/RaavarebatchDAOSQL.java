package dal;

import dal.dto.RaavarebatchDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaavarebatchDAOSQL implements IRaavarebatchDAO {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public RaavarebatchDTO getRaavarebatch(int rbId) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM RaavareBatches where rBID=" + rbId); //Select all columns from recept where receptID is input
        RaavarebatchDTO rb = new RaavarebatchDTO();
        try {
            rs.next();
            rb.setRbId(rs.getInt("rBID"));
            rb.setRaavareId(rs.getInt("raavareID"));
            rb.setStartMaengde(rs.getDouble("maengde"));
            rb.setAktuelMaengde(rs.getDouble("aktuelMaengde"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return rb;
    }

    @Override
    public List<RaavarebatchDTO> getRaavarebatchList() throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM RaavareBatches"); //Select all data from raavarer
        List<RaavarebatchDTO> RBList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                RaavarebatchDTO rb = new RaavarebatchDTO();
                rb.setRbId(rs.getInt("rBID"));
                rb.setRaavareId(rs.getInt("raavareID"));
                rb.setStartMaengde(rs.getDouble("maengde"));
                rb.setAktuelMaengde(rs.getDouble("aktuelMaengde"));
                RBList.add(rb);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return RBList;
    }

    @Override
    public List<RaavarebatchDTO> getAktuelRaavarebatchList() throws IDALException.DALException{

        List<RaavarebatchDTO> oldRBList = getRaavarebatchList();
        List<RaavarebatchDTO> newRBList = new ArrayList<>();

        for (int i = 0; i < oldRBList.size(); i++) {
            if(oldRBList.get(i).getAktuelMaengde() > 0)
                newRBList.add(oldRBList.get(i));
        }

        return newRBList;
    }

    @Override
    public void createRaavarebatch(RaavarebatchDTO raavarebatch) throws IDALException.DALException{
        db.connect();
        db.update("insert into RaavareBatches (rBID, raavareID, maengde, aktuelMaengde) VALUE ('" + raavarebatch.getRbId() + "','" + raavarebatch.getRaavareId() + "','" + raavarebatch.getStartMaengde() + "','" + raavarebatch.getAktuelMaengde()  + "')");
        db.close();
    }

    @Override
    public void updateRaavarebatch(RaavarebatchDTO raavarebatch) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM RaavareBatches where rBID=" + raavarebatch.getRbId());
            rs.next();
            if (rs.getInt("rBID") == raavarebatch.getRbId()) {
                db.update("UPDATE RaavareBatches SET raavareID = '" + raavarebatch.getRaavareId() + "' WHERE (rBID = '" + raavarebatch.getRbId() + "');");
                db.update("UPDATE RaavareBatches SET maengde = '" + raavarebatch.getStartMaengde() + "' WHERE (rBID = '" + raavarebatch.getRbId() + "');");
                db.update("UPDATE RaavareBatches SET aktuelMaengde = '" + raavarebatch.getAktuelMaengde() + "' WHERE (rBID = '" + raavarebatch.getRbId() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

}
