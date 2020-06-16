package Funktionalitet;

import dal.dto.ReceptDTO;

import java.util.List;

public class ReceptFunc {
    public boolean isReceptOk(ReceptDTO recept,List<ReceptDTO> receptDTOList){
        return (isNameOk(recept) && isNonNettoOk(recept) && isToleranceOk(recept) && !(doesIdExist(recept,receptDTOList)));
    }
    private boolean isNameOk(ReceptDTO recept){
        return (!(recept.getReceptNavn().length() <= 2 || recept.getReceptNavn().length() > 20));
    }
    private boolean isNonNettoOk(ReceptDTO recept){
        return (!(recept.getNonNetto()<=0.05 || recept.getNonNetto() > 20.0));
    }
    private boolean isToleranceOk(ReceptDTO recept){
        return (!(recept.getTolerance()<=0.1 || recept.getTolerance() > 10.0));
    }
    private boolean doesIdExist(ReceptDTO recept, List<ReceptDTO> receptDTOList){
        boolean doesIdExist = false;
        for (int i = 0; i < receptDTOList.size() ; i++) {
            if (receptDTOList.get(i).getReceptId()==recept.getReceptId() && receptDTOList.get(i).getRaavareId()==recept.getRaavareId())
                doesIdExist = true;
        }
        return doesIdExist;
    }
}
