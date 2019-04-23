package sbnz.integracija.siem_center.facts;

import java.io.Serializable;

public class Machine implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String ip;
	private OperatingSystem os;
	
	public Machine() {
		super();
	}

	public Machine(String ip, OperatingSystem os) {
		super();
		this.ip = ip;
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

	@Override
	public String toString() {
		return "Machine [ip=" + ip + "]";
	}
	
	

}
