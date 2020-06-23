package Funktionalitet;


import dal.RaavarebatchDAOSQL;
import dal.dto.RaavarebatchDTO;

import java.sql.SQLException;
import java.util.List;

// -Mikkel
public class RaavarebatchFunc {

    private final RaavarebatchDAOSQL dao = new RaavarebatchDAOSQL();

    /**
     * Kontrollerer om alle kravene opfyldt og sender en fejl besked hvis ikke.
     * @param dto Raavarebatch data transfer objekt
     * @return Fejl besked hvis der opstår fejl
     * @throws SQLException
     */
    public String raavarebatchOk(RaavarebatchDTO dto) throws SQLException{

        if(!startMaengdeOk(dto))
            return "ERROR: Startmængde skal være et tal større end nul";

        if(!batchIdOk(dto))
            return "ERROR: Batch ID findes allerede";

        return "OK";
    }

    /**
     * Valider startmængde
     * @param dto Raavarebatch data transfer objekt
     * @return true hvis kravet opfyldt
     */
    public boolean startMaengdeOk(RaavarebatchDTO dto){

        double subject = dto.getStartMaengde();

        return !(subject < 0);
    }

    /**
     * Valider batch ID
     * @param dto  Raavarebatch data transfer objekt
     * @return true hvis kravet er opfyldt
     * @throws SQLException
     */
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
