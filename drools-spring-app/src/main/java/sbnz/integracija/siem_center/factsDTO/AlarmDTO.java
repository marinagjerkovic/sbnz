package sbnz.integracija.siem_center.factsDTO;

import java.time.LocalDateTime;

import sbnz.integracija.siem_center.facts.AlarmType;

public class AlarmDTO {
	private Long id;
	private AlarmType alarmType;
	private String userUsername;
	private Long machineId;
	private LocalDateTime time;
	
	public AlarmDTO() {
		super();
	}

	public AlarmDTO(Long id, AlarmType alarmType, String userUsername, Long machineId, LocalDateTime time) {
		super();
		this.id = id;
		this.alarmType = alarmType;
		this.userUsername = userUsername;
		this.machineId = machineId;
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
}
