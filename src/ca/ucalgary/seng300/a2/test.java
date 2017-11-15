package ca.ucalgary.seng300.a2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	Logic logic;
	ReceptacleListener crListener;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // necessary to capture printed output
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent)); // necessary to capture printed output
		System.setErr(new PrintStream(errContent));

		EventWriter ew = new EventWriter("eventLog.txt");
		vm = new VendingMachine(new int[] { 1, 5, 10, 25, 100, 200 }, 6, 200, 10, 200, 200, 200);
		vm.configure(Arrays.asList("popA", "popB", "popC", "popD", "popE", "popF"),
				Arrays.asList(100, 100, 100, 100, 150, 200));
		
		vm.loadPopCans(10, 10, 10, 10, 10, 10);
		vm.loadCoins(0, 0, 0, 0, 0, 0);

		logic = new Logic(vm, ew);
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
			logic.insertCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(0);
		assertEquals("Credit: 100\nThank you for your purchase!\n", outContent.toString());
		assertEquals(0, logic.getCredit());
	}

	// Tests behavior when the correct coins are added, but not enough of them are
	// added to buy a pop
	@Test
	public void testNotEnoughCoins() {
		try {
			logic.insertCoin(new Coin(25));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(0);
		assertEquals("Credit: 25\nNot enough credit\n", outContent.toString());
		assertEquals(25, logic.getCredit());
	}

	// Tests what happens when a button is pushed with no money added whatsoever
	@Test
	public void testNoCoins() {
		logic.pressButton(0);
		assertEquals("Not enough credit\n", outContent.toString());
		assertEquals(0, logic.getCredit());
	}

	// Tests behavior upon emptying a pop can rack.
	// The coin receptacle should still have the money that a user entered if pop
	// was not vended
	@Test
	public void testEmptyPopCanRack() {
		String s = "";
		try {
			for (int i = 0; i < 11; i++) {
				logic.insertCoin(new Coin(100));
				logic.pressButton(0);
				if (i < 10) {
					s += "Credit: 100\nThank you for your purchase!\n";
				}
			}
		} catch (DisabledException e) {
			fail();
		}
		s += "Credit: 100\nSorry, all out of that selection\n";
		assertEquals(s, outContent.toString());
		assertEquals(100, logic.getCredit());
	}

	// Tests pressing an invalid button
	@Test
	public void testPressInvalid() {
		boolean thrown = false;
		try {
			logic.pressButton(7);
		} catch (ArrayIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	@Test
	public void testPressInvalidNegative() {
		boolean thrown = false;
		try {
			logic.pressButton(-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testExactChange() {
		try {
			logic.insertCoin(new Coin(200));
		} catch (DisabledException e) {
			fail();
		}
		
		logic.pressButton(4);
		assertEquals(50, logic.getCredit());
		assertTrue(logic.getLight("exact change").isActive());
		
	}
	
	@Test
	public void testOutOfOrder() {

		for (int p = 0; p < logic.getPopRackNumber(); ++p) {
			for (int a = 0; a < 10; a++) {
				try {
					logic.insertCoin(new Coin(200));
				} catch (DisabledException e) {
					fail();
				}
				logic.pressButton(p);
			}
		}
		
		assertTrue(logic.getLight("out of order").isActive());
	}

	// Tests to see if all the buttons work as intended given enough money.
	@Test
	public void testAllButtons() {
		try {
			logic.insertCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(0);
		assertEquals(0, logic.getCredit());

		try {
			logic.insertCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(1);
		assertEquals(0, logic.getCredit());

		try {
			logic.insertCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(2);
		assertEquals(0, logic.getCredit());

		try {
			logic.insertCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(3);
		assertEquals(0, logic.getCredit());

		try {
			logic.insertCoin(new Coin(100));
			logic.insertCoin(new Coin(200));
		} catch (DisabledException e) {
			fail();
		}
		assertEquals(300, logic.getCredit());
		logic.pressButton(4);
		assertEquals(50, logic.getCredit());

		try {
			logic.insertCoin(new Coin(200));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(5);
		assertEquals(50, logic.getCredit());
	}

	// Tests such that if a user adds too little money,and presses a button, the
	// money stays in the receptacle
	// and they can add more money until they have enough.
	@Test
	public void testCoinsKeptInReceptacle() {
		try {
			logic.insertCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(5);
		assertEquals(100, logic.getCredit());
		try {
			logic.insertCoin(new Coin(100));
		} catch (DisabledException e) {
			fail();
		}
		logic.pressButton(5);
		assertEquals("Credit: 100\nNot enough credit\nCredit: 200\nThank you for your purchase!\n", outContent.toString());
		assertEquals(0, logic.getCredit());
	}

	// Tests that invalid coins don't count towards money used to buy pop
	@Test
	public void testAddInvalidCoins() {
		try {
			logic.insertCoin(new Coin(300));
		} catch (DisabledException e) {
			fail();
		}
		assertEquals(0, logic.getCredit());
		logic.pressButton(0);
		assertEquals("300 Coin Dispensed\n300 coin rejected. Please insert valid coin.\n\nNot enough credit\n", outContent.toString());
	}

}
