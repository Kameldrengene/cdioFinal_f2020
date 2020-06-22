package Funktionalitet;

import dal.dto.RaavareDTO;

import java.util.List;

public class RaavareFunc {

    public String raavaremsg(RaavareDTO rv,List<RaavareDTO> rvList, String type) {
        if (!isIDOk(rv) && type.equals("POST")) {
            return "Leverandør ID skal være mellem  1 og 99999999";
        }
        if (!isNavnOk(rv)) {
            return "Råvare navn skal være mellem  2 til 20 tegn";
        }
        if (!isleverandoerOk(rv)) {
            return "leverandør navn skal være mellem  2 til 20 tegn";
        }
        if(IDExists(rv,rvList) && type.equals("POST")){
            return "Råvare ID'et er optaget\nVælg en anden";
        }
        if(NavnExists(rv,rvList)){
            if(type.equals("PUT")){
                return "Error: Kan ikke opdatere, da Råvare navnet er optaget";
            }
            return "Råvare navn er optaget\nVælg en anden";
        }
        return null;
    }

    public boolean isNewRaavareOk(RaavareDTO rv, List<RaavareDTO> rvList) {
        return (!NavnExists(rv,rvList) && !IDExists(rv,rvList) && isIDOk(rv) && isNavnOk(rv) && isleverandoerOk(rv));
    }

    public boolean isUpdateRaavareOk(RaavareDTO rv, List<RaavareDTO> rvList) {
        return (!NavnExists(rv,rvList) && isNavnOk(rv) && isleverandoerOk(rv));
    }

    public boolean IDExists(RaavareDTO rv, List<RaavareDTO> rvList) {
        for(int i = 0; i < rvList.size(); i++){
            if(rv.getRaavareID() == rvList.get(i).getRaavareID()){
                return true;
            }
        }
        return false;
    }

    public boolean NavnExists(RaavareDTO rv, List<RaavareDTO> rvList) {
        for(int i = 0; i < rvList.size(); i++){
            if(rv.getRaavareNavn().equals(rvList.get(i).getRaavareNavn())) {
                if(!(rv.getRaavareID() == rvList.get(i).getRaavareID())) {
                    return true;
                }
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
