//Wrote by Beier Nie, this class is to define athletes.
public class Athlete extends Person {
	private int points = 0;
	private String type;

	protected Athlete(String ID, String name, int age, String state, String type) {
		super(ID, name, age, state);
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
