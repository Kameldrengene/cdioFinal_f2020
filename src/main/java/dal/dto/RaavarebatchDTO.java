package dal.dto;

public class RaavarebatchDTO {

    /** raavare batch id i området 1-99999999. Vælges af brugerne */
    private int rbId;
    /** raavare id i området 1-99999999 vælges af brugerne */
    private int raavareId;
    /** Start mængde. Skal være større end nul */
    private double startMaengde;
    /** mængde på lager */
    private double aktuelMaengde;
    /** Råvarens navn */
    private String raavareNavn;
    /** Navn på leverandør 2-20 tegn */
    private String leverandoer;

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

}
