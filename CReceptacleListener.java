package ca.ucalgary.seng300.a2;



import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.CoinReceptacle;
import org.lsmr.vending.hardware.CoinReceptacleListener;

public class CReceptacleListener implements CoinReceptacleListener{
	private int total = 0;
	
	public int getTotal() {
		return total;
	}

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		this.total += coin.getValue();
	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		System.out.print("Coins removed from receptacle.\n");
		this.total = 0;
	}

	@Override
	public void coinsFull(CoinReceptacle receptacle) {
	}

	@Override
	public void coinsLoaded(CoinReceptacle receptacle, Coin... coins) {
	}

	@Override
	public void coinsUnloaded(CoinReceptacle receptacle, Coin... coins) {
	}

}
