package sbnz.integracija.siem_center;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SIemCenterController {
	private static Logger log = LoggerFactory.getLogger(SIemCenterController.class);

	private final SiemCenterService siemCenterService;

	@Autowired
	public SIemCenterController(SiemCenterService siemCenterService) {
		this.siemCenterService = siemCenterService;
	}
}
