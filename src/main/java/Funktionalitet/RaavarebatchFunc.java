package Funktionalitet;


import Data.RaavarebatchDAOSQL;
import Data.dto.RaavarebatchDTO;

import java.sql.SQLException;
import java.util.List;

// -Mikkel
public class RaavarebatchFunc {

    private final RaavarebatchDAOSQL dao = new RaavarebatchDAOSQL();

    public String startMaengdeOk(RaavarebatchDTO dto){

        double subject = dto.getStartMaengde();

        if(subject < 0)
            return "ERROR: Startmængde skal være et tal større end nul";

        return "OK";

    }

    public String batchIdOk(RaavarebatchDTO dto) throws SQLException {

        int subject = dto.getRbId();

        List<RaavarebatchDTO> dtoList = dao.getRaavarebatchList();

        for (int i = 0; i < dtoList.size(); i++) {
            if( dtoList.get(i).getRbId() == subject)
                return "ERROR: Batch ID findes allerede";
        }

        return "OK";

    }
}
