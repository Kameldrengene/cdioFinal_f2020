package Funktionalitet;

import dal.dto.ReceptDTO;

public class ReceptFunc {
    public boolean isReceptOk(ReceptDTO recept){
        return (isNameOk(recept) && isNonNettoOk(recept) && isToleranceOk(recept));
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
}
