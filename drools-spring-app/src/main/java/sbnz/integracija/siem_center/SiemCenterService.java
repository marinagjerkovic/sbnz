package sbnz.integracija.siem_center;

import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiemCenterService {
	private static Logger log = LoggerFactory.getLogger(SiemCenterService.class);

	private final KieContainer kieContainer;

	@Autowired
	public SiemCenterService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}

}
