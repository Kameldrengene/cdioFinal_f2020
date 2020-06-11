package dal.dto;

public class ReceptDTO {
    /** recept id i området 1-99999999 */
    int RCID;
    /** Receptnavn min. 2 max. 20 karakterer */
    String RCNavn;
    /** raavare id i området 1-99999999. Vælges af brugerne
     *  i opgaver står der (1..N) derfor er det en array */
    int RVID;
    /** nonnetto i kilogram med 4 decimaler */
    double nonNetto;
    /** tolerance i kilogram med 4 decimaler */
    double tolerance;

    public ReceptDTO(){

    }

    public String getRCNavn() {
        return RCNavn;
    }

    public void setRCNavn(String RCNavn) {
        this.RCNavn = RCNavn;
    }

    public int getRVID() {
        return RVID;
    }

    public void setRVID(int RVID) {
        this.RVID = RVID;
    }

    public double getNonNetto() {
        return nonNetto;
    }

    public void setNonNetto(double nonNetto) {
        this.nonNetto = nonNetto;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public int getRCID() {
        return RCID;
    }

    public void setRCID(int RCID) {
        this.RCID = RCID;
    }
}
