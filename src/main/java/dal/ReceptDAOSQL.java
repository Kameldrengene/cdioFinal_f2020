package dal;

import dal.dto.ReceptDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            recept.setUserID(rs.getInt("userID"));
            recept.setUserName(rs.getString("userName"));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return recept;
    }

    @Override
    public List<ReceptDTO> getReceptList() throws IDALException.DALException {
        return null;
    }

    @Override
    public void createRecept(ReceptDTO recept) throws IDALException.DALException {

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws IDALException.DALException {

    }
}
