package scripts.potato_picker.logic;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Login;

import scripts.PotatoPicker;
import scripts.api.Task;

public class BankPotato implements Task {

	private int numberAttempts = 0;

	@Override
	public int priority() {
		return 4;
	}

	@Override
	public boolean validate() {
		return (Banking.isInBank() && Inventory.getAll().length>0);
	}

	@Override
	public void execute() {
		if (Banking.openBank()) {
			Banking.depositAll();
			Banking.close();
			resetAttempts();
		} else {
			incrementAttempts();
			if (madeTooManyAttempts()){
				Login.logout();
				PotatoPicker.stopBot();
			}
		}
	}

	@Override
	public String action() {
		return "Banking potatoes...";
	}

	private boolean madeTooManyAttempts() {
		return numberAttempts > 3;
	}
	
	private void incrementAttempts(){
		numberAttempts++;
	}

	private void resetAttempts() {
		numberAttempts = 0;
	}
	
}
