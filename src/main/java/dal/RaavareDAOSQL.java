package dal;

import dal.dto.RaavareDTO;
import dal.dto.ReceptDTO;
import dal.dto.UserDTO;

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
            raavare.setRaavareId(rs.getInt("raavareID"));
            raavare.setRaavareNavn(rs.getString("raavareNavn"));
            raavare.setLeverandoer(rs.getString("leverandoer"));
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
                raavare.setRaavareId(rs.getInt("raavareID"));
                raavare.setRaavareNummer(rs.getInt("raavareNummer"));
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
    public void createRaavare(RaavareDTO raavare) throws IDALException.DALException{
        List<RaavareDTO> users = getRaavareList();
        db.connect();
        int idIndex = users.get(users.size()-1).getRaavareId()+1;
        raavare.setRaavareId(idIndex);
        db.update("insert into Raavarer (raavareID, raavareNummer, raavareNavn, leverandoer) VALUE ('" + raavare.getRaavareId() + "','" + raavare.getRaavareNummer() + "','" + raavare.getRaavareNavn() + "','" + raavare.getLeverandoer()  + "')");
        db.close();
    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM raavareLager where raavareID=" + raavare.getRaavareId());
            rs.next();
            if (rs.getInt("raavareID") == raavare.getRaavareId()) {
                db.update("UPDATE Raavarer SET raavareNummer = '" + raavare.getRaavareNavn() + "' WHERE (raavareID = '" + raavare.getRaavareId() + "');");
                db.update("UPDATE Raavarer SET raavareNavn = '" + raavare.getRaavareNavn() + "' WHERE (raavareID = '" + raavare.getRaavareId() + "');");
                db.update("UPDATE Raavarer SET leverandoer = '" + raavare.getLeverandoer() + "' WHERE (raavareID = '" + raavare.getRaavareId() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
