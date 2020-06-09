package dal.dto;

public class ReceptDTO {
    /** recept id i området 1-99999999 */
    int receptId;
    /** Receptnavn min. 2 max. 20 karakterer */
    String receptNavn;
    /** raavare id i området 1-99999999. Vælges af brugerne
     *  i opgaver står der (1..N) derfor er det en array */
    int raavareId[];
    /** nonnetto i kilogram med 4 decimaler */
    double nonNetto;
    /** tolerance i kilogram med 4 decimaler */
    double tolerance;
}
