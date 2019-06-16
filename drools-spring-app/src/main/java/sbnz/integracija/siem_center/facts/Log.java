package sbnz.integracija.siem_center.facts;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Log implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private LogType type;
	private LogStatus status;
	private Machine machine;
	private User user;
	private LocalDateTime time;
	private String text;
	
	public Log() {
		super();
	}
	
	public Log(int id, LogType type, LogStatus status, Machine machine, User user, LocalDateTime time, String text) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.machine = machine;
		this.user = user;
		this.time = time;
		this.text = text;
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

	public LogStatus getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(LogStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", type=" + type + ", status=" + status + ", machine=" + machine + ", user=" + user
				+ ", time=" + time + ", text=" + text + "]";
	}


	
	
}
