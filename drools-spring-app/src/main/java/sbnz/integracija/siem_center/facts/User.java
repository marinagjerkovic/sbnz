package sbnz.integracija.siem_center.facts;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private Risk risk;
	
	@Column
	private LocalDateTime  lastActivity;
	
	public User() {
		super();
		this.id = -1L;
	}

	public User(String username, String password, Risk risk, LocalDateTime lastActivity) {
		super();
		this.username = username;
		this.password = password;
		this.risk = risk;
		this.lastActivity = lastActivity;
	}
	
	

	public User(Long id, String username, String password, Risk risk, LocalDateTime lastActivity) {
		super();
		this.id = id;
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
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", risk=" + risk + ", lastActivity="
				+ lastActivity + "]";
	}

	
}
