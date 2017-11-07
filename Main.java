//Group 17
//Micheal James de Grood, 10134884
//Tariq Rahmani, 30006949
//Xian-Meng Low, 10127970

package ca.ucalgary.seng300.a1;

import java.util.Arrays;
import java.util.Scanner;

import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.VendingMachine;

//Test cases do not rely on Main

public class Main {

	public static void main(String[] args) {
		VendingMachine vm = new VendingMachine(new int[] {1,5,10,25,100,200}, 6, 200,10,200);
		//Set the prices for each respective pop
		vm.configure(Arrays.asList("popA", "popB", "popC", "popD", "popE", "popF"), Arrays.asList(100,100,100,100,150,200));
		
		CReceptacleListener crListener = new CReceptacleListener();
		DListener disListener = new DListener();
		
		//Register the listeners to their respective classes
		vm.getCoinReceptacle().register(crListener);
		vm.getDisplay().register(disListener);
		vm.getCoinSlot().register(new CSlotListener());
		
		for(int i=0; i<6; i++) {
			vm.getPopCanRack(i).register(new PCRListener());
			vm.getSelectionButton(i).register(new ButtonListener(vm, crListener));
		}
		
		//Load the pop cans and have the coin racks be set to 0
		vm.loadPopCans(10,10,10,10,10,10);
		vm.loadCoins(0,0,0,0,0,0);
		
		System.out.println("\"insert 1, 5, 10, 25, 100 or 200\" to insert a coin. \"press 0, 1, 2, 3, 4 or 5\" to dispense a pop can. \"off\" to quit.");
		System.out.println("popA = 100, popB = 100, popC = 100, popD = 100, popE = 150, popF = 200");
		
		Scanner s = new Scanner(System.in);
		String st;
		try {
			while(true) {
				st = s.nextLine();
				String[] str = st.split("\\s+");
				if(str[0].trim().equals("insert")) {
					//Add coin value to the receptacle, if legal.
					vm.getCoinSlot().addCoin(new Coin(Integer.parseInt(str[1])));
					System.out.println("Coins: " + crListener.getTotal());
				}
				else if(str[0].equals("press")) {
					//If button entered does not exist, it will throw a ArrayIndexOutOfBoundsException, which will then be caught.
					try {
						vm.getSelectionButton(Integer.parseInt(str[1])).press();
					}
					catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("Invalid Button");
					}
				}
				else if(str[0].equals("off")) {
					break;
				}
				else {
					System.out.println(str[0]);
					System.out.println("try again");
				}
			}
		} catch (Exception e) {
			s.close();
			System.out.println(e);
			e.printStackTrace();
		}
		s.close();

	}

}
