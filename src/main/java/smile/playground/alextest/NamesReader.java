package smile.playground.alextest;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class NamesReader {
	String fileName = null;

	public NamesReader(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public String[] readNames() throws Exception {

		InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
		String fileLines = IOUtils.toString(in, StandardCharsets.UTF_8);
		String[] tokens = fileLines.split("\r?\n|\r");

		return tokens;
	}

}
