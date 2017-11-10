package ca.ucalgary.seng300.a2;



import org.lsmr.vending.PopCan;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.PopCanRack;
import org.lsmr.vending.hardware.PopCanRackListener;
import org.lsmr.vending.hardware.VendingMachine;

public class PCRListener implements PopCanRackListener {
	private boolean isEmpty = false;
	private VendingMachine vm;
	private EventWriter ew;
	private Logic logic;
	
	public PCRListener (VendingMachine vm, EventWriter ew, Logic logic) {
		this.ew = ew;
		this.vm = vm;
		this.logic = logic;
	}

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void popCanAdded(PopCanRack popCanRack, PopCan popCan) {
	}

	@Override
	public void popCanRemoved(PopCanRack popCanRack, PopCan popCan) {
		if(!isEmpty) {
			ew.logEvent(popCan.getName() + " removed from rack.\n");
		}
	}

	@Override
	public void popCansFull(PopCanRack popCanRack) {
	}

	@Override
	public void popCansEmpty(PopCanRack popCanRack) {
		isEmpty = true;
	}

	@Override
	public void popCansLoaded(PopCanRack rack, PopCan... popCans) {
	}

	@Override
	public void popCansUnloaded(PopCanRack rack, PopCan... popCans) {
	}

}
