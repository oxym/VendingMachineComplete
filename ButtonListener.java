

import org.lsmr.vending.hardware.*;

public class ButtonListener implements PushButtonListener{
	
	private VendingMachine vm;
	private CReceptacleListener r;
	
	public ButtonListener (VendingMachine vm,CReceptacleListener r) {
		this.vm = vm;
		this.r = r;
	}

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
	}

	@Override
	public void pressed(PushButton button) {
		int buttonNum = vm.getNumberOfSelectionButtons();
		int index=-1;
		for (int i = 0; i < buttonNum; i++) {
			if (vm.getSelectionButton(i).equals(button)){
				index = i;
				break;
				
			}
		}
		
		int popCost = vm.getPopKindCost(index);
		
		//display that there is not enough credit to buy selected pop
		if (r.getTotal() < popCost) {
			vm.getDisplay().display("Not enough credit");
			return;
		}
		else {
			try {
				vm.getPopCanRack(index).dispensePopCan();
				vm.getCoinReceptacle().storeCoins();
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
				System.out.print("The vending machine is disabled.\n");
				return;
			}
		}
	}
}
