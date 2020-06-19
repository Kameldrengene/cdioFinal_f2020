package Funktionalitet;

import Data.dto.RaavareDTO;

import java.util.List;

public class RaavareFunc {

    public boolean isNewRaavareOk(RaavareDTO rv, List<RaavareDTO> rvList) {
        return (!IDExists(rv, rvList) && isIDOk(rv) && isNavnOk(rv) && isleverandoerOk(rv));
    }

    public boolean isUpdateRaavareOk(RaavareDTO rv, List<RaavareDTO> rvList) {
        return (IDExists(rv,rvList) && isIDOk(rv) && isNavnOk(rv) && isleverandoerOk(rv));
    }

    private boolean IDExists(RaavareDTO rv, List<RaavareDTO> rvList) {
        for(int i = 0; i < rvList.size(); i++){
            if(rv.getRaavareID() == rvList.get(i).getRaavareID()){
                return true;
            }
        }
        return false;
    }

    private boolean isIDOk(RaavareDTO rv) {
        if (rv.getRaavareID() <= 99999999 && rv.getRaavareID() >= 1) {
            return true;
        }
        return false;
    }

    private boolean isNavnOk(RaavareDTO rv) {
        if (rv.getRaavareNavn().length() < 21 && rv.getRaavareNavn().length() > 1) {
            return true;
        }
        return false;
    }

    private boolean isleverandoerOk(RaavareDTO rv) {
        if (rv.getLeverandoer().length() < 21 && rv.getLeverandoer().length() > 1) {
            return true;
        }
        return false;
    }

}
