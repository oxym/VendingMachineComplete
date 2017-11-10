package ca.ucalgary.seng300.a2;


import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.*;

public class CSlotListener implements CoinSlotListener{

	private VendingMachine vm;
	private EventWriter ew;
	private Logic logic;
	private boolean off;
	
	
	public boolean getState() {
		return off;
	}
	
	public CSlotListener(VendingMachine vend, EventWriter ew, Logic logic) {
		this.vm = vend;
		this.ew = ew;
		this.logic = logic;
		off = vm.getCoinSlot().isDisabled();
		
	}
	
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void validCoinInserted(CoinSlot slot, Coin coin) {
		
		vm.getDisplay().display("Credit: " + logic.getCredit());
	
		ew.logEvent(coin.getValue() + " coin inserted. Total credit: " );
	}

	@Override
	public void coinRejected(CoinSlot slot, Coin coin) {
		vm.getDisplay().display(coin.getValue() + " coin rejected. Please insert valid coin.\n");
		ew.logEvent(coin.getValue() + " coin Rejected.");
	}
	
}
