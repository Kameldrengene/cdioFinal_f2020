package dal;

import dal.dto.RaavareDTO;
import dal.dto.ReceptDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaavareDAOSQL implements IRaavareDAO{
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    @Override
    public RaavareDTO getRaavare(int raavareId) throws IDALException.DALException{
        db.connect();
        ResultSet rs = db.query("SELECT * FROM Recepter where RID=" + raavareId); //Select all columns from recept where receptID is input
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
        ResultSet rs = db.query("SELECT * FROM Raavarer"); //Select all data from raavarer
        List<RaavareDTO> raavareList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                RaavareDTO raavare = new RaavareDTO();
                raavare.setRaavareId(rs.getInt("raavareID"));
                raavare.setRaavareNavn(rs.getString("raavareNavn"));
                raavare.setLeverandoer(rs.getString("leverandoer"));
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
        db.connect();
        db.update("insert into Recepter (raavareID, raavareNavn, leverandoer) VALUE ('" + raavare.getRaavareId() + "','" + raavare.getRaavareNavn() + "','" + raavare.getLeverandoer()  + "')");
        db.close();
    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws IDALException.DALException{
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM Raavarer where raavareID=" + raavare.getRaavareId());
            rs.next();
            if (rs.getInt("raavareID") == raavare.getRaavareId()) {
                db.update("UPDATE Recepter SET raavareNavn = '" + raavare.getRaavareNavn() + "' WHERE (raavareID = '" + raavare.getRaavareId() + "');");
                db.update("UPDATE Recepter SET leverandoer = '" + raavare.getLeverandoer() + "' WHERE (raavareID = '" + raavare.getRaavareId() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
