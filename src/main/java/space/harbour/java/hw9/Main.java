package space.harbour.java.hw9;

public class Main {
	public static void main(String[] args) {
		Bank branch = new Bank();

		System.out.println(branch.atm.getBalance());
		branch.atm.withdraw(385);
		System.out.println(branch.atm.getBalance());
	}
}
