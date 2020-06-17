package Funktionalitet;

import dal.dto.UserDTO;

public class UserFunc {
    public boolean isUserOk(UserDTO user) {
        return (isNameOk(user) && isIniOk(user) && isPassOk(user));
    }

    private boolean isNameOk(UserDTO user) {
        return (!(user.getUserName().length() < 2 || user.getUserName().length() > 20));
    }

    private boolean isIniOk(UserDTO user) {
        return (!(user.getIni().length() < 2 || user.getIni().length() > 4));
    }

    private boolean isPassOk(UserDTO user) {
        return (!(user.getPassword().length() < 6 || user.getPassword().length() > 50));
    }
}
