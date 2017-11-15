package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.IndicatorLight;
import org.lsmr.vending.hardware.IndicatorLightListener;
import org.lsmr.vending.hardware.VendingMachine;

public class OutOfOrderLightListener implements IndicatorLightListener  {

	private VendingMachine vm;
	private EventWriter ew;
	private Logic logic;

	public OutOfOrderLightListener(VendingMachine vm, EventWriter ew, Logic logic) {
		this.vm = vm;
		this.ew = ew;
		this.logic = logic;
	}
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		//Does nothing yet
		
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		//Does nothing yet
		
	}

	@Override
	public void activated(IndicatorLight light) {
		vm.getDisplay().display("Out of Order");
		logic.setOutofOrder();
		ew.logEvent("Out of order light on.");
		
	}

	@Override
	public void deactivated(IndicatorLight light) {
		//Don't have to worry about this yet
		
	}
	
}
