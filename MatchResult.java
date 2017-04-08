//Wrote by Huayu Sun, this class is to get all the match results.
public class MatchResult {
	private int rank;
	private int time;
	private Athlete athlete;

	public MatchResult(Athlete athlete) {
		this.time = 0;
		this.athlete = athlete;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
}
