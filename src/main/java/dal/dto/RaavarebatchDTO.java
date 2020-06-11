package dal.dto;

public class RaavarebatchDTO {
    public int getRVBID() {
        return RVBID;
    }

    public void setRVBID(int RVBID) {
        this.RVBID = RVBID;
    }

    public int getRVID() {
        return RVID;
    }

    public void setRVID(int RVID) {
        this.RVID = RVID;
    }

    public double getAktuelMaengde() {
        return aktuelMaengde;
    }

    public void setAktuelMaengde(double aktuelMaengde) {
        this.aktuelMaengde = aktuelMaengde;
    }

    public double getMaengde() {
        return maengde;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }

    public int getRVBNummer() {
        return RVBNummer;
    }

    public void setRVBNummer(int RVBNummer) {
        this.RVBNummer = RVBNummer;
    }

    /** raavare batch id i området 1-99999999. Vælges af brugerne */
    int RVBID;

    int RVBNummer;
    /** raavare id i området 1-99999999 vælges af brugerne */
    int RVID;
    /** mængde på lager */
    double aktuelMaengde;
    /** Start mængde */
    double maengde;
}
