package Funktionalitet;

import dal.dto.UserDTO;

public class UserFunc {
    /**
     * Kontrollerer en bruger om den opfylder alle krav
     * @param user User Data Transfer Objekt
     * @return True hvis alle krav er opfyldt
     */
    public boolean isUserOk(UserDTO user) {
        return (isNameOk(user) && isIniOk(user) && isPassOk(user));
    }

    /**
     * Kontrol om navnet er i mellem 2 og 20 tegn
     * @param user User Data Transfer Objekt
     * @return True hvis krav er opfyldt
     */
    private boolean isNameOk(UserDTO user) {
        return (!(user.getUserName().length() < 2 || user.getUserName().length() > 20));
    }

    /**
     * Kontrol om Initial er i mellem 2 til 4 tegn
     * @param user User Data Transfer Objekt
     * @return True hvis krav er opfyldt
     */
    private boolean isIniOk(UserDTO user) {
        return (!(user.getIni().length() < 2 || user.getIni().length() > 4));
    }

    /**
     * Kontrol om Password er i mellem 6 og 50 tegn
     * @param user User Data Transfer Objekt
     * @return True hvis krav er opfyldt
     */
    private boolean isPassOk(UserDTO user) {
        return (!(user.getPassword().length() < 6 || user.getPassword().length() > 50));
    }
}
