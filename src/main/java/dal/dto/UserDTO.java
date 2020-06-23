package dal.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{

	/** Bruger ID 10 til 99 */
	private int userID;
	/** Bruger navn 2 til 20 tegn */
	private String userName;
	/** Bruger Initial 2 til 4 tegn */
	private String ini;
	/** Bruger passsword 6 til 50 tegn */
	private String password;
	/** Bruger rolle (Admin,Farmaceut,Produktionsleder,Laborant) */
	private String job;
	/** Bruger aktivitet status*/
	private boolean aktiv;

	private static int counter = 11;

	public UserDTO() {
	}

	public String getPassword(){
	    return password;
    }

    public void setPassword(String newPassword){
	    this.password = newPassword;
    }

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIni() {
		return ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public boolean getAktiv(){return aktiv;}

	public void setAktiv(boolean aktiv) {this.aktiv = aktiv;}

	public static void setCounter(int counter) {
		UserDTO.counter = counter;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"userID=" + userID +
				", userName='" + userName + '\'' +
				", ini='" + ini + '\'' +
//				", cpr='" + cpr + '\'' +
				", password='" + password + '\'' +
				", job='" + job + '\'' +
				", aktiv=" + aktiv +
				'}';
	}
}
