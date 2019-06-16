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
import sbnz.integracija.siem_center.facts.LogType;
import sbnz.integracija.siem_center.facts.Machine;
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
		Machine machine = new Machine("192.168.20.60", false, OperatingSystem.Windows);
		Log logError1 = new Log (LogType.Login, machine, new User("username1", Risk.Low,LocalDateTime.parse("2019-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)),LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);
		Log logError2 = new Log (LogType.Login, machine, new User("username2", Risk.Low,LocalDateTime.parse("2019-04-08T11:12:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)),LocalDateTime.parse("2019-04-23T11:12:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);
		
		//login error with same username
		User us = new User("username1", Risk.Low,LocalDateTime.parse("2019-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Log logError1 = new Log (LogType.Login, new Machine ("192.168.20.60", false, OperatingSystem.Windows), us, LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);
		Log logError2 = new Log (LogType.Login, new Machine ("192.168.20.70", false, OperatingSystem.Windows), us, LocalDateTime.parse("2019-04-23T11:12:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);		
		
		
		//90 days inactive
		User us = new User("username1", Risk.Low, LocalDateTime.parse("2018-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Log logError1 = new Log (LogType.Login, new Machine ("192.168.20.60", false, OperatingSystem.Windows), us, LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "login attempt", LogStatus.Warning);
		
		//15+ login errors from the same ip address in the last 5 days
		Machine machine = new Machine("192.168.20.60", false, OperatingSystem.Windows);
		int dat = -1;
		for (int i = 0;i<=16;i++) {
			if (dat<9) {
				dat++;
			}
			Log logError = new Log(LogType.Login, machine, new User("username"+i, Risk.Low, LocalDateTime.now()), LocalDateTime.parse("2019-06-14T11:1"+dat+":00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "failed login", LogStatus.Error);
			kieSession.insert(logError);
		}
		
		//two successful logins of the same user on two different machines in less then 10 seconds
		User us = new User("username1", Risk.Low,LocalDateTime.parse("2019-04-08T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Log log1 = new Log(LogType.Login, new Machine ("192.168.20.60", false, OperatingSystem.Windows), us, LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "login success", LogStatus.Ok);
		Log log2 = new Log(LogType.Login, new Machine ("192.168.20.70", false, OperatingSystem.Windows), us, LocalDateTime.parse("2019-04-23T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "login success", LogStatus.Ok);
		
		//antivirus threat not eliminated within hour
		Machine machine = new Machine ("192.168.20.60", false, OperatingSystem.Windows);
		Log log1 = new Log(1, LogType.VirusThreat, LogStatus.Ok, machine, null, LocalDateTime.parse("2019-06-16T11:11:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "threat detected!");
		Log log2 = new Log(2, LogType.ThreatEliminated, LogStatus.Ok, machine, null, LocalDateTime.parse("2019-06-16T12:21:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), "thread removed;\nreporting log id: 1");
		
		
		//profile info changed after login fails
		Machine machine = new Machine ("192.168.20.60", false, OperatingSystem.Windows);
		kieSession.insert(machine);
		for (int i =0;i<6;i++) {
			User us = new User(i, "username"+i, "password", Risk.Low, LocalDateTime.now());
			Log log = new Log(i, LogType.Login, LogStatus.Error, machine, us, LocalDateTime.now(), "login error");
			kieSession.insert(log);
		}
		User us = new User(10, "username"+10, "password", Risk.Low, LocalDateTime.now());
		Log log1 = new Log(10, LogType.Login, LogStatus.Ok, machine, us, LocalDateTime.now(), "login success!");
		Log log2 = new Log(11, LogType.Information, LogStatus.Ok, machine, us, LocalDateTime.now(), "profile info changed");
		*/

		Machine machine = new Machine ("192.168.20.60", true, OperatingSystem.Windows);
		User us = new User(10, "username"+10, "password", Risk.Low, LocalDateTime.now());
		Log log1 = new Log(1, LogType.Login, LogStatus.Ok, machine, us, LocalDateTime.now(), "login success!");
		
		
		kieSession.insert(log1);
		//kieSession.insert(log2);
		kieSession.fireAllRules();
		kieSession.dispose();
		return log1;
	}
}
