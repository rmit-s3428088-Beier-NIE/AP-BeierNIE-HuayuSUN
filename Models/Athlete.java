//Wrote by Beier Nie
package Models;

public abstract class Athlete extends Participants {

	private int points = 0;

	public Athlete(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
	}

	public abstract int compete(String gameType);

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void addPoints(int points) {
		this.points += points;
	}
}
