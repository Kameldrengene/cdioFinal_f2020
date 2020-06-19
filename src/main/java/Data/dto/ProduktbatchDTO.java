package Data.dto;

public class ProduktbatchDTO {
    /** produkt batch id i området 1-99999999. Vælges af brugerne */
    int pbId;
    /** status 0: ikke påbegyndt, 1: under produktion, 2: afsluttet */
    String status;
    /** recept id i området 1-99999999. Vælges af brugerne */
    int receptId;

    String dato;

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

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) { this.dato = dato; }




}
