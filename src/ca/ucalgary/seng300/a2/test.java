package ca.ucalgary.seng300.a2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.DisabledException;
import org.lsmr.vending.hardware.VendingMachine;

public class test {
	VendingMachine vm;
	CReceptacleListener crListener;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // necessary to capture printed output
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent)); // necessary to capture printed output
		System.setErr(new PrintStream(errContent));

		/*
		 * Vending Machine Parameters: Accepted coin values: 1, 5, 10, 25, 100, 200
		 * Number of pop types/selection buttons: 6 Coin Rack Capacity: 200 Pop Can Rack
		 * Capacity: 10 Coin Receptacle Capacity: 200 Pop (Name, Cost): (popA, 100),
		 * (popB, 100), (popC, 100), (popD, 100), (popE, 150), (popF, 200)
		 */
		vm = new VendingMachine(new int[] { 1, 5, 10, 25, 100, 200 }, 6, 200, 10, 200, 200, 200);
		vm.configure(Arrays.asList("popA", "popB", "popC", "popD", "popE", "popF"),
				Arrays.asList(100, 100, 100, 100, 150, 200));

		crListener = new CReceptacleListener();

		vm.getCoinReceptacle().register(crListener);
		vm.getCoinSlot().register(new CSlotListener());
		for (int i = 0; i < 6; i++) {
			vm.getPopCanRack(i).register(new PCRListener());
			vm.getSelectionButton(i).register(new ButtonListener(vm, crListener));
		}

		vm.loadPopCans(10, 10, 10, 10, 10, 10);
		vm.loadCoins(0, 0, 0, 0, 0, 0);
	}

	@After
	public void cleanUp() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	// Basic test wherein the correct coins are added, enough money is added, and
	// the button is pressed
	public void testTrue() {
		try {
			vm.getCoinSlot().addCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(0).press();
		assertEquals("popA was vended.\nCoins removed from receptacle.\n", outContent.toString());
		assertEquals(0, crListener.getTotal());
	}

	// Tests behavior when the correct coins are added, but not enough of them are
	// added to buy a pop
	@Test
	public void testNotEnoughCoins() {
		try {
			vm.getCoinSlot().addCoin(new Coin(25));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(0).press();
		assertEquals("Not enough cash\n", outContent.toString());
		assertEquals(25, crListener.getTotal());
	}

	// Tests what happens when a button is pushed with no money added whatsoever
	@Test
	public void testNoCoins() {
		vm.getSelectionButton(0).press();
		assertEquals("Not enough cash\n", outContent.toString());
		assertEquals(0, crListener.getTotal());
	}

	// Tests to see if the coin slot rejects the money once the receptacle is full,
	// and if vending can still proceed as intended after too many coins were added
	@Test
	public void testAddOverCapacity() {
		try {
			for (int i = 0; i < 201; i++) {
				vm.getCoinSlot().addCoin(new Coin(25));
			}
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(0).press();
		assertEquals("Coin Rejected.\npopA was vended.\nCoins removed from receptacle.\n", outContent.toString());
		assertEquals(0, crListener.getTotal());
	}

	// Tests behavior upon emptying a pop can rack.
	// The coin receptacle should still have the money that a user entered if pop
	// was not vended
	@Test
	public void testEmptyPopCanRack() {
		try {
			for (int i = 0; i < 11; i++) {
				vm.getCoinSlot().addCoin(new Coin(100));
				vm.getSelectionButton(0).press();
			}
		} catch (DisabledException e) {
			fail();
		}
		assertEquals(
				"popA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\npopA was vended.\nCoins removed from receptacle.\nSorry, it's empty\n",
				outContent.toString());
		assertEquals(100, crListener.getTotal());
	}

	// Tests pressing an invalid button
	@Test
	public void testPressInvalid() {
		boolean thrown = false;
		try {
			vm.getSelectionButton(7);
		} catch (ArrayIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	@Test
	public void testPressInvalidNegative() {
		boolean thrown = false;
		try {
			vm.getSelectionButton(-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	// Tests to see if all the buttons work as intended given enough money.
	@Test
	public void testAllButtons() {
		try {
			vm.getCoinSlot().addCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(0).press();
		assertEquals(0, crListener.getTotal());

		try {
			vm.getCoinSlot().addCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(1).press();
		assertEquals(0, crListener.getTotal());

		try {
			vm.getCoinSlot().addCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(2).press();
		assertEquals(0, crListener.getTotal());

		try {
			vm.getCoinSlot().addCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(3).press();
		assertEquals(0, crListener.getTotal());

		try {
			vm.getCoinSlot().addCoin(new Coin(100));
			vm.getCoinSlot().addCoin(new Coin(200));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(4).press();
		assertEquals(0, crListener.getTotal());

		try {
			vm.getCoinSlot().addCoin(new Coin(200));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(5).press();
		assertEquals(
				"popA was vended.\nCoins removed from receptacle.\npopB was vended.\nCoins removed from receptacle.\npopC was vended.\nCoins removed from receptacle.\npopD was vended.\nCoins removed from receptacle.\npopE was vended.\nCoins removed from receptacle.\npopF was vended.\nCoins removed from receptacle.\n",
				outContent.toString());
		assertEquals(0, crListener.getTotal());
	}

	// Tests such that if a user adds too little money,and presses a button, the
	// money stays in the receptacle
	// and they can add more money until they have enough.
	@Test
	public void testCoinsKeptInReceptacle() {
		try {
			vm.getCoinSlot().addCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(5).press();
		assertEquals(100, crListener.getTotal());
		try {
			vm.getCoinSlot().addCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		vm.getSelectionButton(5).press();
		assertEquals("Not enough cash\npopF was vended.\nCoins removed from receptacle.\n", outContent.toString());
		assertEquals(0, crListener.getTotal());
	}

	// Tests that invalid coins don't count towards money used to buy pop
	@Test
	public void testAddInvalidCoins() {
		try {
			vm.getCoinSlot().addCoin(new Coin(300));
		} catch (DisabledException e) {
			fail();
		}
		assertEquals(0, crListener.getTotal());
		vm.getSelectionButton(0).press();
		assertEquals("Coin Rejected.\nNot enough cash\n", outContent.toString());
	}

}
