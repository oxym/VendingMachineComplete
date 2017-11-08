
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventWriter {
	private BufferedWriter writer;
	private Calendar cal = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	public EventWriter(String file) {
		 try {
			writer = new BufferedWriter(new FileWriter (file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logEvent(String event) {
		try {
			writer.write(sdf.format("(" + cal.getTime()) + ") " + event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeWriter(){
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
