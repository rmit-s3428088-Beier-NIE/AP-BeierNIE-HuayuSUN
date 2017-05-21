//Wrote by Beier Nie
package Models;

import java.util.ArrayList;
import java.util.List;

public class Official extends Participants {

	ArrayList<Athlete> resultList = new ArrayList<>();
	ArrayList<Integer> scoreList = new ArrayList<>();
	final int rank1 = 1;
	final int rank2 = 2;
	final int rank3 = 3;

	public Official(String ID, String type, String name, int age, String state) {
		super(ID, type, name, age, state);
	}
	
	public List<Athlete> getResult() {
		return resultList;
	}

	public List<Integer> getscoreList() {
		return scoreList;
	}

	public void startNewGame() {
		resultList = new ArrayList<>();
		scoreList = new ArrayList<>();
	}

	public void rank(Athlete athlete, int result) {
		for (int i = 0; i < scoreList.size(); i++) {
			if (scoreList.get(i) < result) {
				continue;
			} else {
				resultList.add(i, athlete);
				scoreList.add(i, result);
				break;
			}
		}
		if (!resultList.contains(athlete)) {
			resultList.add(athlete);
			scoreList.add(result);
		}
	}

	public void addPoint() {
		for (int r = 1; r <= 3; r++) {
			Athlete athlete = resultList.get(r);
			switch (r) {
			case rank1:
				athlete.addPoints(5);
				break;
			case rank2:
				athlete.addPoints(2);
				break;
			case rank3:
				athlete.addPoints(1);
				break;
			}
		}
	}


}
