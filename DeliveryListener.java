package ca.ucalgary.seng300.a2.logic;

import ca.ucalgary.seng300.a2.hardware.AbstractHardware;
import ca.ucalgary.seng300.a2.hardware.AbstractHardwareListener;
import ca.ucalgary.seng300.a2.hardware.DeliveryChute;
import ca.ucalgary.seng300.a2.hardware.DeliveryChuteListener;
import ca.ucalgary.seng300.a2.hardware.VendingMachine;

public class DeliveryListener implements DeliveryChuteListener {
	private boolean isFull = false;
	
	private VendingMachine vm;
	
	public DeliveryListener() {
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
		//vm.getDisplay().display("Coins returned/ Pop can vended");
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
