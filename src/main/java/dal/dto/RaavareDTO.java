package dal.dto;

public class RaavareDTO {

    /** raavare id i området 1-99999999 vælges af brugerne */
    private int raavareID;
    /** min. 2 max. 20 karakterer */
    private String raavareNavn;
    /** min. 2 max. 20 karakterer */
    private String leverandoer;

// --Commented out by Inspection START (22/06/2020 14.01):
    public double getLagerBeholdning() {
        return lagerBeholdning;
    }
// --Commented out by Inspection STOP (22/06/2020 14.01)

    public void setLagerBeholdning(double lagerBeholdning) {
        /*Lagerbeholdning DECIMAL(4,4)*/
    }

    public void setRaavareID(int raavareID) {
        this.raavareID = raavareID;
    }

    public void setRaavareNavn(String raavareNavn) {
        this.raavareNavn = raavareNavn;
    }

    public void setLeverandoer(String leverandoer) {
        this.leverandoer = leverandoer;
    }

    public int getRaavareID() {
        return raavareID;
    }

    public String getRaavareNavn() {
        return raavareNavn;
    }

    public String getLeverandoer() {
        return leverandoer;
    }

}
