package bll;

import dto.UserDTO;

import java.util.List;

public interface IFunctionality {
    
    boolean isUserIDPresent(int ID, int[] IDs);
    int[] getUserIDs(List<UserDTO> users);
    boolean verifyPassword(UserDTO user, String pass) throws Exception;
    
}
