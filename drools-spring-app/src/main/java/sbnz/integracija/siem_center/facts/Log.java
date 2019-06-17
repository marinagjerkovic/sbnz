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
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column
	private LogType type;
	
	@Column
	private LogStatus status;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Machine machine;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;
	
	@Column
	private LocalDateTime time;
	
	@Column
	private String text;
	
	@Column
	private InformationSystem informationSystem;
	
	public Log() {
		super();
	}
	
	

	public Log(Long id, LogType type, LogStatus status, Machine machine, User user, LocalDateTime time, String text,
			InformationSystem informationSystem) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.machine = machine;
		this.user = user;
		this.time = time;
		this.text = text;
		this.informationSystem = informationSystem;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(LogStatus status) {
		this.status = status;
	}
	
	

	public InformationSystem getInformationSystem() {
		return informationSystem;
	}



	public void setInformationSystem(InformationSystem informationSystem) {
		this.informationSystem = informationSystem;
	}



	@Override
	public String toString() {
		return "Log [id=" + id + ", type=" + type + ", status=" + status + ", machine=" + machine + ", user=" + user
				+ ", time=" + time + ", text=" + text + "]";
	}


	
	
}
