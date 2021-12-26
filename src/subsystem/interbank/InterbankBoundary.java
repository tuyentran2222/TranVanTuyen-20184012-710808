package subsystem.interbank;

import java.util.logging.Logger;

import common.exception.UnrecognizedException;
import utils.API;
import utils.Configs;
import utils.Utils;

public class InterbankBoundary {
	private static Logger LOGGER = Utils.getLogger(InterbankSubsystemController.class.getName());
	String query(String url, String data) {
		LOGGER.info(url);
		LOGGER.info(data);
		String response = null;
		try {
			response = API.post(url, data, Configs.TOKEN);
			LOGGER.info(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
