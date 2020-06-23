package controller;

import dal.ReceptDAOSQL;
import dal.dto.ReceptDTO;
import Funktionalitet.ReceptFunc;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class ReceptController {

    public final ReceptDAOSQL receptDAOSQL;
    private final ReceptFunc receptFunc;
    private final String SQLErrorMsg = "ERROR: Fejl i forbindelse med kontakt af databasen";

    public ReceptController() {
        receptDAOSQL = new ReceptDAOSQL();
        receptFunc = new ReceptFunc();

    }

    /**
     * henter den enkelt recept med en recept komponent.
     * @param receptID Recept ID
     * @param raavareID Raavare ID
     * @return Returnerer en specifik raavare som tilhører en recept
     */
    public ReceptDTO getRecept(int receptID, int raavareID) {
        try {
            return receptDAOSQL.getRecept(receptID, raavareID);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
    }

    /**
     * henter alle recept komponenter af en recept fra SQLdatabasen
     * @return henter alle recept komponenter af en recept fra SQLdatabasen
     */
    public List<ReceptDTO> getData() {
        try {
            return receptDAOSQL.getReceptList();
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }

    }

    /**
     * henter alle recept komponenter af en recept
     * @param receptId Recept ID
     * @return henter alle recept komponenter af en recept
     */
    public List<ReceptDTO> getuniqueReceptkomponents(int receptId) {
        try {
            return receptDAOSQL.getReceptKomponents(receptId);
        }catch(SQLException e){
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);

        }

    }

    /**
     * Opretter hele recepten med alle ens komponenter på en gang, hvis alle kravene er opfyldt.
     * @param recept Alle rækker i en recept(komponenter)
     * @return Hele recepten med komponenter
     */
    public List<ReceptDTO> opretRecept (List < ReceptDTO > recept) {

        try {
            int i = 0;
            while (i < recept.size()) {
                if (!(receptFunc.isReceptOk(recept.get(i),getData()))) { //checker for hvis en kraven er ikke opfyldt
                    throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity(receptFunc.receptmsg(recept.get(i),getData())).build());
                }
                i++;
            }
            receptDAOSQL.createReceptList(recept);
        } catch (SQLException e) {
            throw buildError(Response.Status.NOT_ACCEPTABLE, SQLErrorMsg);
        }
        return recept;
    }
    /**
     * Kaster en Exception videre hvis der sker en fejl i systemet
     * @param status Web status
     * @param msg Besked der skal stå i fejl meddelelse
     * @return returner en Webapplication Exception
     */
    public WebApplicationException buildError (Response.Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}




