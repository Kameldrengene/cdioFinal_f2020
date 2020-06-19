package Funktionalitet;

import dal.dto.RaavareDTO;
import dal.dto.ReceptDTO;

import java.util.List;

public class ReceptFunc {
    public boolean isReceptOk( ReceptDTO recept){
        return (isIDOk(recept) && isNameOk(recept) && isNonNettoOk(recept) && isToleranceOk(recept));
    }
    private boolean isIDOk(ReceptDTO recept) {
        if (recept.getReceptId() <= 99999999 && recept.getReceptId() >= 1) {
            return true;
        }
        return false;
    }
    private boolean isNameOk(ReceptDTO recept){
        return (!(recept.getReceptNavn().length() <= 1 || recept.getReceptNavn().length() > 20));
    }
    private boolean isNonNettoOk(ReceptDTO recept){
        return (!(recept.getNonNetto()<=0.04 || recept.getNonNetto() > 20.0));
    }
    private boolean isToleranceOk(ReceptDTO recept){
        return (!(recept.getTolerance()<=0.09 || recept.getTolerance() > 10.0));
    }
    public boolean doesIdExist(ReceptDTO recept, List<ReceptDTO> receptDTOList){
        for (int i = 0; i < receptDTOList.size() ; i++) {
            if (receptDTOList.get(i).getReceptId()==recept.getReceptId()) {
                return true;
            }
        }
        return false;

    }

    public boolean doesNameExist(ReceptDTO recept, List<ReceptDTO> receptDTOList){
        for (int i = 0; i < receptDTOList.size() ; i++) {
            if (receptDTOList.get(i).getReceptNavn().equals(recept.getReceptNavn())) {
                return true;
            }
        }
        return false;
    }


}
