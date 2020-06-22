package Funktionalitet;

import dal.dto.ReceptDTO;

import java.util.List;

public class ReceptFunc {
    /**
     * Sender beskeder i forhold til de fejl der opstår
     * @param recept Recep data transfer objekt
     * @param receptDTOList Recept listen
     * @return Returnerer besked hvis der er en fejl
     */
    public String receptmsg( ReceptDTO recept,List<ReceptDTO> receptDTOList){
        if (!isIDOk(recept)) {
            return "ID størrelse skal være mellem 1 til 99999999 decimaler";
        }
        if(!isNameOk(recept)){
            return "Recept navnet skal være mellem 2 til 20 tegn ";
        }
        if(!isNonNettoOk(recept)){
            return "mængde skal være mellem  1 til  20 decimaler ";
        }
        if(!isToleranceOk(recept)){
            return "Tolerance skal være mellem 0.09 til 10.0 decimaler ";
        }
        if(doesIdExist(recept,receptDTOList)){
            return "Recept ID er optaget\nVælge en anden";
        }
        if(doesNameExist(recept,receptDTOList)){
            return "Recept navn er optaget\nVælge en anden";
        }
        return null;
    }

    /**
     * KOntrollerer om navnet eksisterer allerede
     * @param recept Recep data transfer objekt
     * @param receptDTOList Recept listen
     * @return true hvis krav opfyldt
     */
    public boolean isReceptOk( ReceptDTO recept,List<ReceptDTO> receptDTOList){
        return (!doesNameExist(recept,receptDTOList)&& !doesIdExist(recept,receptDTOList) && isIDOk(recept) && isNameOk(recept) && isNonNettoOk(recept) && isToleranceOk(recept));
    }

    /**
     * Kontrollerer recept ID
     * @param recept Recep data transfer objekt
     * @return true hvis krav opfyldt
     */
    private boolean isIDOk(ReceptDTO recept) {
        return recept.getReceptId() <= 99999999 && recept.getReceptId() >= 1;
    }

    /**
     * Kontrollerer om navnet består af 1 til 20 tegn
     * @param recept Recep data transfer objekt
     * @return true hvis krav opfyldt
     */
    private boolean isNameOk(ReceptDTO recept){
        return (!(recept.getReceptNavn().length() <= 1 || recept.getReceptNavn().length() > 20));
    }

    /**
     * Kontrollerer om recept nonNetto er indenfor 0.04 til 20.0
     * @param recept Recep data transfer objekt
     * @return true hvis krav opfyldt
     */
    private boolean isNonNettoOk(ReceptDTO recept){
        return (!(recept.getNonNetto()<=0.04 || recept.getNonNetto() > 20.0));
    }

    /**
     * Kontrollerer om recept Tolerance er indenfor 0.09 og 10
     * @param recept Recep data transfer objekt
     * @return true hvis krav opfyldt
     */
    private boolean isToleranceOk(ReceptDTO recept){
        return (!(recept.getTolerance()<=0.09 || recept.getTolerance() > 10.0));
    }

    /**
     * KOntrollerer om Recept Id eksisterer allerede
     * @param recept Recep data transfer objekt
     * @param receptDTOList Recept listen
     * @return true hvis krav opfyldt
     */
    public boolean doesIdExist(ReceptDTO recept, List<ReceptDTO> receptDTOList){
        for (int i = 0; i < receptDTOList.size() ; i++) {
            if (receptDTOList.get(i).getReceptId()==recept.getReceptId()) {
                return true;
            }
        }
        return false;

    }

    /**
     * Kontrollerer om navnet eksisterer allerede
     * @param recept Recep data transfer objekt
     * @param receptDTOList Recept listen
     * @return true hvis krav opfyldt
     */
    public boolean doesNameExist(ReceptDTO recept, List<ReceptDTO> receptDTOList){
        for (int i = 0; i < receptDTOList.size() ; i++) {
            if (receptDTOList.get(i).getReceptNavn().equals(recept.getReceptNavn())) {
                return true;
            }
        }
        return false;
    }


}