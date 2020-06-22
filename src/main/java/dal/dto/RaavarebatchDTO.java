package dal.dto;

public class RaavarebatchDTO {

    /** raavare batch id i området 1-99999999. Vælges af brugerne */
    int rbId;
    /** raavare id i området 1-99999999 vælges af brugerne */
    int raavareId;
    /** Start mængde. Skal være større end nul */
    double startMaengde;
    /** mængde på lager */
    double aktuelMaengde;
    /** Råvarens navn */
    String raavareNavn;
    /** Navn på leverandør 2-20 tegn */
    String leverandoer;

    public int getRbId() {
        return rbId;
    }

    public void setRbId(int rbId) {
        this.rbId = rbId;
    }

    public int getRaavareId() {
        return raavareId;
    }

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
    }

    public double getAktuelMaengde() {
        return aktuelMaengde;
    }

    public void setAktuelMaengde(double aktuelMaengde) {
        this.aktuelMaengde = aktuelMaengde;
    }

    public double getStartMaengde() {
        return startMaengde;
    }

    public void setStartMaengde(double startMaengde) {
        this.startMaengde = startMaengde;
    }

// --Commented out by Inspection START (22/06/2020 14.01):
//    public String getRaavareNavn() {
//        return raavareNavn;
//    }
// --Commented out by Inspection STOP (22/06/2020 14.01)

    public void setRaavareNavn(String raavareNavn) {
        this.raavareNavn = raavareNavn;
    }

// --Commented out by Inspection START (22/06/2020 14.01):
//    public String getLeverandoer() {
//        return leverandoer;
//    }
// --Commented out by Inspection STOP (22/06/2020 14.01)

    public void setLeverandoer(String leverandoer) {
        this.leverandoer = leverandoer;
    }

}
