package dal;

import dal.dto.ReceptDTO;
import dal.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAOSQL implements IReceptDAO{
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.


    @Override
    public ReceptDTO getRecept(int receptId) throws IDALException.DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM Recepter where RID=" + receptId); //Select all columns from recept where receptID is input
        ReceptDTO recept = new ReceptDTO();
        try {
            rs.next();
            recept.setReceptId(rs.getInt("RID"));
            recept.setReceptNavn(rs.getString("RName"));
            recept.setRaavareId(rs.getInt("raavareID"));
            recept.setNonNetto(rs.getDouble("nonNetto"));
            recept.setTolerance(rs.getDouble("Tolerance"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return recept;
    }

    @Override
    public List<ReceptDTO> getReceptList() throws IDALException.DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM Recepter"); //Select all data from userdto
        List<ReceptDTO> receptList = new ArrayList<>();
        try {
            //We do as in getUser, except we make new user until rs is empty
            while (rs.next()) {
                ReceptDTO recept = new ReceptDTO();
                recept.setReceptId(rs.getInt("RID"));
                recept.setReceptNavn(rs.getString("RName"));
                recept.setRaavareId(rs.getInt("raavareID"));
                recept.setNonNetto(rs.getDouble("nonNetto"));
                recept.setTolerance(rs.getDouble("Tolerance"));
                receptList.add(recept);
            }
            rs.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return receptList;
    }

    @Override
    public void createRecept(ReceptDTO recept) throws IDALException.DALException {
        db.connect();
        db.update("insert into Recepter (RID, RName, raavareID, nonNetto, Tolerance) VALUE ('" + recept.getReceptId() + "','" + recept.getReceptNavn() + "','" + recept.getRaavareId() + "','" + recept.getNonNetto() + "','" + recept.getTolerance() + "')");
        db.close();

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws IDALException.DALException {
        db.connect();
        try {
            ResultSet rs = db.query("SELECT * FROM Recepter where RID=" + recept.getReceptId());
            rs.next();
            if (rs.getInt("RID") == recept.getReceptId()) {
                db.update("UPDATE Recepter SET RID = '" + recept.getReceptId() + "' WHERE (RID = '" + recept.getReceptId() + "');");
                db.update("UPDATE Recepter SET RName = '" + recept.getReceptNavn() + "' WHERE (RID = '" + recept.getReceptId() + "');");
                db.update("UPDATE Recepter SET raavareID = '" + recept.getRaavareId() + "' WHERE (RID = '" + recept.getReceptId() + "');");
                db.update("UPDATE Recepter SET nonNetto = '" + recept.getNonNetto() + "' WHERE (RID = '" + recept.getReceptId() + "');");
                db.update("UPDATE Recepter SET Tolerance = '" + recept.getTolerance() + "' WHERE (RID = '" + recept.getReceptId() + "');");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}