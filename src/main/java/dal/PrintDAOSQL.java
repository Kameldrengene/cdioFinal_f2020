package dal;

import dal.dto.PrintDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintDAOSQL implements IPrintDAO{
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);

    @Override
    public List<PrintDTO> getPrint(int pbId, int receptID) throws IDALException.DALException {
        db.connect();
        ResultSet rs = db.query("SELECT * FROM printBatch WHERE RID="+receptID+" AND PBID="+pbId);
        List<PrintDTO> printDTOList = new ArrayList<>();
        try {
            while (rs.next()){
                PrintDTO printDTO = new PrintDTO();
                printDTO.setDato(rs.getString("Dato"));
                printDTO.setLeverandoer(rs.getString("leverandoer"));
                printDTO.setNetto(rs.getDouble("Netto"));
                printDTO.setNonNetto(rs.getDouble("nonNetto"));
                printDTO.setPbId(rs.getInt("PBID"));
                printDTO.setRaavareID(rs.getInt("raavareID"));
                printDTO.setRaavareNavn(rs.getString("raavareNavn"));
                printDTO.setRbID(rs.getInt("RBID"));
                printDTO.setReceptId(rs.getInt("RID"));
                printDTO.setReceptNavn(rs.getString("RName"));
                printDTO.setStatus(rs.getString("Standing"));
                printDTO.setTara(rs.getDouble("TARA"));
                printDTO.setTolerance(rs.getDouble("Tolerance"));
                printDTO.setUserId(rs.getInt("UserID"));
                printDTOList.add(printDTO);
            }
            rs.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        db.close();

        return printDTOList;
    }
}
