import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;

public class main {
    public static void main(String[] args) {
        ReceptDTO receptDTO = new ReceptDTO();
        receptDTO.setReceptId(10);
        receptDTO.setReceptNavn("Panodil");
        receptDTO.setRaavareId(1);
        receptDTO.setNonNetto(40.0);
        receptDTO.setTolerance(0.5);
        ReceptDAOSQL receptDAOSQL = new ReceptDAOSQL();
        try {
            receptDAOSQL.updateRecept(receptDTO);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }

    }
}
