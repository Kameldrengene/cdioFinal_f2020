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

}
