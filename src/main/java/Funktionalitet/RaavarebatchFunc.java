package Funktionalitet;


import dal.dto.RaavarebatchDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

// -Mikkel
public class RaavarebatchFunc {


    public String startMaengdeOk(RaavarebatchDTO dto){

        double subject = dto.getStartMaengde();

        if(subject < 0)
            return "ERROR: Startmængde skal være et tal større end nul";

        return "OK";

    }

    public String batchIdOk(RaavarebatchDTO dto){

//        return "ERROR: Batch ID findes allerede";
        return "OK";

    }


}
