package dal.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{

	private int userID;
	private String userName;
	private String ini;
	private String password;
	private String job;
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


// --Commented out by Inspection START (22/06/2020 14.01):
//	public void removeRole(){
// --Commented out by Inspection START (22/06/2020 14.01):
////		this.job = null;
////	}
//// --Commented out by Inspection STOP (22/06/2020 14.01)
//
//// --Commented out by Inspection START (22/06/2020 14.01):
// --Commented out by Inspection STOP (22/06/2020 14.01)
//	public static int getCounter() {
//		return counter;
//	}
// --Commented out by Inspection STOP (22/06/2020 14.01)

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
