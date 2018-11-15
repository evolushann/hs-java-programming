package space.harbour.java.hw8;

import java.util.Iterator;

public abstract class Dispenser {
	private static final int BILLS_AMT = 10;
	private ATM atm;
	private Dispenser next;
	private int denomination;
	private int bills;

	public Dispenser(int denomination, ATM atm) {
		this.atm = atm;
		this.denomination = denomination;
		bills = BILLS_AMT;
	}

	public void printWithdrawal(int quantity) {
		System.out.println("Withdrawing " + denomination + " x " + quantity);
	}

	public Dispenser getNext() {
		return next;
	}

	public void setNext(Dispenser next) {
		this.next = next;
	}

	public int getDenomination() {
		return denomination;
	}

	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}

	public int getBills() {
		return bills;
	}

	public void setBills(int bills) {
		this.bills = bills;
	}

	public ATM getAtm() {
		return atm;
	}

	public void setAtm(ATM atm) {
		this.atm = atm;
	}

	private Dispenser getDispenser() {
		return this;
	}

	public void withdraw(int amount) {
		int quantity = amount / denomination;

		if(amount >= denomination && bills >= quantity) {
			printWithdrawal(quantity);
			bills -= quantity;
			amount -= quantity * denomination;
		} else if (bills < quantity && bills > 0) {
			printWithdrawal(bills);
			amount -= denomination * bills;
			bills = 0;
		}

		if(next != null)
			getNext().withdraw(amount);

		if(bills == 0) {
			System.out.println("We're out of " + denomination + "s");
			atm.alert(this);
		}
	}

	public Iterator<Dispenser> getIterator() {
		return new Iterator<Dispenser>() {
			Dispenser current = getDispenser();

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Dispenser next() {
				Dispenser temp = current;
				current = current.getNext();
				return temp;
			}
		};
	}

	public void refill() {
		bills = BILLS_AMT;
	}
}
