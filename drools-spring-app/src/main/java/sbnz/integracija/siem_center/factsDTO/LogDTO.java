package sbnz.integracija.siem_center.factsDTO;

import java.time.LocalDateTime;

import sbnz.integracija.siem_center.facts.LogStatus;
import sbnz.integracija.siem_center.facts.LogType;

public class LogDTO {
	private Long id;
	private LogType type;
	private LogStatus status;
	private Long machineId;
	private String userUsername;
	private LocalDateTime time;
	private String text;
	
	
	
	public LogDTO() {
		super();
	}



	public LogDTO(Long id, LogType type, LogStatus status, Long machineId, String userUsername, LocalDateTime time,
			String text) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.machineId = machineId;
		this.userUsername = userUsername;
		this.time = time;
		this.text = text;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public LogType getType() {
		return type;
	}



	public void setType(LogType type) {
		this.type = type;
	}



	public LogStatus getStatus() {
		return status;
	}



	public void setStatus(LogStatus status) {
		this.status = status;
	}



	public Long getMachineId() {
		return machineId;
	}



	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}



	public String getUserUsername() {
		return userUsername;
	}



	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
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
	
	
	
	
	
	
}
