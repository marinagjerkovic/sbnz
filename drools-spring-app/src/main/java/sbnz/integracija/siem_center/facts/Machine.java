package sbnz.integracija.siem_center.facts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Machine implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column
	private String ip;
	
	@Column
	private boolean maliciousIp;
	
	@Column
	private OperatingSystem os;
	
	public Machine() {
		super();
	}

	public Machine(String ip, boolean maliciousIp, OperatingSystem os) {
		super();
		this.ip = ip;
		this.maliciousIp = maliciousIp;
		this.os = os;
	}
	
	

	public Machine(Long id, String ip, boolean maliciousIp, OperatingSystem os) {
		super();
		this.id = id;
		this.ip = ip;
		this.maliciousIp = maliciousIp;
		this.os = os;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public OperatingSystem getOs() {
		return os;
	}

	public void setOs(OperatingSystem os) {
		this.os = os;
	}

	public boolean isMaliciousIp() {
		return maliciousIp;
	}

	public void setMaliciousIp(boolean maliciousIp) {
		this.maliciousIp = maliciousIp;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Machine [ip=" + ip + ", maliciousIp=" + maliciousIp + ", os=" + os + "]";
	}

	
	
	

}
