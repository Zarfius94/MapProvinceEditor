package main;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class provides help functions to streamline and simplify the code.
 * @author Zarfius
 * @version 1.0
 *
 */
public final class Helper {

	public static void writeLineBufferedWriter(BufferedWriter writer, String line) {	
		try {
			writer.write(line);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
