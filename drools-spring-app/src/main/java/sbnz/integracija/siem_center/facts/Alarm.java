package sbnz.integracija.siem_center.facts;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Alarm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String alarmType;
	private User user;
	private Machine machine;
	private LocalDateTime time;
	
	public Alarm() {
		super();
	}

	public Alarm(String alarmType, User user, Machine machine, LocalDateTime time) {
		super();
		this.alarmType = alarmType;
		this.user = user;
		this.machine = machine;
		this.time = time;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Alarm [alarmType=" + alarmType + ", user=" + user + ", machine=" + machine + ", time=" + time + "]";
	}
	
	
}
