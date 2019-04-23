package sbnz.integracija.siem_center.facts;

import java.io.Serializable;
import java.util.List;

public class MaliciousIps implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<String> ips;
	
	public MaliciousIps() {
		super();
	}

	public MaliciousIps(List<String> ips) {
		super();
		this.ips = ips;
	}

	public List<String> getIps() {
		return ips;
	}

	public void setIps(List<String> ips) {
		this.ips = ips;
	}

	@Override
	public String toString() {
		return "MaliciousIps [ips=" + ips + "]";
	}
	
	

}
