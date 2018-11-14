package space.harbour.java.hw8;

public abstract class Dispenser {
	private Dispenser next;
	private int denomination;

	public Dispenser(int denomination) {
		this.denomination = denomination;
	}

	public void withdraw(int amount) {
		if(amount >= denomination)
			printWithdrawal(amount / denomination);
		if(amount % denomination > 0 && next != null)
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
}
