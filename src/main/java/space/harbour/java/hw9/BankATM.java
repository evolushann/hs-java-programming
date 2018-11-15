package space.harbour.java.hw9;

import space.harbour.java.hw8.ATM;

public class BankATM extends ATM {
	Bank department;

	public BankATM(Bank department) {
		super(department);
		if(department != null)
			this.department = department;
	}

	@Override
	public ATM clone() {
		return new BankATM(department);
	}
}
