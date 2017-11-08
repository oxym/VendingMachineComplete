package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.DeliveryChute;
import org.lsmr.vending.hardware.DeliveryChuteListener;
import org.lsmr.vending.hardware.VendingMachine;

public class DeliveryListener implements DeliveryChuteListener {
	private boolean isFull = false;
	
	private VendingMachine vm;
	
	public DeliveryListener(VendingMachine vm) {
		this.vm = vm;
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
		// TODO Auto-generated method stub
		vm.getDisplay().display("Pop can vended");
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
