package dal;

import dal.dto.PrintDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintDAOSQL implements IPrintDAO{
    public final SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);

    /**
     * Indhenter alle rækker i databasen i forhold til Produktbatch ID
     * @param pbId Produktbatch id
     * @return PrintDTOList: Alle linjer i PrintBatch view fra databasen
     * @throws SQLException
     */
    @Override
    public List<PrintDTO> getPrint(int pbId) throws SQLException {
        db.connect();
        ResultSet rs = db.query("SELECT PBID, RID, raavareID, Standing, UserID, RName, raavareNavn, leverandoer," +
                " nonNetto, Tolerance, RBID, Tara, Netto, Dato ,(SELECT SUM(Tara) FROM ProduktBatches" +
                " WHERE ProduktBatches.PBID="+pbId+") AS sumTara,(SELECT SUM(Netto) FROM ProduktBatches" +
                " WHERE ProduktBatches.PBID="+pbId+") AS sumNetto  FROM printBatch WHERE PBID="+pbId+" order by Dato, raavareID");

        List<PrintDTO> printDTOList = new ArrayList<>();
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
            printDTO.setSumTara(rs.getDouble("sumTara"));
            printDTO.setSumNetto(rs.getDouble("sumNetto"));
            printDTOList.add(printDTO);
        }
        rs.close();
        db.close();

        return printDTOList;
    }

}
