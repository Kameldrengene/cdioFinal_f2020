package dal.dto;

public class ProduktbatchKompDTO {

    /** produkt batch id i området 1-99999999. Vælges af brugerne */
    private int pbId;
    /** status 0: ikke påbegyndt, 1: under produktion, 2: afsluttet */
    private String status;
    /** user id i området 1-99999999. Vælges af brugerne
     *  i opgaver står der (1..N) derfor er det en array */
    private int userId;
    /** raavarebatch id  1-99999999. vælges af brugere */
    private int rbID;
    /** tara i kilogram med 4 decimaler */
    private double tara;
    /** netto i kilogram med 4 decimaler */
    private double netto;

    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
