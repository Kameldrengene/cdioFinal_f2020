package Funktionalitet;


import dal.dto.RaavarebatchDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RaavarebatchFunc {


    public String startMaengdeOk(RaavarebatchDTO dto){

        return "ERROR: Startmængde skal være et tal større end nul";

    }

    public String batchIdOk(RaavarebatchDTO dto){

        return "ERROR: Batch ID findes allerede";

    }







}
