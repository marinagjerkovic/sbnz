package sbnz.integracija.siem_center;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.siem_center.facts.Log;
import sbnz.integracija.siem_center.factsDTO.AlarmDTO;
import sbnz.integracija.siem_center.factsDTO.LogDTO;

@RestController
public class SIemCenterController {
	private static Logger log = LoggerFactory.getLogger(SIemCenterController.class);

	private final SiemCenterService siemCenterService;

	@Autowired
	public SIemCenterController(SiemCenterService siemCenterService) {
		this.siemCenterService = siemCenterService;
	}
	
	
	@RequestMapping(value="/simulateLoginErrorSameMachine", method=RequestMethod.GET)
	public void simulateLoginErrorSameMachine(){
		log.debug("Simulating log");
		
		siemCenterService.simulateLoginErrorSameMachine();
	}
	
	@RequestMapping(value="/simulateLoginErrorSameUsername", method=RequestMethod.GET)
	public void simulateLoginErrorSameUsername(){
		log.debug("Simulating log");

		siemCenterService.simulateLoginErrorSameUsername();
	}
	
	@RequestMapping(value="/simulateLoginAttemptAfter90InactiveDays", method=RequestMethod.GET)
	public Log simulateLoginAttemptAfter90InactiveDays(){
		log.debug("Simulating log");

		siemCenterService.simulateLoginAttemptAfter90InactiveDays();
		return null;
	}
	
	@RequestMapping(value="/simulate15FailedLoginsSameIp", method=RequestMethod.GET)
	public Log simulate15FailedLoginsSameIp(){
		log.debug("Simulating log");

		siemCenterService.simulate15FailedLoginsSameIp();
		return null;
	}
	
	@RequestMapping(value="/simulateIpBecomingMalicious", method=RequestMethod.GET)
	public Log simulateIpBecomingMalicious(){
		log.debug("Simulating log");

		siemCenterService.simulateIpBecomingMalicious();
		return null;
	}
	
	@RequestMapping(value="/simulateUserLoginFromTwoDifferentIps", method=RequestMethod.GET)
	public Log simulateUserLoginFromTwoDifferentIps(){
		log.debug("Simulating log");

		siemCenterService.simulateUserLoginFromTwoDifferentIps();
		return null;
	}
	
	@RequestMapping(value="/simulateVirusThreatNotEliminatedWithinHour", method=RequestMethod.GET)
	public Log simulateVirusThreatNotEliminatedWithinHour(){
		log.debug("Simulating log");

		siemCenterService.simulateVirusThreatNotEliminatedWithinHour();
		return null;
	}
	
	@RequestMapping(value="/simulateVirusThreatEliminatedWithinHour", method=RequestMethod.GET)
	public Log simulateVirusThreatEliminatedWithinHour(){
		log.debug("Simulating log");

		siemCenterService.simulateVirusThreatEliminatedWithinHour();
		return null;
	}
	
	@RequestMapping(value="/simulateUserInfoChangeAfter5LoginErrors", method=RequestMethod.GET)
	public Log simulateUserInfoChangeAfter5LoginErrors (){
		log.debug("Simulating log");

		siemCenterService.simulateUserInfoChangeAfter5LoginErrors();
		return null;
	}
	
	@RequestMapping(value="/simulate7VirusThreatsSameMachine", method=RequestMethod.GET)
	public Log simulate7VirusThreatsSameMachine (){
		log.debug("Simulating log");

		siemCenterService.simulate7VirusThreatsSameMachine();
		return null;
	}
	
	@RequestMapping(value="/simulateLoginFromMaliciousIp", method=RequestMethod.GET)
	public Log simulateLoginFromMaliciousIp (){
		log.debug("Simulating log");

		siemCenterService.simulateLoginFromMaliciousIp();
		return null;
	}
	
	@RequestMapping(value="/simulateDosAttack", method=RequestMethod.GET)
	public Log simulateDosAttack (){
		log.debug("Simulating log");

		siemCenterService.simulateDosAttack();
		return null;
	}
	
	@RequestMapping(value="/simulateUser6Alarms6Months3Parts", method=RequestMethod.GET)
	public Log simulateUser6Alarms6Months3Parts (){
		log.debug("Simulating log");

		siemCenterService.simulateUser6Alarms6Months3Parts();
		return null;
	}
	
	@RequestMapping(value="/generateUsersWithOver6Alarms", method=RequestMethod.GET)
	public Object generateUsersWithOver6Alarms (){
		log.debug("Simulating log");

		Object list = siemCenterService.generateUsersWithOver6Alarms();
		return list;
	}
	
	@RequestMapping(value="/generateUsersWithOver10AntivirusAlarms", method=RequestMethod.GET)
	public Object generateUsersWithOver10AntivirusAlarms (){
		log.debug("Simulating log");

		Object list = siemCenterService.generateUsersWithOver10AntivirusAlarms();
		return list;
	}
	
	@RequestMapping(value="/generateInfSys", method=RequestMethod.GET)
	public Object generateInfSys (){
		log.debug("Simulating log");

		Object list = siemCenterService.generateInfSys();
		return list;
	}
	
	
	
	@RequestMapping(path="/createLog", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> createLog(@RequestBody LogDTO newLog){
		if(siemCenterService.createLog(newLog)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(path= "/getAlarms" ,method=RequestMethod.GET)
	public ResponseEntity<List<AlarmDTO>> getAlarms(){
		List<AlarmDTO> alarmsDTO = siemCenterService.getAllAlarms();
		return new ResponseEntity<List<AlarmDTO>>(alarmsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(path= "/getLogs" ,method=RequestMethod.GET)
	public ResponseEntity<List<LogDTO>> getLogs(){
		List<LogDTO> logsDTO = siemCenterService.getAllLogs();
		return new ResponseEntity<List<LogDTO>>(logsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(path= "/getLogsInRuleEngine" ,method=RequestMethod.GET)
	public ResponseEntity<List<LogDTO>> getLogsInRuleEngine(){
		List<LogDTO> logsDTO = siemCenterService.getAllLogsInRuleEngine();
		return new ResponseEntity<List<LogDTO>>(logsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(path= "/threatTemplate/{noOfThreats}/{periodOfTime}" ,method=RequestMethod.POST)
	public ResponseEntity<String> threatTemplate(@PathVariable String noOfThreats, @PathVariable String periodOfTime){
		boolean ruleInserted = siemCenterService.threatTemplate(noOfThreats, periodOfTime);
		if (ruleInserted){
			return new ResponseEntity<String>(HttpStatus.OK);
		}else{
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(path= "/logTemplate/{type}/{status}/{ip}/{username}/{informationSystem}/{numberOfLogs}/{numberOfDays}/{alarmType}){" ,method=RequestMethod.POST)
	public ResponseEntity<String> logTemplate(@PathVariable String type, @PathVariable String status, @PathVariable String ip, @PathVariable String username, @PathVariable String informationSystem, @PathVariable String numberOfLogs, @PathVariable String numberOfDays, @PathVariable String alarmType){
		boolean ruleInserted = siemCenterService.logTemplate(type, status, ip, username, informationSystem, numberOfLogs, numberOfDays, alarmType);
		if (ruleInserted){
			return new ResponseEntity<String>(HttpStatus.OK);
		}else{
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
}
