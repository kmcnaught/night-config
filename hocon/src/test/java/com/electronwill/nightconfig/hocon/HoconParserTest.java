package com.electronwill.nightconfig.hocon;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TheElectronWill
 */
class HoconParserTest {

	@Test
	public void readWriteReadAgain() {
		File file = new File("test.hocon");
		CommentedConfig parsed = new HoconParser().parse(file, FileNotFoundAction.THROW_ERROR);

		System.out.println("--- parsed --- \n" + parsed);
		System.out.println("--------------------------------------------");
		java.io.StringWriter sw = new StringWriter();
		HoconWriter writer = new HoconWriter();
		writer.write(parsed, sw);
		System.out.println("--- written --- \n" + sw);
		System.out.println("--------------------------------------------");

		CommentedConfig reparsed = new HoconParser().parse(new StringReader(sw.toString()));
		System.out.println("--- reparsed --- \n" + reparsed);
		assertEquals(parsed, reparsed);
	}

}