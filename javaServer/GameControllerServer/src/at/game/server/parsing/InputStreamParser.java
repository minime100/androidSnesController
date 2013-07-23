package at.game.server.parsing;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class InputStreamParser {
	Logger logger = Logger.getLogger(getClass());

	public String parseFromInputStream(InputStream in) throws IOException {
		logger.trace("parsing...");
		String parsedString = IOUtils.toString(in, "UTF-8");
		logger.debug("parsed message: " + parsedString);
		return parsedString;
	}

}
