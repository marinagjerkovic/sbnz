package sbnz.integracija.siem_center;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	@RequestMapping(value="/simulateLog", method=RequestMethod.GET)
	public Log simulateLog(){
		log.debug("Simulating log");
		
		Log l = siemCenterService.simulate();
		return l;
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
	/*
	@RequestMapping(path= "/getLogsInRuleEngine" ,method=RequestMethod.GET)
	public ResponseEntity<List<LogDTO>> getLogsInRuleEngine(){
		List<LogDTO> logsDTO = siemCenterService.getAllLogsInRuleEngine();
		return new ResponseEntity<List<LogDTO>>(logsDTO, HttpStatus.OK);
	}*/
	
	
}
