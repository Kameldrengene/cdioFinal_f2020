package Controllers;

import Funktionalitet.RaavareFunc;

import Data.RaavareDAOSQL;
import Data.dto.RaavareDTO;

import java.sql.SQLException;
import java.util.List;

public class RaavareController {
    public final RaavareDAOSQL raavareDAOSQL;

    public RaavareController (){
        raavareDAOSQL = new RaavareDAOSQL();
    }

    public List<RaavareDTO> getData()  {
        try {
            return raavareDAOSQL.getRaavareList();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO getRaavare(int id) {
        try {
            return raavareDAOSQL.getRaavare(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public RaavareDTO opretRaavare (RaavareDTO raavareDTO) {
        RaavareFunc rvFunc = new RaavareFunc();
        try {
            if (rvFunc.isNewRaavareOk(raavareDTO,getData())) {
                raavareDAOSQL.createRaavare(raavareDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }

    public RaavareDTO updateRaavare(RaavareDTO raavareDTO) {
        RaavareFunc rvFunc = new RaavareFunc();
        try {
            if (rvFunc.isUpdateRaavareOk(raavareDTO,getData())) {
                raavareDAOSQL.updateRaavare(raavareDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return raavareDTO;
    }


}

