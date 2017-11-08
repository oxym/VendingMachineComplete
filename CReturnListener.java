import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.CoinReturn;
import org.lsmr.vending.hardware.CoinReturnListener;
import org.lsmr.vending.hardware.VendingMachine;

public class CReturnListener implements CoinReturnListener {

	private VendingMachine vm;
	private boolean on;
	
	public boolean getState() {
		return on;
	}
	
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		on = true;

	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		on = false;

	}

	@Override
	public void coinsDelivered(CoinReturn coinReturn, Coin[] coins) {
		// TODO Auto-generated method stubs

	}

	@Override
	public void returnIsFull(CoinReturn coinReturn) {
		coinReturn.disable(); //if the return is full, it should be prevented from returning more
		vm.getDisplay().display("Coin return slot is full, please take your change");
		

	}

}
