//Wrote by Beier Nie
package Models;
import java.util.Random;

public class Swimmer extends Athlete {

	public Swimmer(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
	}

	public int compete(String gameType) {
		Random r = new Random();

		return r.nextInt(101) + 100;

	}

}
