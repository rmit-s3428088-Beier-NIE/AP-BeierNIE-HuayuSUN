////Wrote by Beier Nie, this class is to define all kinds of people including athletes and officials.
public abstract class Person {

	private String ID;
	private String name;
	private int age;
	private String state;

	public Person(String ID, String name, int age, String state) {
		this.ID = ID;
		this.setName(name);
		this.setAge(age);
		this.setState(state);
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
