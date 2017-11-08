package ca.ucalgary.seng300.a2;



import org.lsmr.vending.PopCan;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.PopCanRack;
import org.lsmr.vending.hardware.PopCanRackListener;

public class PCRListener implements PopCanRackListener {
	private boolean isEmpty = false;

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
			System.out.print(popCan.getName() + " was vended.\n");
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
