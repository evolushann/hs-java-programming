package space.harbour.java.hw8;

import java.util.Iterator;

public abstract class Dispenser {
	private Dispenser next;
	private int denomination;
	private int bills;

	public Dispenser(int denomination) {
		this.denomination = denomination;
		bills = 10;
	}

	public void withdraw(int amount) {
		int quantity = amount / denomination;

		if(amount >= denomination && bills >= quantity) {
			printWithdrawal(quantity);
			bills -= quantity;
		} else if (bills < quantity && bills > 0) {
			amount -= denomination * bills;
			bills = 0;
		}

		if(next != null)
			getNext().withdraw(amount % denomination);
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

	private Dispenser getDispenser() {
		return this;
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
}
