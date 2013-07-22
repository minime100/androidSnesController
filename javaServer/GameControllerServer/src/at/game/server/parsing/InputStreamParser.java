package at.game.server.parsing;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class InputStreamParser {

	public String parseFromInputStream(InputStream in) throws IOException {
		return IOUtils.toString(in, "UTF-8");
	}

}
