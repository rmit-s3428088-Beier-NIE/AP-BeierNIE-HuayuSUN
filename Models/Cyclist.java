//Wrote by Beier Nie
package Models;

import java.util.Random;

public class Cyclist extends Athlete {

	public Cyclist(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
	}
	
	public int compete(String gameType) {

		Random r = new Random();

		return r.nextInt(301) + 500;

	}

}
