package sbnz.integracija.siem_center.facts;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private Risk risk;
	private LocalDateTime  lastActivity;
	
	public User() {
		super();
	}

	public User(String username, String password, Risk risk, LocalDateTime lastActivity) {
		super();
		this.username = username;
		this.password = password;
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

	public LocalDateTime getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(LocalDateTime lastActivity) {
		this.lastActivity = lastActivity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", risk=" + risk + ", lastActivity="
				+ lastActivity + "]";
	}

	
}
