package pl;

import dto.UserDTO;
import java.util.List;

public interface IUI {
    
    public int showMenu(String message, String... menuItems);
    public String inputName();
    public String inputInit();
    public String inputCPR();
    public void addRolesToUser(UserDTO user);
    public UserDTO createUser(int id);
    public void listUsers(List<UserDTO> list);
    public int getUserID();
    public String inputString(String message);
    
}
