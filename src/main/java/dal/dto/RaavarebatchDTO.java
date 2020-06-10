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

    /** raavare batch id i området 1-99999999. Vælges af brugerne */
    int rbId;
    /** raavare id i området 1-99999999 vælges af brugerne */
    int raavareId;
    /** mængde på lager */
    double aktuelMaengde;
    /** Start mængde */
    double startMaengde;
}
