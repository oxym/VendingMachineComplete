
import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.*;

public class CSlotListener implements CoinSlotListener{

	private VendingMachine vm;
	private boolean on;
	
	public boolean getState() {
		return on;
	}
	
	public CSlotListener(VendingMachine vend, boolean state) {
		vm = vend;
		on = state;
	}
	
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void validCoinInserted(CoinSlot slot, Coin coin) {
		vm.getDisplay().display(coin.getValue() + " coin inserted.\n");
	}

	@Override
	public void coinRejected(CoinSlot slot, Coin coin) {
		vm.getDisplay().display(coin.getValue() + "Coin Rejected.\n");		
	}
	
}
