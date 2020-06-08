package data;

import dto.UserDTO;
import java.util.HashMap;
import java.util.Map;

public class MapNonPersistent {
    Map<Integer , UserDTO> users;
    public MapNonPersistent(){
        users = new HashMap<>();
        UserDTO volkan = new UserDTO();
        UserDTO mikkel = new UserDTO();
        UserDTO mark = new UserDTO();
        UserDTO talha = new UserDTO();
        UserDTO frederik = new UserDTO();
        UserDTO lasse = new UserDTO();
        volkan.setUserID(11);
        volkan.addRole("Admin");
        volkan.setUserName("Volkan");
        volkan.setIni("VOL");
        volkan.setCpr("0912941235");
        mikkel.setUserID(12);
        mikkel.addRole("Pharmacist");
        mikkel.setUserName("Mikkel");
        mikkel.setIni("MIK");
        mikkel.setCpr("3007902345");
        mark.setUserID(13);
        mark.setUserName("Mark");
        mark.setIni("MAR");
        mark.setCpr("1603783455");
        mark.addRole("Foreman");
        talha.setUserID(14);
        talha.addRole("Operator");
        talha.setUserName("Talha");
        talha.setIni("TAL");
        talha.setCpr("2301974565");
        frederik.setUserID(15);
        frederik.setUserName("Frederik");
        frederik.setIni("FRE");
        frederik.setCpr("0501025675");
        frederik.addRole("Pharmacist");
        frederik.addRole("Operator");
        lasse.setUserID(16);
        lasse.setIni("LAS");
        lasse.setUserName("Lasse");
        lasse.setCpr("1104966785");
        lasse.addRole("Admin");
        lasse.addRole("Pharmacist");
        lasse.addRole("Foreman");
        lasse.addRole("Operator");

        users.put(volkan.getUserID(),volkan);
        users.put(mikkel.getUserID(),mikkel);
        users.put(mark.getUserID(),mark);
        users.put(talha.getUserID(),talha);
        users.put(frederik.getUserID(),frederik);
        users.put(lasse.getUserID(),lasse);
    }

    public Map< Integer , UserDTO> getUsers() {
        return users;
    }

}
