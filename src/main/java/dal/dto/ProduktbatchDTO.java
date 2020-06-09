package dal.dto;

public class ProduktbatchDTO {
    /** produkt batch id i området 1-99999999. Vælges af brugerne */
    int pbId;
    /** status 0: ikke påbegyndt, 1: under produktion, 2: afsluttet */
    int status;
    /** recept id i området 1-99999999. Vælges af brugerne */
    int receptId;
    /** user id i området 1-99999999. Vælges af brugerne
     *  i opgaver står der (1..N) derfor er det en array */
    int userId[];
    /** raavarebatch id  1-99999999. vælges af brugere */
    int rbID;
    /** tara i kilogram med 4 decimaler */
    double tara;
    /** netto i kilogram med 4 decimaler */
    double netto;

    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public int[] getUserId() {
        return userId;
    }

    public void setUserId(int[] userId) {
        this.userId = userId;
    }

    public int getRbID() {
        return rbID;
    }

    public void setRbID(int rbID) {
        this.rbID = rbID;
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
