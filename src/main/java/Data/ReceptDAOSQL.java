package Data;

import Data.dto.ReceptDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAOSQL implements IReceptDAO{
    public final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.


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

    public List<ReceptDTO> getReceptkomponents(int receptId) throws SQLException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM Recepter where RID=" + receptId); //Select all columns from recept where receptID is input
        List<ReceptDTO> receptList = new ArrayList<>();
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
        db.close();
        return receptList;
    }

    @Override
    public List<ReceptDTO> getRecepts(int id) throws SQLException {
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

    @Override
    public void createRecept(ReceptDTO recept) throws SQLException {
        //System.out.println(recept.getReceptId());
        //System.out.println(getRecept(recept.getReceptId()));
            if(!getReceptList().contains(recept)){
                db.connect();
                db.update("insert into Recepter (RID, RName, raavareID, nonNetto, Tolerance) VALUE ('" + recept.getReceptId() + "','" + recept.getReceptNavn() + "','" + recept.getRaavareId() + "','" + recept.getNonNetto() + "','" + recept.getTolerance() + "')");
                db.close();
            }
    }

    @Override
    public void updateRecept(ReceptDTO recept) throws SQLException {
        db.connect();
            ResultSet rs = db.query("select * from Recepter where RID = "+recept.getReceptId() +" and raavareID = "+recept.getRaavareId()+"");
            rs.next();
            if (rs.getInt("RID") == recept.getReceptId() && rs.getInt("raavareID")  == recept.getRaavareId()) {
                db.update("UPDATE Recepter SET nonNetto = '" + recept.getNonNetto() + "' WHERE (RID = '" + recept.getReceptId() + "' and raavareID= '" + recept.getRaavareId() + "');");
                db.update("UPDATE Recepter SET Tolerance = '" + recept.getTolerance() + "' WHERE (RID = '" + recept.getReceptId() + "' and raavareID= '" + recept.getRaavareId() + "');");
            }
            rs.close();
        db.close();
    }


    public void createReceptList(List<ReceptDTO> recept) throws SQLException {
        //System.out.println(recept.getReceptId());
        //System.out.println(getRecept(recept.getReceptId()));
        for (int i = 0; i < recept.size(); i++) {
            if(!getReceptList().contains(recept.get(i))){
                db.connect();
                db.update("insert into Recepter (RID, RName, raavareID, nonNetto, Tolerance) VALUE ('" + recept.get(i).getReceptId() + "','" + recept.get(i).getReceptNavn() + "','" + recept.get(i).getRaavareId() + "','" + recept.get(i).getNonNetto() + "','" + recept.get(i).getTolerance() + "')");
                db.close();
            }
        }

    }

}