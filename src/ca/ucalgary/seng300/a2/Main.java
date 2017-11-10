package ca.ucalgary.seng300.a2;


import java.util.Arrays;
import java.util.Scanner;

import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.VendingMachine;

import ca.ucalgary.seng300.a2.DeliveryListener;

//Test cases do not rely on Main

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		EventWriter ew = new EventWriter("eventLog.txt");
		VendingMachine vm = new VendingMachine(new int[] { 1, 5, 10, 25, 100, 200 }, 6, 200, 10, 200, 200, 200);
		// Set the prices for each respective pop
		vm.configure(Arrays.asList("popA", "popB", "popC", "popD", "popE", "popF"),
				Arrays.asList(100, 100, 100, 100, 150, 200));

		// Load the pop cans and have the coin racks be set to 0
		vm.loadPopCans(10, 10, 10, 10, 10, 10);
		vm.loadCoins(0, 0, 0, 0, 0, 0);
		
		Logic logic = new Logic(vm, ew);

		System.out.println(
				"\"insert 1, 5, 10, 25, 100 or 200\" to insert a coin. \"press 0, 1, 2, 3, 4 or 5\" to dispense a pop can. \"off\" to quit.");
		System.out.println("popA = 100, popB = 100, popC = 100, popD = 100, popE = 150, popF = 200");
		
		Thread thread1 = Thread.currentThread();
		Thread thread2 = null;
		Scanner s = new Scanner(System.in);
		String st;
		try {
			while (true) {
				if(logic.getCredit() == 0) {
					thread2 = new Thread(() -> {
					    try {
					    	while(true) {
					    		logic.getDisplay().display("Hi there!");
					    		Thread.sleep(5 * 1000);
					    		logic.getDisplay().display("");
					    		Thread.sleep(10*1000);
					    	}
					    }catch(InterruptedException weCanIgnoreThisException){}
					});
					thread2.start();
				}
				st = s.nextLine();
				thread2.stop();
				String[] str = st.split("\\s+");
				if (str[0].trim().equals("insert")) {
					// Add coin value to the receptacle, if legal.
					logic.insertCoin(new Coin(Integer.parseInt(str[1])));

				} else if (str[0].equals("press")) {
					// If button entered does not exist, it will throw a
					// ArrayIndexOutOfBoundsException, which will then be caught.
					try {
						logic.pressButton(Integer.parseInt(str[1]));
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Invalid Button");
					}
				} else if (str[0].equals("off")) {
					break;
				} else {
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
		ew.closeWriter();


	}

}
