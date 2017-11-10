package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.*;

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
		int buttonNum = vm.getNumberOfSelectionButtons();
		ew.logEvent("Button " + buttonNum + " was pressed.");
		int index=-1;
		for (int i = 0; i < buttonNum; i++) {
			if (vm.getSelectionButton(i).equals(button)){
				index = i;
				break;
				
			}
		}
		
		int popCost = vm.getPopKindCost(index);
		
		//display that there is not enough credit to buy selected pop
		if (logic.getCredit() < popCost) {
			vm.getDisplay().display("Not enough credit");
			return;
		}
		else {
			try {
				vm.getPopCanRack(index).dispensePopCan();
				vm.getCoinReceptacle().storeCoins();
				logic.changeCredit(-popCost);
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
