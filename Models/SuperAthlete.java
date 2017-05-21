//Wrote by Beier Nie
package Models;

import java.util.Random;
import Controller.Driver;

public class SuperAthlete extends Athlete {

	public SuperAthlete(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
	}

	 int min;
	 int max;

	public int compete(String gameType) {
		switch (gameType) {
		case Driver.RUN:
			min = 11;
			max = 10;
			break;
		case Driver.SWIM:
			min = 101;
			max = 100;
			break;
		case Driver.CYCLE:
			min = 301;
			max = 500;
			break;
		}

		Random r = new Random();
	   return r.nextInt(min) + max;


	}

}
