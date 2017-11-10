package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.DeliveryChute;
import org.lsmr.vending.hardware.DeliveryChuteListener;
import org.lsmr.vending.hardware.VendingMachine;

public class DeliveryListener implements DeliveryChuteListener {
	private boolean isFull = false;
	
	private VendingMachine vm;
	private EventWriter ew;
	private Logic logic;
	
	public DeliveryListener(VendingMachine vm, EventWriter ew, Logic logic) {
		this.vm = vm;
		this.ew = ew;
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
	public void itemDelivered(DeliveryChute chute) {
		ew.logEvent("Pop can vended");
		vm.getDisplay().display("Thank you for your purchase!");
	}

	@Override
	public void doorOpened(DeliveryChute chute) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doorClosed(DeliveryChute chute) {
		// TODO Auto-generated method stub
		isFull = false;
	}

	@Override
	public void chuteFull(DeliveryChute chute) {
		// TODO Auto-generated method stub
		isFull = true;
	}



}
