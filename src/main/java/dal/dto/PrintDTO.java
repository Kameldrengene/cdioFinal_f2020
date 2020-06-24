package dal.dto;

public class PrintDTO {

    /** produkt batch id i området 1-99999999. Vælges af brugerne */
    private int pbId;
    /** status 0: ikke påbegyndt, 1: under produktion, 2: afsluttet */
    private String status;
    /** recept id i området 1-99999999. Vælges af brugerne */
    private int receptId;
    /** user id i området 1-99999999. Vælges af brugerne
     *  i opgaver står der (1..N) derfor er det en array */
    private int userId;
    /** raavarebatch id  1-99999999. vælges af brugere */
    private int rbID;
    /** tara i kilogram med 4 decimaler */
    private double tara;
    /** netto i kilogram med 4 decimaler */
    private double netto;
    /** raavare id i området 1-99999999 vælges af brugerne */
    private int raavareID;
    /** min. 2 max. 20 karakterer */
    private String raavareNavn;
    /** min. 2 max. 20 karakterer */
    private String leverandoer;
    /** nonnetto i kilogram med 4 decimaler */
    private double nonNetto;
    /** tolerance i kilogram med 4 decimaler */
    private double tolerance;
    /** Receptnavn min. 2 max. 20 karakterer */
    private String receptNavn;
    /** Produktbatch oprettelses dato */
    private String dato;
    /** Summen af tara */
    private double sumTara;
    /** Summen af Netto */
    private double sumNetto;

    public PrintDTO() { }

    public double getSumTara() {
        return sumTara;
    }

    public void setSumTara(double sumTara) {
        this.sumTara = sumTara;
    }

    public double getSumNetto() {
        return sumNetto;
    }

    public void setSumNetto(double sumNetto) {
        this.sumNetto = sumNetto;
    }

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

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
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

    public int getRaavareID() {
        return raavareID;
    }

    public void setRaavareID(int raavareID) {
        this.raavareID = raavareID;
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

    public String getReceptNavn() {
        return receptNavn;
    }

    public void setReceptNavn(String receptNavn) {
        this.receptNavn = receptNavn;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}
