package dal;

import dal.dto.ReceptDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAOSQL implements IReceptDAO{
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.


    @Override
    public ReceptDTO getRC(int RCID) throws IDALException.DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM Recepter where RCID=" + RCID); //Select all columns from recept where receptID is input
        ReceptDTO rc = new ReceptDTO();
        try {
            rs.next();
            rc.setRCID(rs.getInt("RCID"));
            rc.setRCNavn(rs.getString("RCName"));
            rc.setRVID(rs.getInt("RVID"));
            rc.setNonNetto(rs.getDouble("nonNetto"));
            rc.setTolerance(rs.getDouble("tolerance"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return rc;
    }

    @Override
    public List<ReceptDTO> getRCList() throws IDALException.DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM Recepter"); //Select all data from userdto
        List<ReceptDTO> rcList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                ReceptDTO rc = new ReceptDTO();
                rc.setRCID(rs.getInt("RCID"));
                rc.setRCNavn(rs.getString("RCName"));
                rc.setRVID(rs.getInt("RVID"));
                rc.setNonNetto(rs.getDouble("nonNetto"));
                rc.setTolerance(rs.getDouble("tolerance"));
                rcList.add(rc);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return rcList;
    }

    @Override
    public void opretRC(ReceptDTO rc) throws IDALException.DALException {
        db.connect();
        db.update("insert into Recepter (RCID, RCName, RVID, nonNetto, Tolerance) VALUE ('" + rc.getRCID() + "','" + rc.getRCNavn() + "','" + rc.getRVID() + "','" + rc.getNonNetto() + "','" + rc.getTolerance() + "')");
        db.close();

    }

    @Override
    public void opdaterRC(ReceptDTO rc) throws IDALException.DALException {
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM Recepter where RCID=" + rc.getRCID());
            rs.next();
            if (rs.getInt("RCID") == rc.getRCID()) {
                db.update("UPDATE Recepter SET RCName = '" + rc.getRCNavn() + "' WHERE (RCID = '" + rc.getRCID() + "');");
                db.update("UPDATE Recepter SET RVID = '" + rc.getRVID() + "' WHERE (RCID = '" + rc.getRCID() + "');");
                db.update("UPDATE Recepter SET nonNetto = '" + rc.getNonNetto() + "' WHERE (RCID = '" + rc.getRCID() + "');");
                db.update("UPDATE Recepter SET Tolerance = '" + rc.getTolerance() + "' WHERE (RCID = '" + rc.getRCID() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
