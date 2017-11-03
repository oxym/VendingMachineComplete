package ca.ucalgary.seng300.a1;
import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.*;

public class CSlotListener implements CoinSlotListener{

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void validCoinInserted(CoinSlot slot, Coin coin) {
	}

	@Override
	public void coinRejected(CoinSlot slot, Coin coin) {
		System.out.print("Coin Rejected.\n");		
	}
	
}
