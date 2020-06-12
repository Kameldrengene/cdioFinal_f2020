package Funktionalitet;

import dal.RaavareDAOSQL;
import dal.dto.RaavareDTO;

public class RaavareFunc {
    public boolean isNewRaavareOk(RaavareDTO rv) {
        return (!IDExists(rv) && isIDOk(rv) && isNavnOk(rv) && isleverandoerOk(rv));
    }

    public boolean isUpdateRaavareOk(RaavareDTO rv) {
        return (IDExists(rv) && isIDOk(rv) && isNavnOk(rv) && isleverandoerOk(rv));
    }

    private boolean IDExists(RaavareDTO rv) {
        RaavareDAOSQL dao = new RaavareDAOSQL();
        if (dao.raavareExists(rv.getRaavareID())) {
            return true;
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
