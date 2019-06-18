package sbnz.integracija.siem_center;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
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
	private KieSession kieSession;
	
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
		this.kieSession = kieContainer.newKieSession();
		kieSession.setGlobal("service", this);
		//for generating lists
		kieSession.setGlobal("usersAnyAlarms", new ArrayList<User>());
		kieSession.setGlobal("usersAntivirusAlarms", new ArrayList<User>());
		kieSession.setGlobal("infSys", new ArrayList<InformationSystem>());
		
	}
	
	public void seed() {
		
		//users
		User us1 = new User(1L, "username1", "password1", Risk.High, LocalDateTime.now(), true);
		User us2 = new User(2L, "username2", "password2", Risk.Low, LocalDateTime.now(), false);
		User us3 = new User(3L, "username3", "password3", Risk.Moderate, LocalDateTime.now(), true);
		User us4 = new User(4L, "username4", "password4", Risk.Extreme, LocalDateTime.now(), false);
		User us5 = new User(5L, "username5", "password5", Risk.High, LocalDateTime.now(), true);
		User us6 = new User(6L, "username6", "password6", Risk.Low, LocalDateTime.now(), false);
		User us7 = new User(7L, "username7", "password7", Risk.Moderate, LocalDateTime.now(), true);
		User us8 = new User(8L, "username8", "password8", Risk.Extreme, LocalDateTime.now(), false);
		User us9 = new User(9L, "username9", "password9", Risk.High, LocalDateTime.now(), true);
		User us10 = new User(10L, "username10", "password10", Risk.Low, LocalDateTime.now(), false);
		User us11 = new User(11L, "username11", "password11", Risk.Moderate, LocalDateTime.now(), true);
		User us12 = new User(12L, "username12", "password12", Risk.Extreme, LocalDateTime.now(), false);
		
		
		userRepository.save(us1);
		userRepository.save(us2);
		userRepository.save(us3);
		userRepository.save(us4);
		userRepository.save(us5);
		userRepository.save(us6);
		userRepository.save(us7);
		userRepository.save(us8);
		userRepository.save(us9);
		userRepository.save(us10);
		userRepository.save(us11);
		userRepository.save(us12);
		
		//machines
		Machine machine1 = new Machine(1L, "192.168.20.60", false, OperatingSystem.Windows);
		Machine machine2 = new Machine(2L, "192.168.20.70", false, OperatingSystem.Windows);
		Machine machine3 = new Machine(3L, "192.168.20.80", false, OperatingSystem.Windows);
		Machine machine4 = new Machine(4L, "192.168.20.90", true, OperatingSystem.Windows);
		
		machineRepository.save(machine1);
		machineRepository.save(machine2);
		machineRepository.save(machine3);
		machineRepository.save(machine4);
	}
	
	public void simulateLoginErrorSameMachine() {
		//get non-malicious machine 
		Machine machine = machineRepository.getOne(1L);
		//two different users
		User us1 = userRepository.getOne(1L);
		User us2 = userRepository.getOne(2L);
		Log logError1 = new Log (LogType.Login, LogStatus.Error, machine, us1, LocalDateTime.now().minusSeconds(10), "failed login", InformationSystem.PaymentSystem);
		Log logError2 = new Log (LogType.Login, LogStatus.Error, machine, us2, LocalDateTime.now(), "failed login", InformationSystem.PaymentSystem);
		logError1 = logRepository.save(logError1);
		logError2 = logRepository.save(logError2);
		
		this.kieSession.insert(machine);
		this.kieSession.insert(logError1);
		this.kieSession.insert(logError2);
		this.kieSession.fireAllRules();
	}
	
	public void simulateLoginErrorSameUsername() {
		//get user 
		User us = userRepository.getOne(1L);
		//machine
		Machine machine = machineRepository.getOne(1L);
		Log logError1 = new Log (LogType.Login, LogStatus.Error, machine, us, LocalDateTime.now().minusSeconds(10), "failed login", InformationSystem.PaymentSystem);
		Log logError2 = new Log (LogType.Login, LogStatus.Error, machine, us, LocalDateTime.now(), "failed login", InformationSystem.PaymentSystem);
		logError1 = logRepository.save(logError1);
		logError2 = logRepository.save(logError2);
		
		this.kieSession.insert(us);
		this.kieSession.insert(logError1);
		this.kieSession.insert(logError2);
		this.kieSession.fireAllRules();
	}
	
	public void simulateLoginAttemptAfter90InactiveDays(){
		//get user 
		User us = userRepository.getOne(1L);
		us.setLastActivity(LocalDateTime.now().minusDays(100));
		us = userRepository.save(us);
		//machine
		Machine machine = machineRepository.getOne(1L);
		Log logError1 = new Log (LogType.Login, LogStatus.Error, machine, us, LocalDateTime.now(), "failed login", InformationSystem.PaymentSystem);
		logError1 = logRepository.save(logError1);
		
		this.kieSession.insert(us);
		this.kieSession.insert(logError1);
		this.kieSession.fireAllRules();
	}
	
	public void simulate15FailedLoginsSameIp(){
		//get non-malicious machine 
		Machine machine = machineRepository.getOne(1L);
		this.kieSession.insert(machine);
		
		//different users would also work
		User us = userRepository.findByUsername("username2");
		for (int i = 0; i<15;i++){
			Log logError = new Log (LogType.Login, LogStatus.Error, machine, us, LocalDateTime.now(), "failed login", InformationSystem.PaymentSystem);
			logError = logRepository.save(logError);
			this.kieSession.insert(logError);
		}
		
		this.kieSession.fireAllRules();
	}
	
	public void simulateUserLoginFromTwoDifferentIps() {
		User us = userRepository.findByUsername("username2");
		this.kieSession.insert(us);
		//get non-malicious machines
		Machine machine1 = machineRepository.getOne(1L);
		Machine machine2 = machineRepository.getOne(2L);
		
		Log log1 = new Log (LogType.Login, LogStatus.Ok, machine1, us, LocalDateTime.now(), "login success", InformationSystem.PaymentSystem);
		//different parts of system
		Log log2 = new Log (LogType.Login, LogStatus.Ok, machine2, us, LocalDateTime.now(), "login success", InformationSystem.PrivacySystem);
		//same part of system
		//Log log2 = new Log (LogType.Login, LogStatus.Ok, machine2, us, LocalDateTime.now(), "login success", InformationSystem.PaymentSystem);
		
		log1 = logRepository.save(log1);
		log2 = logRepository.save(log2);
		
		this.kieSession.insert(log1);
		this.kieSession.insert(log2);
		this.kieSession.fireAllRules();
	}
	
	//also, user's risk will change to Moderate/High
	public void simulateVirusThreatNotEliminatedWithinHour() {
		Machine machine = machineRepository.getOne(1L);
		this.kieSession.insert(machine);
		
		//virus found log
		User user = userRepository.findByUsername("username1");
		this.kieSession.insert(user);
		Log log1 = new Log (LogType.VirusThreat, LogStatus.Warning, machine, user, LocalDateTime.now().minusHours(1), "virus detected", InformationSystem.PaymentSystem);
		log1 = logRepository.save(log1);
		this.kieSession.insert(log1);
		
		//virus removed log
		//User user = userRepository.findByUsername("username1");
		//Log log2 = new Log (LogType.ThreatEliminated, LogStatus.Ok, machine, user, LocalDateTime.now().minusMinutes(10), "virus removed; reporting log id: "+log1.getId(), InformationSystem.PaymentSystem);
		//log2 = logRepository.save(log2);
		//this.kieSession.insert(log2);
		
		this.kieSession.fireAllRules();
	}
	
	public void simulateVirusThreatEliminatedWithinHour() {
		Machine machine = machineRepository.getOne(1L);
		this.kieSession.insert(machine);
		
		//virus found log
		User user = userRepository.findByUsername("username8");
		Log log1 = new Log (LogType.VirusThreat, LogStatus.Warning, machine, user, LocalDateTime.now().minusHours(1), "virus detected", InformationSystem.PaymentSystem);
		log1 = logRepository.save(log1);
		this.kieSession.insert(log1);
		
		//virus removed log
		User user2 = userRepository.findByUsername("username1");
		Log log2 = new Log (LogType.ThreatEliminated, LogStatus.Ok, machine, user2, LocalDateTime.now().minusMinutes(10), "virus removed; reporting log id: "+log1.getId(), InformationSystem.PaymentSystem);
		log2 = logRepository.save(log2);
		this.kieSession.insert(log2);
		
		this.kieSession.fireAllRules();
	}
	
	public void simulateUserInfoChangeAfter5LoginErrors() {
		Machine machine = machineRepository.getOne(1L);
		this.kieSession.insert(machine);
		
		for (int i =3;i<=8;i++) {
			User userError = userRepository.findByUsername("username"+i);
			Log errorLog = new Log(LogType.Login, LogStatus.Error, machine, userError, LocalDateTime.now(), "login failed", InformationSystem.PaymentSystem);
			errorLog = logRepository.save(errorLog);
			this.kieSession.insert(errorLog);
		}
		
		
		User user = userRepository.findByUsername("username2");
		Log loginLog = new Log(LogType.Login, LogStatus.Ok, machine, user, LocalDateTime.now(), "login success", InformationSystem.PaymentSystem);
		loginLog = logRepository.save(loginLog);
		Log infoChangedLog = new Log(LogType.Information, LogStatus.Ok, machine, user, LocalDateTime.now(), "profile info changed", InformationSystem.PaymentSystem);
		infoChangedLog = logRepository.save(infoChangedLog);
		
		this.kieSession.insert(loginLog);
		this.kieSession.insert(infoChangedLog);
		this.kieSession.fireAllRules();
	}
	
	public void simulate7VirusThreatsSameMachine() {
		Machine machine = machineRepository.getOne(1L);
		this.kieSession.insert(machine);
		
		//random user 
		User us = userRepository.findByUsername("username8");
		for (int i = 0;i<8;i++) {
			Log errorLog = new Log(LogType.VirusThreat, LogStatus.Warning, machine, us, LocalDateTime.now(), "virus detected", InformationSystem.PaymentSystem);
			errorLog = logRepository.save(errorLog);
			this.kieSession.insert(errorLog);
		}
		
		this.kieSession.fireAllRules();
	}
	
	public void simulateLoginFromMaliciousIp() {
		//get machine with malicious ip
		Machine machine = machineRepository.getOne(4L);
		this.kieSession.insert(machine);
		
		User user = userRepository.findByUsername("username4");
		Log loginLog = new Log (LogType.Login, LogStatus.Ok, machine, user, LocalDateTime.now(), "login success", InformationSystem.PaymentSystem);
		loginLog = logRepository.save(loginLog);
		this.kieSession.insert(loginLog);
		this.kieSession.fireAllRules();
	}
	
	public void simulateIpBecomingMalicious() {
		//getting non-malicious ip
		Machine machine = machineRepository.getOne(1L);
		this.kieSession.insert(machine);
		User user = userRepository.findByUsername("username2");
		for (int i =0;i<30;i++) {
			Log loginLog = new Log(LogType.Login, LogStatus.Error, machine, user, LocalDateTime.now(), "login failed", InformationSystem.PaymentSystem);
			loginLog = logRepository.save(loginLog);
			this.kieSession.insert(loginLog);
		}
		this.kieSession.fireAllRules();
	}
	
	public void simulateUserRiskToLow() {
		//enter username of other users
		User user = userRepository.findByUsername("username2");
		this.kieSession.insert(user);
	}
		
	public void simulateUser6Alarms6Months3Parts() {
		User user = userRepository.findByUsername("username1");
		Machine machine = machineRepository.getOne(1L);
		
		Alarm alarm1 = new Alarm(AlarmType.Antivirus, user, machine,LocalDateTime.now().minusMonths(1), InformationSystem.PaymentSystem);
		Alarm alarm2 = new Alarm(AlarmType.Antivirus, user, machine,LocalDateTime.now().minusMonths(2), InformationSystem.PrivacySystem);
		Alarm alarm3 = new Alarm(AlarmType.Antivirus, user, machine,LocalDateTime.now().minusMonths(3), InformationSystem.SecuritySystem);
		Alarm alarm4 = new Alarm(AlarmType.Antivirus, user, machine,LocalDateTime.now().minusMonths(4), InformationSystem.PaymentSystem);
		Alarm alarm5 = new Alarm(AlarmType.Antivirus, user, machine,LocalDateTime.now().minusMonths(5), InformationSystem.PaymentSystem);
		Alarm alarm6 = new Alarm(AlarmType.Antivirus, user, machine,LocalDateTime.now().minusMonths(1), InformationSystem.PaymentSystem);
		alarm1 = alarmRepository.save(alarm1);
		alarm2 = alarmRepository.save(alarm2);
		alarm3 = alarmRepository.save(alarm3);
		alarm4 = alarmRepository.save(alarm4);
		alarm5 = alarmRepository.save(alarm5);
		alarm6 = alarmRepository.save(alarm6);
		
		this.kieSession.insert(user);
		this.kieSession.insert(alarm1);
		this.kieSession.insert(alarm2);
		this.kieSession.insert(alarm3);
		this.kieSession.insert(alarm4);
		this.kieSession.insert(alarm5);
		this.kieSession.insert(alarm6);

		this.kieSession.fireAllRules();
	}
	
	public void simulateDosAttack() {
		for (int i = 0;i<40;i++) {
			Log log = new Log(LogType.Information, LogStatus.Ok, new Machine(), new User(), LocalDateTime.now(), "text", InformationSystem.PrivacySystem);
			this.kieSession.insert(log);
		}
		for (int i = 0;i<5;i++) {
			Log log = new Log(LogType.Information, LogStatus.Ok, new Machine(), new User(), LocalDateTime.now(), "text", InformationSystem.PaymentSystem);
			this.kieSession.insert(log);
		}
		for (int i = 0;i<5;i++) {
			Log log = new Log(LogType.Login, LogStatus.Ok, new Machine(), new User(), LocalDateTime.now(), "text", InformationSystem.SecuritySystem);
			this.kieSession.insert(log);
		}
		this.kieSession.fireAllRules();
	}
		
	public List<LogDTO> getAllLogs(){
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		ArrayList<Log> logs = (ArrayList<Log>) logRepository.findAll();
		for (Log l:logs){
			LogDTO logDTO = new LogDTO();
			logDTO.setId(l.getId());
			logDTO.setMachineIp(l.getMachine().getIp());
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
	
	public List<LogDTO> getAllLogsInRuleEngine(){
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		QueryResults results = kieSession.getQueryResults( "getAllLogs" ); 
		for ( QueryResultsRow row : results ) {
		    Log log = (Log) row.get( "$log" ); //you can retrieve all the bounded variables here
		    LogDTO logDTO = new LogDTO();
		    logDTO.setId(log.getId());
		    logDTO.setMachineIp(log.getMachine().getIp());
		    logDTO.setStatus(log.getStatus());
		    logDTO.setText(log.getText());
		    logDTO.setTime(log.getTime());
		    logDTO.setType(log.getType());
		    logDTO.setUserUsername(log.getUser().getUsername());
		    logsDTO.add(logDTO);
		}
		return logsDTO;
	}
	
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
	/*
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
		
	}*/
	
	public Object generateUsersWithOver6Alarms() {
		return this.kieSession.getGlobal("usersAnyAlarms");
	}
	
	public Object generateUsersWithOver10AntivirusAlarms() {
		return this.kieSession.getGlobal("usersAntivirusAlarms");
	}
	
	public Object generateInfSys() {
		return this.kieSession.getGlobal("infSys");
	}
	
	public void setUserRisk(User user) {
		userRepository.save(user);
	}
	
	public void addIpToMalicious(Machine machine) {
		machineRepository.save(machine);
	}
	
	public void saveAlarm(Alarm alarm) {
		alarmRepository.save(alarm);
	}
	
  public boolean threatTemplate(String noOfThreats, String periodOfTime) {
		
		InputStream template = SiemCenterService.class.getResourceAsStream("/sbnz/templates/rule_templates.drt");
		
		DataProvider dataProvider = new ArrayDataProvider(new String[][] {
			new String[] {noOfThreats, periodOfTime},
		});		
		
		insertRule(dataProvider, template);
		return true;
	}
	
	
	public boolean logTemplate(String type, String status, String ip, String username, String informationSystem, String numberOfLogs, String numberOfDays, String alarmType){
		InputStream template = SiemCenterService.class.getResourceAsStream("/sbnz/templates/log_rule_templates.drt");
		DataProvider dataProvider = new ArrayDataProvider(new String[][] {
			new String[] {numberOfLogs, numberOfDays, alarmType, username, ip, type, status, informationSystem},
		});		
		
		insertRule(dataProvider, template);
		return true;
	}

	
	
	public void insertRule(DataProvider dataProvider, InputStream template){
		DataProviderCompiler converter = new DataProviderCompiler();
		String ruleContent = converter.compile(dataProvider, template);
		
		System.out.println(ruleContent);
		
		this.kieSession = null;
	    try {
	        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
	        kb.add(ResourceFactory.newByteArrayResource(ruleContent.getBytes("utf-8")), ResourceType.DRL);

	        KnowledgeBuilderErrors errors = kb.getErrors();
	        for (KnowledgeBuilderError error : errors) {
	            System.out.println(error);
	        }
	        InternalKnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
	        kBase.addPackages(kb.getKnowledgePackages());
	        kieSession = kBase.newKieSession();
	        //pozivanje testa
	        

	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } finally {
	        if (kieSession != null)
	            kieSession.dispose();
	    }
	}
  
  public List<LogDTO> searchLogsDatabase(LogDTO searchLog) {
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		ArrayList<Log> logs = (ArrayList<Log>) logRepository.findAll();
		
		LogType type = searchLog.getType();
		LogStatus status = searchLog.getStatus();
		String machineIp = searchLog.getMachineIp();
		String username = searchLog.getUserUsername();
		InformationSystem informationSystem = searchLog.getInformationSystem();
		LocalDateTime time = searchLog.getTime();
		
		System.out.println(logs.size());
		for (Log l:logs){			
			if (type==null){
				type=l.getType();
			}
			if (status==null){
				status=l.getStatus();
			}
			if (machineIp==null){
				machineIp=l.getMachine().getIp();
			}
			if (username==null){
				username=l.getUser().getUsername();
			}
			if (informationSystem==null){
				informationSystem=l.getInformationSystem();
			}
			
			LogDTO logDTO = new LogDTO();
			if (l.getInformationSystem().equals(informationSystem) && l.getMachine().getIp().equals(machineIp) && l.getStatus().equals(status) && l.getTime().equals(time) && l.getType().equals(type) && l.getUser().getUsername().equals(username)){
				logDTO.setId(l.getId());
				logDTO.setInformationSystem(l.getInformationSystem());
				logDTO.setMachineIp(l.getMachine().getIp());
				logDTO.setStatus(l.getStatus());
				logDTO.setText(l.getText());
				logDTO.setTime(l.getTime());
				logDTO.setType(l.getType());
				logDTO.setUserUsername(l.getUser().getUsername());
				logsDTO.add(logDTO);
			}
			
			
		}
		return logsDTO;
	}
	
	
	public List<LogDTO> searchLogsWorkingMemory(LogDTO searchLog) {
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		QueryResults results = kieSession.getQueryResults( "searchLogs", searchLog);

		
		for ( QueryResultsRow row : results ) {
		    Log l = (Log) row.get( "$log" ); 
		    LogDTO logdto = new LogDTO(l);
		    logsDTO.add(logdto);
		}
		return logsDTO;
		
	}

	public List<LogDTO> searchLogsDatabaseRegex(String regexString) {
		//searchovanje logova po regexu iz baze
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		ArrayList<Log> logs = (ArrayList<Log>) logRepository.findAll();
		//dodati proveru za svaki log iz logs da li odgovara regexu
		return logsDTO;
	}

	public List<LogDTO> searchLogsWorkingMemoryRegex(String regexString) {
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		
		QueryResults results = kieSession.getQueryResults( "getLogsRegex", regexString);
		
		for ( QueryResultsRow row : results ) {
		    Log l = (Log) row.get( "$log" ); 
		    LogDTO logdto = new LogDTO(l);
		    logsDTO.add(logdto);
		}
		return logsDTO;
	}
	
}

