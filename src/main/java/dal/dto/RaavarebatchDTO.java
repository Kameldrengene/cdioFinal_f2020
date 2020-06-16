package dal.dto;

public class RaavarebatchDTO {
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

    public String getRaavareNavn() {
        return raavareNavn;
    }

    public void setRaavareNavn(String raavareNavn) {
        this.raavareNavn = raavareNavn;
    }

    public String getLeverandoer() {
        return leverandoer;
    }

    public void setLeverandoer(String leverandoer) {
        this.leverandoer = leverandoer;
    }


    /** raavare batch id i området 1-99999999. Vælges af brugerne */
    int rbId;

    /** raavare id i området 1-99999999 vælges af brugerne */
    int raavareId;

    /** Start mængde */
    double startMaengde;

    /** mængde på lager */
    double aktuelMaengde;

    String raavareNavn;

    String leverandoer;

}
