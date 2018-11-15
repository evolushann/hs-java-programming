package space.harbour.java.hw8;

import space.harbour.java.hw9.Bank;

import java.util.Iterator;

public abstract class ATM implements Cloneable {
	Dispenser dispenser;
	private Bank department;

	public ATM(Bank department) {
		dispenser = new FiftyEuroDispenser(this);
		TwentyEuroDispenser twenties = new TwentyEuroDispenser(this);
		dispenser.setNext(twenties);
		TenEuroDispenser tens = new TenEuroDispenser(this);
		twenties.setNext(tens);
		FiveEuroDispenser fives = new FiveEuroDispenser(this);
		tens.setNext(fives);

		this.department = department;
	}

	public void withdraw(int amount) {
		if(amount % 5 > 0)
			System.out.println("Invalid amount!");
		else if(amount > getBalance())
			System.out.println("The ATM is broke.");
		else
			dispenser.withdraw(amount);
	}

	public int getBalance() {
		int balance = 0;
		Iterator<Dispenser> dispenserIterator = dispenser.getIterator();
		while (dispenserIterator.hasNext()) {
			Dispenser d = dispenserIterator.next();
			balance += d.getDenomination() * d.getBills();
		}

		return balance;
	}

	public void alert(Dispenser dispenser) {
		department.alert(dispenser);
	}

	public abstract ATM clone();
}