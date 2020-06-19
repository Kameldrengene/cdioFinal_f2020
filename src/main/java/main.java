import controller.ReceptController;
import dal.IDALException;
import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<ReceptDTO> receptDTOList = new ArrayList<>();

        ReceptDTO receptDTO = new ReceptDTO();
        receptDTO.setReceptId(37);
        receptDTO.setReceptNavn("juice");
        receptDTO.setRaavareId(1);
        receptDTO.setNonNetto(0.05);
        receptDTO.setTolerance(0.1);

        ReceptDTO newRecept = new ReceptDTO();
        newRecept.setNonNetto(0.05);
        newRecept.setRaavareId(3);
        newRecept.setReceptId(13);
        newRecept.setReceptNavn("juice");
        newRecept.setTolerance(9.5);

        receptDTOList.add(receptDTO);
        receptDTOList.add(newRecept);
        ReceptDAOSQL receptDAOSQL = new ReceptDAOSQL();
        ReceptController receptController = new ReceptController();

        receptController.opretRecept(receptDTOList);

    }
}
