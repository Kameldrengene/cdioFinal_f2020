package dal.dto;

public class RaavareDTO {

    /** raavare id i området 1-99999999 vælges af brugerne */
    private int raavareId;
    /** min. 2 max. 20 karakterer */
    private String raavareNavn;
    /** min. 2 max. 20 karakterer */
    private String leverandoer;
    //Lagerbeholdning DECIMAL(4,4)
    private double lagerBeholdning;

    public double getLagerBeholdning() {
        return lagerBeholdning;
    }

    public void setLagerBeholdning(double lagerBeholdning) {
        this.lagerBeholdning = lagerBeholdning;
    }

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
    }

    public void setRaavareNavn(String raavareNavn) {
        this.raavareNavn = raavareNavn;
    }

    public void setLeverandoer(String leverandoer) {
        this.leverandoer = leverandoer;
    }

    public int getRaavareId() {
        return raavareId;
    }

    public String getRaavareNavn() {
        return raavareNavn;
    }

    public String getLeverandoer() {
        return leverandoer;
    }

}
