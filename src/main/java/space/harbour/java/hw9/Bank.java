package space.harbour.java.hw9;

import space.harbour.java.hw8.ATM;
import space.harbour.java.hw8.Dispenser;

public class Bank {
	public ATM atm;

	public Bank() {
		atm = new BankATM(this);
	}

	public ATM createATM() {
		return atm.clone();
	}

	public void alert(Dispenser dispenser) {
		System.out.println("We need to refill " + dispenser.getDenomination() + "s!");
		dispenser.refill();
	}
}
