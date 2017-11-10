package ca.ucalgary.seng300.a2;


import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.Display;
import org.lsmr.vending.hardware.DisplayListener;
import org.lsmr.vending.hardware.VendingMachine;

public class MyDisplayListener implements DisplayListener{


	private VendingMachine vm;
	private EventWriter ew;
	private Logic logic;
	
	public MyDisplayListener (VendingMachine vm, EventWriter ew, Logic logic) {
		this.ew = ew;
		this.vm = vm;
		this.logic = logic;
	}
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageChange(Display display, String oldMessage, String newMessage) {
		System.out.println(newMessage);
		ew.logEvent("\"" + newMessage + "\" was displayed");
	}

}
