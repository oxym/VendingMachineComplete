package ca.ucalgary.seng300.a2;

import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.CoinReturn;
import org.lsmr.vending.hardware.CoinReturnListener;
import org.lsmr.vending.hardware.VendingMachine;

public class CReturnListener implements CoinReturnListener {

	private VendingMachine vm;
	private EventWriter ew;
	private Logic logic;
	private boolean off;
	
	public boolean getState() {
		return off;
	}
	
	public CReturnListener(VendingMachine vend, EventWriter ew, Logic logic) {
		this.vm = vend;
		this.ew = ew;
		this.logic = logic;
		off = vm.getCoinReturn().isDisabled();
	}
	
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		off = false;

	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		off = true;

	}

	@Override
	public void coinsDelivered(CoinReturn coinReturn, Coin[] coins) {
		for(int i = 0; i < coins.length; i++) {
			vm.getDisplay().display(coins[i].getValue() + "Cent Coin Dispensed");
		}

		
		for(int i = 0; i < coins.length; i++) {
			ew.logEvent(coins[i].getValue() + " coin returned");
		}

	}

	@Override
	public void returnIsFull(CoinReturn coinReturn) {
		coinReturn.disable(); //if the return is full, it should be prevented from returning more
		vm.getDisplay().display("Coin return slot is full, please take your change");
		ew.logEvent("Coin return slot is full");

	}

}
