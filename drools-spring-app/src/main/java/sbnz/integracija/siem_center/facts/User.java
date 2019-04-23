package sbnz.integracija.siem_center.facts;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Risk risk;
	
	//kako ovo pamtimo?
	private String lastActivity;
	
	public User() {
		super();
	}

	public User(String username, Risk risk, String lastActivity) {
		super();
		this.username = username;
		this.risk = risk;
		this.lastActivity = lastActivity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public String getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(String lastActivity) {
		this.lastActivity = lastActivity;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", risk=" + risk + ", lastActivity=" + lastActivity + "]";
	}
	
	
	
}
