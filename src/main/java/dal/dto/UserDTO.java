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
	private List<String> roles;
	private static int counter = 11;
	
//TODO Add relevant fields
	
	public UserDTO() {
		this.roles = new ArrayList<>();
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

	public List<String> getRoles() {
		return roles;
	}
	public String getRolesToString(){
		String answer = "";
		for (int i = 0; i < roles.size(); i++) {
			if (i > 0)
				answer = answer + "-" + roles.get(i);
			else
				answer = answer + roles.get(i);
		}
		return answer;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role){
		boolean exist = false;
		for (int i = 0; i < roles.size(); i++) {
			if (role.equals(roles.get(i))) {
				exist = true;
				break;
			}
		}
		if (!exist)
			this.roles.add(role);
	}
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public void removeRole(String role){
		this.roles.remove(role);
	}
	
	public void removeRoles(){
		removeRole("Admin");
		removeRole("Pharmacist");
		removeRole("Foreman");
		removeRole("Operator");
	}
	
	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		UserDTO.counter = counter;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userID + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
	}
	
	
	
}
