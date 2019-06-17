package sbnz.integracija.siem_center;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.siem_center.facts.Alarm;
import sbnz.integracija.siem_center.facts.AlarmType;
import sbnz.integracija.siem_center.facts.InformationSystem;
import sbnz.integracija.siem_center.facts.Log;
import sbnz.integracija.siem_center.facts.LogStatus;
import sbnz.integracija.siem_center.facts.LogType;
import sbnz.integracija.siem_center.facts.Machine;
import sbnz.integracija.siem_center.facts.OperatingSystem;
import sbnz.integracija.siem_center.facts.Risk;
import sbnz.integracija.siem_center.facts.User;
import sbnz.integracija.siem_center.factsDTO.AlarmDTO;
import sbnz.integracija.siem_center.factsDTO.LogDTO;
import sbnz.integracija.siem_center.repositories.AlarmRepository;
import sbnz.integracija.siem_center.repositories.LogRepository;
import sbnz.integracija.siem_center.repositories.MachineRepository;
import sbnz.integracija.siem_center.repositories.UserRepository;



@Service
public class SiemCenterService {
	private static Logger log = LoggerFactory.getLogger(SiemCenterService.class);

	private final KieContainer kieContainer;
	
	@Autowired
	AlarmRepository alarmRepository;
	
	@Autowired
	LogRepository logRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MachineRepository machineRepository;
	
	

