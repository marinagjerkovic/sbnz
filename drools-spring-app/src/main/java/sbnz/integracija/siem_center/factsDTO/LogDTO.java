package sbnz.integracija.siem_center.factsDTO;

import java.time.LocalDateTime;

import sbnz.integracija.siem_center.facts.InformationSystem;
import sbnz.integracija.siem_center.facts.Log;
import sbnz.integracija.siem_center.facts.LogStatus;
import sbnz.integracija.siem_center.facts.LogType;

public class LogDTO {
	private Long id;
	private LogType type;
	private LogStatus status;
	private String machineIp;
	private String userUsername;
	private LocalDateTime time;
	private String text;
	private InformationSystem informationSystem;
	
	
	
	public LogDTO() {
		super();
	}
	
	public LogDTO(Log l) {
		super();
		this.id = l.getId();
		this.type = l.getType();
		this.status = l.getStatus();
		this.machineIp = l.getMachine().getIp();
		this.userUsername = l.getUser().getUsername();
		this.time = l.getTime();
		this.text = l.getText();
		this.informationSystem = l.getInformationSystem();
	}



	public LogDTO(Long id, LogType type, LogStatus status, String machineIp, String userUsername, LocalDateTime time,
			String text, InformationSystem informationSystem) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.machineIp = machineIp;
		this.userUsername = userUsername;
		this.time = time;
		this.text = text;
		this.informationSystem = informationSystem;
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



	public String getMachineIp() {
		return machineIp;
	}



	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
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



	public InformationSystem getInformationSystem() {
		return informationSystem;
	}



	public void setInformationSystem(InformationSystem informationSystem) {
		this.informationSystem = informationSystem;
	}

	@Override
	public String toString() {
		return "LogDTO [id=" + id + ", type=" + type + ", status=" + status + ", machineIp=" + machineIp
				+ ", userUsername=" + userUsername + ", time=" + time + ", text=" + text + ", informationSystem="
				+ informationSystem + "]";
	}
	
	
	
	
	
	
}
