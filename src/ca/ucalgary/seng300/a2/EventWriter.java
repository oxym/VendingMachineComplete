package ca.ucalgary.seng300.a2;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventWriter {
	private BufferedWriter writer;
    private String sdf;
	public EventWriter(String file) {
		 try {
			writer = new BufferedWriter(new FileWriter (file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logEvent(String event) {
		try {
			sdf =  new SimpleDateFormat("HH:mm:ss").format(new Date());
			writer.write("(" + sdf + ") " + event + "\n");
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
