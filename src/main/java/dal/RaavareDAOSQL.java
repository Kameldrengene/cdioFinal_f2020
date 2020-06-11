package dal;

import dal.dto.RaavareDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaavareDAOSQL implements IRaavareDAO{
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public RaavareDTO getRV(int RVID) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM RaavareLager where RVID=" + RVID); //Select all columns from recept where receptID is input
        RaavareDTO raavare = new RaavareDTO();
        try {
            rs.next();
            raavare.setRVID(rs.getInt("RVID"));
            raavare.setRVNummer(rs.getInt("RVNummer"));
            raavare.setRVNavn(rs.getString("RVNavn"));
            raavare.setLeverandoer(rs.getString("leverandoer"));
            raavare.setLagerBeholdning(rs.getDouble("lagerbeholdning"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return raavare;
    }


    @Override
    public List<RaavareDTO> getRVList() throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM raavareLager"); //Select all data from raavarer
        List<RaavareDTO> rvList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                RaavareDTO rv = new RaavareDTO();
                rv.setRVID(rs.getInt("RVID"));
                rv.setRVNummer(rs.getInt("RVNummer"));
                rv.setRVNavn(rs.getString("RVNavn"));
                rv.setLeverandoer(rs.getString("leverandoer"));
                rv.setLagerBeholdning(rs.getDouble("lagerbeholdning"));
                rvList.add(rv);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return rvList;
    }

    @Override
    public void opretRV(RaavareDTO rv) throws IDALException.DALException{
        List<RaavareDTO> rvList = getRVList();
        db.connect();
        int idIndex = rvList.get(rvList.size()-1).getRVID()+1;
        rv.setRVID(idIndex);
        db.update("insert into Raavarer (RVID, RVNummer, RVNavn, leverandoer) VALUE ('" + rv.getRVID() + "','" + rv.getRVNummer() + "','" + rv.getRVNavn() + "','" + rv.getLeverandoer()  + "')");
        db.close();
    }

    @Override
    public void opdaterRV(RaavareDTO rv) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM raavareLager where RVID=" + rv.getRVID());
            rs.next();
            if (rs.getInt("RVID") == rv.getRVID()) {
                db.update("UPDATE Raavarer SET RVNummer = '" + rv.getRVNummer() + "' WHERE (RVID = '" + rv.getRVID() + "');");
                db.update("UPDATE Raavarer SET RVNavn = '" + rv.getRVNavn() + "' WHERE (RVID = '" + rv.getRVID() + "');");
                db.update("UPDATE Raavarer SET leverandoer = '" + rv.getLeverandoer() + "' WHERE (RVID = '" + rv.getRVID() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
