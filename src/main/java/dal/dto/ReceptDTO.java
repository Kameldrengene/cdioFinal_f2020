package dal.dto;

public class ReceptDTO {
    /** recept id i området 1-99999999 */
    int receptId;
    /** Receptnavn min. 2 max. 20 karakterer */
    String receptNavn;
    /** raavare id i området 1-99999999. Vælges af brugerne
     *  i opgaver står der (1..N) derfor er det en array */
    int raavareId;
    /** nonnetto i kilogram med 4 decimaler */
    double nonNetto;
    /** tolerance i kilogram med 4 decimaler */
    double tolerance;

    public ReceptDTO(){

    }

    public String getReceptNavn() {
        return receptNavn;
    }

    public void setReceptNavn(String receptNavn) {
        this.receptNavn = receptNavn;
    }

    public int getRaavareId() {
        return raavareId;
    }

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
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

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }
}
