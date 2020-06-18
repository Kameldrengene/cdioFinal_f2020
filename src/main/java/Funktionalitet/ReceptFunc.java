package Funktionalitet;

import dal.dto.ReceptDTO;

import java.util.List;

public class ReceptFunc {
    public boolean isReceptOk( ReceptDTO recept){
        return (isNameOk(recept) && isNonNettoOk(recept) && isToleranceOk(recept));
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


}
