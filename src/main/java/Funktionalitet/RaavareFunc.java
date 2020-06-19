package Funktionalitet;

import dal.dto.RaavareDTO;

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
        return rv.getRaavareID() <= 99999999 && rv.getRaavareID() >= 1;
    }

    private boolean isNavnOk(RaavareDTO rv) {
        return rv.getRaavareNavn().length() < 21 && rv.getRaavareNavn().length() > 1;
    }

    private boolean isleverandoerOk(RaavareDTO rv) {
        return rv.getLeverandoer().length() < 21 && rv.getLeverandoer().length() > 1;
    }

}
