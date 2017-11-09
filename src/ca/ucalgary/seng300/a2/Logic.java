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
	private DListener displayListener;
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
		
		
		
		
		
	}
	
	public void insertCoin(Coin coin) throws DisabledException {
		vm.getCoinSlot().addCoin(coin);
		
	}
	
	public void vend(PushButton button) {
		
	}

}
