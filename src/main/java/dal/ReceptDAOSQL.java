package dal;

import dal.dto.ReceptDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAOSQL implements IReceptDAO{
    public final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.

    //henter den enkelt recept med en recept komponent.
    @Override
    public ReceptDTO getRecept(int receptId, int RaavareID) throws SQLException {
        db.connect();
        ResultSet rs = db.query("select * from Recepter where RID = "+receptId +" and raavareID = "+RaavareID+""); //Select all columns from recept where receptID is input
        ReceptDTO recept = new ReceptDTO();
        rs.next();
        recept.setReceptId(rs.getInt("RID"));
        recept.setReceptNavn(rs.getString("RName"));
        recept.setRaavareId(rs.getInt("raavareID"));
        recept.setNonNetto(rs.getDouble("nonNetto"));
        recept.setTolerance(rs.getDouble("Tolerance"));
        rs.close();
        db.close();
        return recept;
    }

    //henter alle recept komponenter af en recept fra SQLdatabasen
    @Override
    public List<ReceptDTO> getReceptKomponents(int id) throws SQLException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM receptRaavare where RID=" + id); //Select all data from userdto
        List<ReceptDTO> receptList = new ArrayList<>();
        //We do as in getUser, except we make new user until rs is empty
        while (rs.next()) {
            ReceptDTO recept = new ReceptDTO();
            recept.setReceptId(rs.getInt("RID"));
            recept.setReceptNavn(rs.getString("RName"));
            recept.setRaavareId(rs.getInt("raavareID"));
            recept.setRaavarNavn(rs.getString("raavareNavn"));
            recept.setNonNetto(rs.getDouble("nonNetto"));
            recept.setTolerance(rs.getDouble("Tolerance"));
            receptList.add(recept);
        }
        rs.close();
        db.close();
        return receptList;
    }


    //henter alle recepter fra SQL databasen
    @Override
    public List<ReceptDTO> getReceptList() throws SQLException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM visRecepter"); //Select all data from a view called 'visRecepter'
        List<ReceptDTO> receptList = new ArrayList<>();
        //We do as in getUser, except we make new user until rs is empty
        while (rs.next()) {
            ReceptDTO recept = new ReceptDTO();
            recept.setReceptId(rs.getInt("RID"));
            recept.setReceptNavn(rs.getString("RName"));
            receptList.add(recept);
        }
        rs.close();
        db.close();
        return receptList;
    }

    //opretter en recept med alle af sine komponeneter
    public void createReceptList(List<ReceptDTO> recept) throws SQLException {
        for (int i = 0; i < recept.size(); i++) {
            if(!getReceptList().contains(recept.get(i))){
                db.connect();
                db.update("insert into Recepter (RID, RName, raavareID, nonNetto, Tolerance) VALUE ('" + recept.get(i).getReceptId() + "','" + recept.get(i).getReceptNavn() + "','" + recept.get(i).getRaavareId() + "','" + recept.get(i).getNonNetto() + "','" + recept.get(i).getTolerance() + "')");
                db.close();
            }
        }

    }

}
