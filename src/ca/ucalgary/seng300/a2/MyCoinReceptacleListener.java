import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.CoinReceptacle;

public class MyCoinReceptacleListener implements CoinReceptacleListener {
	/**
     * An event that announces that the indicated coin has been added to the
     * indicated receptacle.
     * 
     * @param receptacle
     *            The receptacle where the event happened.
     * @param coin
     *            The coin added.
     */
    void coinAdded(CoinReceptacle receptacle, Coin coin) {
    	//TO-DO
    }

    /**
     * An event that announces that all coins have been removed from the
     * indicated receptacle.
     * 
     * @param receptacle
     *            The receptacle where the event happened.
     */
    void coinsRemoved(CoinReceptacle receptacle) {
    	//TO-DO
    }

    /**
     * An event that announces that the indicated receptacle is now full.
     * 
     * @param receptacle
     *            The receptacle where the event happened.
     */
    void coinsFull(CoinReceptacle receptacle) {
    	//TO-DO
    }

    /**
     * Announces that the indicated sequence of coins has been added to the
     * indicated coin receptacle. Used to simulate direct, physical loading of
     * the receptacle.
     * 
     * @param receptacle
     *            The receptacle where the event occurred.
     * @param coins
     *            The coins that were loaded.
     */
    void coinsLoaded(CoinReceptacle receptacle, Coin... coins) {
    	//TO-DO
    }

    /**
     * Announces that the indicated sequence of coins has been removed to the
     * indicated coin receptacle. Used to simulate direct, physical unloading of
     * the receptacle.
     * 
     * @param receptacle
     *            The receptacle where the event occurred.
     * @param coins
     *            The coins that were unloaded.
     */
    void coinsUnloaded(CoinReceptacle receptacle, Coin... coins) {
    	//TO-DO
    }

}
