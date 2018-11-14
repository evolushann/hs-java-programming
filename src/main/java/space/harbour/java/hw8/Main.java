package space.harbour.java.hw8;

public class Main {
	public static void main(String[] args) {
		Dispenser atm = new FiftyEuroDispenser();
		atm.setNext(new TwentyEuroDispenser());
		atm.getNext().setNext(new TenEuroDispenser());
		atm.getNext().getNext().setNext(new FiveEuroDispenser());

		System.out.println(atm.withdraw(24));
	}
}
