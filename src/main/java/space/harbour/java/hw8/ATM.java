package space.harbour.java.hw8;

import java.util.Iterator;

public class ATM {
	Dispenser atm;

	public ATM() {
		atm = new FiftyEuroDispenser();
		TwentyEuroDispenser twenties = new TwentyEuroDispenser();
		atm.setNext(twenties);
		TenEuroDispenser tens = new TenEuroDispenser();
		twenties.setNext(tens);
		FiveEuroDispenser fives = new FiveEuroDispenser();
		tens.setNext(fives);
	}

	public void withdraw(int amount) {
		if(amount % 5 > 0)
			System.out.println("Invalid amount!");
		else if(amount > getBalance())
			System.out.println("The ATM is broke.");
		else
			atm.withdraw(amount);
	}

	public int getBalance() {
		int balance = 0;
		Iterator<Dispenser> dispenserIterator = atm.getIterator();
		while (dispenserIterator.hasNext()) {
			Dispenser d = dispenserIterator.next();
			balance += d.getDenomination() * d.getBills();
		}

		return balance;
	}
}
