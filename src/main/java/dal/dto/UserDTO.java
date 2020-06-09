package dal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private int userID;
	private String userName;                
	private String ini;
	private String cpr;
	private String password;
	private String role;
	private boolean aktiv;
	private static int counter = 11;
	
//TODO Add relevant fields
	
	public UserDTO() {
		this.password = "SkiftMig!";
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
	public String getCpr() {
		return cpr;
	}
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean getAktiv(){return aktiv;}
	public void setAktiv(boolean aktiv) {this.aktiv = aktiv;}

	
	public void removeRole(){
		this.role = null;
	}
	
	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		UserDTO.counter = counter;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userID + ", userName=" + userName + ", ini=" + ini + ", role=" + role + ", aktiv=" + aktiv + ", password=" + password + "]";
	}
	
	
	
}
