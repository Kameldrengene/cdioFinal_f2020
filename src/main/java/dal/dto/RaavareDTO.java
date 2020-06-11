package dal.dto;

public class RaavareDTO {

    /** raavare id i området 1-99999999 vælges af brugerne */
    private int RVID;

    private int RVNummer;
    /** min. 2 max. 20 karakterer */
    private String RVNavn;
    /** min. 2 max. 20 karakterer */
    private String leverandoer;
    //Lagerbeholdning DECIMAL(4,4)
    private double lagerBeholdning;



    public int getRVNummer() {return RVNummer;}

    public void setRVNummer(int RVNummer) {this.RVNummer = RVNummer;}

    public double getLagerBeholdning() {
        return lagerBeholdning;
    }

    public void setLagerBeholdning(double lagerBeholdning) {
        this.lagerBeholdning = lagerBeholdning;
    }

    public void setRVID(int RVID) {
        this.RVID = RVID;
    }

    public void setRVNavn(String RVNavn) {
        this.RVNavn = RVNavn;
    }

    public void setLeverandoer(String leverandoer) {
        this.leverandoer = leverandoer;
    }

    public int getRVID() {
        return RVID;
    }

    public String getRVNavn() {
        return RVNavn;
    }

    public String getLeverandoer() {
        return leverandoer;
    }

}
