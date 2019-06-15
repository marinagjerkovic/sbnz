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
import sbnz.integracija.siem_center.facts.LogType;
import sbnz.integracija.siem_center.facts.LogStatus;
import sbnz.integracija.siem_center.facts.Machine;
import sbnz.integracija.siem_center.facts.MaliciousIps;
import sbnz.integracija.siem_center.facts.OperatingSystem;
import sbnz.integracija.siem_center.facts.Risk;
import sbnz.integracija.siem_center.facts.User;

@Service
public class SiemCenterService {
	private static Logger log = LoggerFactory.getLogger(SiemCenterService.class);

	private final KieContainer kieContainer;

	@Autowired
	public SiemCenterService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	
	public Log simulate(){
		KieSession kieSession = kieContainer.newKieSession();
		
		//test za pojavu error log-a
		/*
		Log errorLog = new Log(LogType.Login, new Machine("192.168.20.60", false, OperatingSystem.Windows), new User("username1", Risk.Low,LocalDateTime.parse("2019-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)),LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),"wrong password",LogStatus.Error);
		kieSession.insert(errorLog);
		kieSession.fireAllRules();
		kieSession.dispose();
		return errorLog;
		*/
	
		/*
		//simulations
		//login error on same machine
		Log logError1 = new Log (LogType.Login, new Machine ("192.168.20.60", false, OperatingSystem.Windows), new User("username1", Risk.Low,LocalDateTime.parse("2019-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)),LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);
		Log logError2 = new Log (LogType.Login, new Machine ("192.168.20.60", false, OperatingSystem.Windows), new User("username2", Risk.Low,LocalDateTime.parse("2019-04-08T11:12:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)),LocalDateTime.parse("2019-04-23T11:12:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);
		
		//login error with same username
		User us = new User("username1", Risk.Low,LocalDateTime.parse("2019-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Log logError1 = new Log (LogType.Login, new Machine ("192.168.20.60", false, OperatingSystem.Windows), us, LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);
		Log logError2 = new Log (LogType.Login, new Machine ("192.168.20.70", false, OperatingSystem.Windows), us, LocalDateTime.parse("2019-04-23T11:12:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);		
		*/
		
		//90 days inactive
		User us = new User("username1", Risk.Low, LocalDateTime.parse("2018-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Log logError1 = new Log (LogType.Login, new Machine ("192.168.20.60", false, OperatingSystem.Windows), us, LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "attemted login", LogStatus.Warning);

		kieSession.insert(logError1);
		kieSession.fireAllRules();
		kieSession.dispose();
		return logError1;
	}
}
