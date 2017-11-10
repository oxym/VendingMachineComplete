package ca.ucalgary.seng300.a2;

import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.DisabledException;
import org.lsmr.vending.hardware.PushButton;
import org.lsmr.vending.hardware.VendingMachine;

public class Logic {
	private VendingMachine vm;
	private EventWriter ew;
	private int credit;
	
	//listeners
	private ButtonListener buttonListener;
	private CReturnListener returnListener;
	private CSlotListener slotListener;
	private DeliveryListener deliveryListener;
	private MyDisplayListener displayListener;
	private MyIndicatorLightListener exactChangeListener;
	private MyIndicatorLightListener outOfOrderListener;
	private PCRListener popCanRackListener;
	private PopListener popListener;
	private ReceptacleListener receptacleListener;
	
	//constructor for logic class
	public Logic(VendingMachine vend, EventWriter write) {
		
		vm = vend;
		ew = write;
		credit = 0;
		
		buttonListener = new ButtonListener(vm, ew, this);
		returnListener = new CReturnListener(vm, ew, this);
		slotListener = new CSlotListener(vm, ew, this);
		deliveryListener = new DeliveryListener(vm, ew, this);
		displayListener = new MyDisplayListener(vm, ew, this);
		exactChangeListener = new MyIndicatorLightListener(vm, ew, this);
		outOfOrderListener = new MyIndicatorLightListener(vm, ew, this);
		popCanRackListener = new PCRListener(vm, ew, this);
		popListener = new PopListener(vm, ew, this);
		receptacleListener = new ReceptacleListener(vm, ew, this);
		
		// Register the listeners to their respective classes 
		vm.getCoinReceptacle().register(receptacleListener);
		vm.getDisplay().register(displayListener);
		vm.getCoinSlot().register(slotListener);
		
		//below line is giving NullPointer exception
		//vm.getCoinReturn().register(returnListener);
		
		vm.getExactChangeLight().register(exactChangeListener);
		vm.getOutOfOrderLight().register(outOfOrderListener);
		vm.getDeliveryChute().register(deliveryListener);

		for (int i = 0; i < 6; i++) {
			vm.getPopCanRack(i).register(new PCRListener(vm, ew, this));
			vm.getSelectionButton(i).register(new ButtonListener(vm, ew, this));
		}

		
	}
	
	public void insertCoin(Coin coin) throws DisabledException {
		vm.getCoinSlot().addCoin(coin);
		
	}
	
	public void vend(PushButton button) {
		
	}
	
	public int getCredit() {
		return credit;
	}

	
}
