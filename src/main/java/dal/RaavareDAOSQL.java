package dal;

import dal.dto.RaavareDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaavareDAOSQL implements IRaavareDAO{
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public RaavareDTO getRaavare(int raavareId) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM raavareLager where raavareID=" + raavareId); //Select all columns from recept where receptID is input
        RaavareDTO raavare = new RaavareDTO();
        try {
            rs.next();
            raavare.setRaavareID(rs.getInt("raavareID"));
            raavare.setRaavareNavn(rs.getString("raavareNavn"));
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
    public List<RaavareDTO> getRaavareList() throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM raavareLager"); //Select all data from raavarer
        List<RaavareDTO> raavareList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                RaavareDTO raavare = new RaavareDTO();
                raavare.setRaavareID(rs.getInt("raavareID"));
                raavare.setRaavareNavn(rs.getString("raavareNavn"));
                raavare.setLeverandoer(rs.getString("leverandoer"));
                raavare.setLagerBeholdning(rs.getDouble("lagerbeholdning"));
                raavareList.add(raavare);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return raavareList;
    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws IDALException.DALException{ //TODO take care of exception if ID alredy exists
        db.connect();
        db.update("insert into Raavarer (raavareID, raavareNavn, leverandoer) VALUE ('" + raavare.getRaavareID() + "','" + raavare.getRaavareNavn() + "','" + raavare.getLeverandoer()  + "')");
        db.close();
    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM raavareLager where raavareID=" + raavare.getRaavareID());
            rs.next();
            if (rs.getInt("raavareID") == raavare.getRaavareID()) {
                db.update("UPDATE Raavarer SET raavareNavn = '" + raavare.getRaavareNavn() + "' WHERE (raavareID = '" + raavare.getRaavareID() + "');");
                db.update("UPDATE Raavarer SET leverandoer = '" + raavare.getLeverandoer() + "' WHERE (raavareID = '" + raavare.getRaavareID() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    public boolean raavareExists(int raavareID) {
        db.connect();
        try {
            ResultSet rs = db.query("SELECT count(raavareID) AS fundet FROM Raavarer where raavareID=" + raavareID);
            rs.next();
            if (rs.getInt("fundet") == 1) {return true;}
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void setDB(String dbName){
        db.setDB(dbName);
    }
}
