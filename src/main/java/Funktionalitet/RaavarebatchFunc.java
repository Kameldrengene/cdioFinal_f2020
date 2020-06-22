package Funktionalitet;


import dal.RaavarebatchDAOSQL;
import dal.dto.RaavarebatchDTO;

import java.sql.SQLException;
import java.util.List;

// -Mikkel
public class RaavarebatchFunc {

    private final RaavarebatchDAOSQL dao = new RaavarebatchDAOSQL();

    public String raavarebatchOk(RaavarebatchDTO dto) throws SQLException{

        if(!startMaengdeOk(dto))
            return "ERROR: Startmængde skal være et tal større end nul";

        if(!batchIdOk(dto))
            return "ERROR: Batch ID findes allerede";

        return "OK";
    }

    //Valider startmængde
    public boolean startMaengdeOk(RaavarebatchDTO dto){

        double subject = dto.getStartMaengde();

        if(subject < 0)
            return false;

        return true;
    }

    //Valider batch ID
    public boolean batchIdOk(RaavarebatchDTO dto) throws SQLException {

        int subject = dto.getRbId();

        List<RaavarebatchDTO> dtoList = dao.getRaavarebatchList();

        //Check if it already exists
        for (int i = 0; i < dtoList.size(); i++) {
            if( dtoList.get(i).getRbId() == subject)
                return false;
        }

        return true;
    }
}
