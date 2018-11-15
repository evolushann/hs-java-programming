package space.harbour.java.hw9;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

public class BankTest extends TestCase {
	Bank branch;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		branch = new Bank();
	}

	@After
	public void tearDown() throws Exception {
		branch = null;
		super.tearDown();
	}

	public void testWithdrawNoRefills() {
		assertEquals(850, branch.atm.getBalance());
		branch.atm.withdraw(385);
		assertEquals(465, branch.atm.getBalance());
	}

	public void testWithdrawWithRefills() {
		assertEquals(850, branch.atm.getBalance());
		branch.atm.withdraw(700);
		assertEquals(850, branch.atm.getBalance());
	}

	public void testWithdrawAllMoney() {
		assertEquals(850, branch.atm.getBalance());
		branch.atm.withdraw(850);
		assertEquals(850, branch.atm.getBalance());
	}

	public void testWithdrawMoreThanBalance() {
		assertEquals(850, branch.atm.getBalance());
		branch.atm.withdraw(900);
		assertEquals(850, branch.atm.getBalance());
	}
}
