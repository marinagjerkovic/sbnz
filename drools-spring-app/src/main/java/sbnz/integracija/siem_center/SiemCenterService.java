package sbnz.integracija.siem_center;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.siem_center.facts.Log;
import sbnz.integracija.siem_center.facts.LogStatus;
import sbnz.integracija.siem_center.facts.Machine;
import sbnz.integracija.siem_center.facts.MaliciousIps;
import sbnz.integracija.siem_center.facts.OperatingSystem;
import sbnz.integracija.siem_center.facts.Risk;
import sbnz.integracija.siem_center.facts.RiskType;
import sbnz.integracija.siem_center.facts.User;

@Service
public class SiemCenterService {
	private static Logger log = LoggerFactory.getLogger(SiemCenterService.class);

	private final KieContainer kieContainer;
	
	//cuva listu malicioznih ip adresa
	private MaliciousIps maliciousIps;

	@Autowired
	public SiemCenterService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	
	public Log simulate(){
		KieSession kieSession = kieContainer.newKieSession();
		
		//test za pojavu error log-a
		Log errorLog = new Log("Login", new Machine("192.168.20.60", OperatingSystem.Windows), new User("username1",new Risk("condition1", RiskType.Low),LocalDateTime.parse("2019-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)),LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),"wrong password",LogStatus.Error);
		kieSession.insert(errorLog);
		kieSession.fireAllRules();
		kieSession.dispose();
		return errorLog;

	
	}

}