	@Autowired
	public SiemCenterService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
		
	}
	
	
	
	public Log simulate(){
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.setGlobal("service", this);
		
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
		/*
		Machine machine = new Machine ("192.168.20.60", false, OperatingSystem.Windows);
		Machine malMachine = new Machine ("192.168.20.80", true, OperatingSystem.Windows);
		//User us = new User(10, "username"+10, "password", Risk.Low, LocalDateTime.now());
		//Log log1 = new Log(1, LogType.Login, LogStatus.Ok, machine, us, LocalDateTime.now(), "login success!");
		
		User us = new User(1L, "username1", "password1", Risk.High, LocalDateTime.now(), true);
		User us2 = new User(2L, "username2", "password2", Risk.Low, LocalDateTime.now(), false);
		User us3 = new User(3L, "username3", "password3", Risk.Moderate, LocalDateTime.now(), true);
		User us4 = new User(4L, "username4", "password4", Risk.Extreme, LocalDateTime.now(), false);
		
		userRepository.save(us);
		userRepository.save(us2);
		userRepository.save(us3);
		userRepository.save(us4);
		
		Alarm highAlarm = new Alarm(1L, AlarmType.Antivirus, us3, machine, LocalDateTime.now(), InformationSystem.SecuritySystem);
		Alarm moderateAlarm = new Alarm(2L, AlarmType.Antivirus, us2, machine, LocalDateTime.now(), InformationSystem.SecuritySystem);
		//Alarm extremeAlarm = new Alarm(3L, AlarmType.Antivirus, us, malMachine, LocalDateTime.now(), InformationSystem.SecuritySystem);
		
		Log log1 = new Log(1L, LogType.Login, LogStatus.Ok, malMachine, us, LocalDateTime.now(), "login", InformationSystem.PaymentSystem);
		
		kieSession.insert(us);
		kieSession.insert(us2);
		kieSession.insert(us3);
		kieSession.insert(us4);
		
		kieSession.insert(highAlarm);
		kieSession.insert(moderateAlarm);
		*/
	
		Machine machine = new Machine ("192.168.20.60", false, OperatingSystem.Windows);
		User us = userRepository.findByUsername("username4");
		Log log1 = new Log(2L, LogType.Login, LogStatus.Error, machine, us, LocalDateTime.now().minusHours(1).minusMinutes(1), "login failed", InformationSystem.PaymentSystem);
		Log log2 = new Log(3L, LogType.Login, LogStatus.Error, machine, us, LocalDateTime.now().minusHours(1).minusMinutes(1), "login failed", InformationSystem.PaymentSystem);
		
		Log log3 = new Log(1L, LogType.Login, LogStatus.Ok, machine, us, LocalDateTime.now().minusHours(1), "login success", InformationSystem.PaymentSystem);

		Log log4 = new Log(4L, LogType.Information, LogStatus.Ok, machine, us, log3.getTime().plusSeconds(10), "profile info changed", InformationSystem.PaymentSystem);
		
		Alarm alarm = new Alarm(1L, AlarmType.Antivirus, us, machine, LocalDateTime.now().minusMonths(2), InformationSystem.PaymentSystem);
		
		
		
		
		//Log log1 = new Log();

		kieSession.insert(machine);
		kieSession.insert(us);
		kieSession.insert(alarm);
		kieSession.insert(log1);
		kieSession.insert(log2);
		kieSession.insert(log3);
		kieSession.insert(log4);
		//kieSession.insert(log2);
		kieSession.fireAllRules();
		kieSession.dispose();
		return log1;
	}
	
	public List<LogDTO> getAllLogs(){
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		ArrayList<Log> logs = (ArrayList<Log>) logRepository.findAll();
		for (Log l:logs){
			LogDTO logDTO = new LogDTO();
			logDTO.setId(l.getId());
			logDTO.setMachineId(l.getMachine().getId());
			logDTO.setStatus(l.getStatus());
			logDTO.setText(l.getText());
			logDTO.setTime(l.getTime());
			logDTO.setType(l.getType());
			if (l.getUser()!=null){
				logDTO.setUserUsername(l.getUser().getUsername());
			}			
			logsDTO.add(logDTO);
		}
		return logsDTO;
	}
	/*
	public List<LogDTO> getAllLogsInRuleEngine(){
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		QueryResults results = kieSession.getQueryResults( "getObjectsOfLog" ); 
		for ( QueryResultsRow row : results ) {
		    Log log = (Log) row.get( "$result" ); //you can retrieve all the bounded variables here
		    LogDTO logDTO = new LogDTO();
		    logDTO.setId(log.getId());
		    logDTO.setMachineId(log.getMachine().getId());
		    logDTO.setStatus(log.getStatus());
		    logDTO.setText(log.getText());
		    logDTO.setTime(log.getTime());
		    logDTO.setType(log.getType());
		    logDTO.setUserUsername(log.getUser().getUsername());
		    logsDTO.add(logDTO);
		}
		return logsDTO;
	}*/
	
	public List<AlarmDTO> getAllAlarms(){
		List<AlarmDTO> alarmsDTO = new ArrayList<AlarmDTO>();
		ArrayList<Alarm> alarms = (ArrayList<Alarm>) alarmRepository.findAll();
		for (Alarm a:alarms){
			AlarmDTO alarmDTO = new AlarmDTO();
			alarmDTO.setAlarmType(a.getAlarmType());
			alarmDTO.setId(a.getId());
			alarmDTO.setMachineId(a.getMachine().getId());
			alarmDTO.setTime(a.getTime());
			if (a.getUser()!=null){
				alarmDTO.setUserUsername(a.getUser().getUsername());
			}
			alarmsDTO.add(alarmDTO);
			
		}
		return alarmsDTO;
	}
	
	public boolean createLog(LogDTO logDTO){
		Log log = new Log();
		Machine machine = machineRepository.getOne(logDTO.getMachineId());
		if (machine!=null){
			log.setMachine(machine);
		}else{
			return false;
		}
		log.setStatus(logDTO.getStatus());
		log.setText(logDTO.getText());
		log.setTime(logDTO.getTime());
		log.setType(logDTO.getType());
		User user = userRepository.findByUsername(logDTO.getUserUsername());
		if (user!=null){
			log.setUser(user);
		}
		logRepository.save(log);
		return true;
		
	}
	
	public void setUserRisk(User user, Risk newRisk) {
		user.setRisk(newRisk);
		userRepository.save(user);
	}
	
	public void addIpToMalicious(Machine machine) {
		machine.setMaliciousIp(true);
		machineRepository.save(machine);
	}
	
}
