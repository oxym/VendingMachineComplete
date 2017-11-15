package ca.ucalgary.seng300.a2;



import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.CapacityExceededException;
import org.lsmr.vending.hardware.CoinReceptacle;
import org.lsmr.vending.hardware.CoinReceptacleListener;
import org.lsmr.vending.hardware.DisabledException;
import org.lsmr.vending.hardware.VendingMachine;

public class ReceptacleListener implements CoinReceptacleListener{
	private VendingMachine vm;
	private EventWriter ew;
	private Logic logic;
	
	public ReceptacleListener (VendingMachine vm, EventWriter ew, Logic logic) {
		this.ew = ew;
		this.vm = vm;
		this.logic = logic;
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
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		try {
			vm.getCoinReceptacle().storeCoins();
		} catch (CapacityExceededException | DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsFull(CoinReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsLoaded(CoinReceptacle receptacle, Coin... coins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsUnloaded(CoinReceptacle receptacle, Coin... coins) {
		try {
			vm.getCoinReceptacle().storeCoins();
		} catch (CapacityExceededException | DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
