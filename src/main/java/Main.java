import dal.IUserDAO;
import dal.SQLDatabaseIO;
import dal.UserDAOSQL;
import dal.dto.UserDTO;

import java.util.List;

public class Main {
    public static void main(String[] args)  {
        UserDAOSQL data = new UserDAOSQL();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("newadmin");
        userDTO.setIni("adm");
        userDTO.setCpr("132154500");
        userDTO.setPassword("dontchange");
        userDTO.setJob("Administrator");
        userDTO.setAktiv(false);

        try {
            data.createUser(userDTO);
            // System.out.println(data.getData());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
