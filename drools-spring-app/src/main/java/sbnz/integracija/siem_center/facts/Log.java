package sbnz.integracija.siem_center.facts;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Log implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LogType type;
	private Machine machine;
	private User user;
	private LocalDateTime time;
	private String text;
	
	//sta je status loga?
	private String status;
	
	public Log() {
		super();
	}

	public Log(LogType type, Machine machine, User user, LocalDateTime time, String text, String status) {
		super();
		this.type = type;
		this.machine = machine;
		this.user = user;
		this.time = time;
		this.text = text;
		this.status = status;
	}

	public LogType getType() {
		return type;
	}

	public void setType(LogType type) {
		this.type = type;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Log [type=" + type + ", machine=" + machine + ", user=" + user + ", time=" + time + ", text=" + text
				+ ", status=" + status + ", getType()=" + getType() + ", getMachine()=" + getMachine() + ", getUser()="
				+ getUser() + ", getTime()=" + getTime() + ", getText()=" + getText() + ", getStatus()=" + getStatus()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
