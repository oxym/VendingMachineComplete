package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.CapacityExceededException;
import org.lsmr.vending.hardware.DisabledException;
import org.lsmr.vending.hardware.EmptyException;
import org.lsmr.vending.hardware.PushButton;
import org.lsmr.vending.hardware.PushButtonListener;
import org.lsmr.vending.hardware.VendingMachine;

public class ButtonListener implements PushButtonListener{
	
	private VendingMachine vm;
	private EventWriter ew;
	private Logic logic;
	private boolean off;
	
	public ButtonListener (VendingMachine vm, EventWriter ew, Logic logic) {
		this.vm = vm;
		this.logic = logic;
		this.ew = ew;
	}
	public boolean getState() {
		return off;
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
	public void pressed(PushButton button) {
		//find the button number that was pressed
		int buttonNum = vm.getNumberOfSelectionButtons();
		int index=-1;
		for (int i = 0; i <= buttonNum; i++) {
			if (vm.getSelectionButton(i).equals(button)){
				index = i;
				break;
			}
		}
		
		ew.logEvent("Button " + index + " was pressed.");
		int popCost = vm.getPopKindCost(index);
		
		//display that there is not enough credit to buy selected pop
		if (logic.getCredit() < popCost) {
			vm.getDisplay().display("Not enough credit");
			return;
		}
		else {
			try {
				vm.getPopCanRack(index).dispensePopCan();
				logic.changeCredit(-popCost);
				logic.returnCoins();
				return;
			}catch(EmptyException e) {
				vm.getDisplay().display("Sorry, all out of that selection");
				return;
			}
			catch(CapacityExceededException e) {
				System.out.println(e);
				return;
			}
			catch(DisabledException e) {
				vm.getDisplay().display("The vending machine is disabled.");
				return;
			}
		}
	}
}
