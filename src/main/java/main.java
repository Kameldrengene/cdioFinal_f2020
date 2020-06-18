import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<ReceptDTO> receptDTOList = new ArrayList<>();
        ReceptDTO receptDTO = new ReceptDTO();
        receptDTO.setReceptId(13);
        receptDTO.setReceptNavn("juice");
        receptDTO.setRaavareId(1);
        receptDTO.setNonNetto(40.0);
        receptDTO.setTolerance(0.5);
        ReceptDAOSQL receptDAOSQL = new ReceptDAOSQL();
        try {
            receptDAOSQL.createRecept(receptDTO);
        }catch (IDALException.DALException e){
            e.printStackTrace();
        }

    }
}
