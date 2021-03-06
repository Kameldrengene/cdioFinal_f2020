package dal;

import dal.dto.RaavareDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RaavareDAOSQL implements IRaavareDAO{

    public final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    /**
     * Get all raavarer
     * @param raavareId Raavare ID
     * @return Get all raavarer
     * @throws SQLException
     */
    @Override
    public RaavareDTO getRaavare(int raavareId) throws SQLException {
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
            db.close();
            return raavare;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Liste over Raavarer
     * @return Liste over Raavarer
     * @throws SQLException
     */
    @Override
    public List<RaavareDTO> getRaavareList() throws SQLException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM raavareLager"); //Select all data from raavarer
        List<RaavareDTO> raavareList = new ArrayList<>();
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
        db.close();
        return raavareList;
    }

    /**
     * Create raavare
     * @param raavare Raavare Data Transfer objekt
     * @throws SQLException
     */
    @Override
    public void createRaavare(RaavareDTO raavare) throws SQLException{
        db.connect();
        db.update("insert into Raavarer (raavareID, raavareNavn, leverandoer) VALUE ('" + raavare.getRaavareID() + "','" + raavare.getRaavareNavn() + "','" + raavare.getLeverandoer()  + "')");
        db.close();
    }

    /**
     * Opdatere Raavare
     * @param raavare Raavare Data Transfer objekt
     * @throws SQLException
     */
    @Override
    public void updateRaavare(RaavareDTO raavare) throws SQLException{
        db.connect();

            ResultSet rs = db.query("SELECT * FROM raavareLager where raavareID=" + raavare.getRaavareID());
            rs.next();
            if (rs.getInt("raavareID") == raavare.getRaavareID()) {
                db.update("UPDATE Raavarer SET raavareNavn = '" + raavare.getRaavareNavn() + "' WHERE (raavareID = '" + raavare.getRaavareID() + "');");
                db.update("UPDATE Raavarer SET leverandoer = '" + raavare.getLeverandoer() + "' WHERE (raavareID = '" + raavare.getRaavareID() + "');");
            }
            rs.close();
        db.close();
    }

    /**
     * Kontrollerer om Raavaren eksisterer
     * @param raavareID Raavare ID
     * @return True hvis raavareren eksisterer
     * @throws SQLException
     */
    public boolean raavareExists(int raavareID) throws SQLException {
        db.connect();
            ResultSet rs = db.query("SELECT count(raavareID) AS fundet FROM Raavarer where raavareID=" + raavareID);
            rs.next();
        return rs.getInt("fundet") == 1;
    }

}
