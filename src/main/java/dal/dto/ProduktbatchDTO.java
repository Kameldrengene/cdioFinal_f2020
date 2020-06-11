package dal.dto;

public class ProduktbatchDTO {
    /** produkt batch id i området 1-99999999. Vælges af brugerne */
    int PBID;
    /** status 0: ikke påbegyndt, 1: under produktion, 2: afsluttet */
    String status;
    /** recept id i området 1-99999999. Vælges af brugerne */
    int RCID;
    /** user id i området 1-99999999. Vælges af brugerne
     *  i opgaver står der (1..N) derfor er det en array */
    int userID;
    /** raavarebatch id  1-99999999. vælges af brugere */
    int RVBID;
    /** tara i kilogram med 4 decimaler */
    double tara;
    /** netto i kilogram med 4 decimaler */
    double netto;

    public int getPBID() {
        return PBID;
    }

    public void setPBID(int PBID) {
        this.PBID = PBID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRCID() {
        return RCID;
    }

    public void setRCID(int RCID) {
        this.RCID = RCID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRVBID() {
        return RVBID;
    }

    public void setRVBID(int RVBID) {
        this.RVBID = RVBID;
    }

    public double getTara() {
        return tara;
    }

    public void setTara(double tara) {
        this.tara = tara;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }



}
