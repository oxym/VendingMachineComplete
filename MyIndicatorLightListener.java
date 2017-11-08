package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.IndicatorLight;
import org.lsmr.vending.hardware.IndicatorLightListener;

public class MyIndicatorLightListener implements IndicatorLightListener {

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activated(IndicatorLight light) {
		System.out.println("Indicator activated");

	}

	@Override
	public void deactivated(IndicatorLight light) {
		// TODO Auto-generated method stub

	}

}
