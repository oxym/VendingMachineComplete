
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.Display;
import org.lsmr.vending.hardware.DisplayListener;

public class DListener implements DisplayListener{

	private EventWriter e;
	public DListener (EventWriter e) {
		this.e = e;
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
		e.logEvent("\"" + newMessage + "\" was displayed");
	}

}
