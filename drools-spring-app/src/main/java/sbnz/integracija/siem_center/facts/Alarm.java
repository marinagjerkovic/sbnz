package sbnz.integracija.siem_center.facts;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.kie.api.definition.type.Role;

@Entity
@org.kie.api.definition.type.Role(Role.Type.EVENT)
public class Alarm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column
	private AlarmType alarmType;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;
	
	public Alarm(AlarmType alarmType, User user, Machine machine, LocalDateTime time,
			InformationSystem informationSystem) {
		super();
		this.alarmType = alarmType;
		this.user = user;
		this.machine = machine;
		this.time = time;
		this.informationSystem = informationSystem;
	}



	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Machine machine;
	
	@Column
	private LocalDateTime time;
	
	@Column
	private InformationSystem informationSystem;
	
	public Alarm() {
		super();
	}

	

	public Alarm(Long id, AlarmType alarmType, User user, Machine machine, LocalDateTime time,
			InformationSystem informationSystem) {
		super();
		this.id = id;
		this.alarmType = alarmType;
		this.user = user;
		this.machine = machine;
		this.time = time;
		this.informationSystem = informationSystem;
	}



	public AlarmType getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(AlarmType alarmType) {
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
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public InformationSystem getInformationSystem() {
		return informationSystem;
	}



	public void setInformationSystem(InformationSystem informationSystem) {
		this.informationSystem = informationSystem;
	}



	@Override
	public String toString() {
		return "Alarm [alarmType=" + alarmType + ", user=" + user + ", machine=" + machine + ", time=" + time + "]";
	}
	
	
}
