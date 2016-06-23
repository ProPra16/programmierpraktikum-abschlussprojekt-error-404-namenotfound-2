import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileCreator {
	
	public static void createFile(String text, String fileName) {
		try (PrintStream out = new PrintStream(new FileOutputStream(fileName))) {
		    out.print(text);
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}