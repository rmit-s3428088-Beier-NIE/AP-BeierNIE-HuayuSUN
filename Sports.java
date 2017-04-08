//Wrote by Beier Nie, this class is to define the superclass sports.
import java.util.ArrayList;

public abstract class Sports {
	private String sportsID;
	private Officials officials;
	private ArrayList<MatchResult> matchResults;
	private Athlete predWinner;

	public Sports(String sportsID, Officials officials, ArrayList<MatchResult> matchResults) {
		this.setSportsID(sportsID);
		this.setOfficials(officials);
		this.setMatchResults(matchResults);
	}

	public String getSportsID() {
		return sportsID;
	}

	public void setSportsID(String sportsID) {
		this.sportsID = sportsID;
	}

	public Officials getOfficials() {
		return officials;
	}

	public void setOfficials(Officials officials) {
		this.officials = officials;
	}

	public ArrayList<MatchResult> getMatchResults() {
		return matchResults;
	}

	public void setMatchResults(ArrayList<MatchResult> matchResults) {
		this.matchResults = matchResults;
	}

	public Athlete getPredWinner() {
		return predWinner;
	}

	public void setPredictedWinner(Athlete predWinner) {
		this.predWinner = predWinner;
	}
	public abstract int compete();
}
