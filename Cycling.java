//Wrote by Beier Nie, this class is to define cycling sports.
import java.util.ArrayList;
import java.util.Random;

public class Cycling extends Sports {

	public Cycling(String sportsID, Officials officials, ArrayList<MatchResult> competeResults) {
		super(sportsID, officials, competeResults);
	}
	@Override
	public int compete() {

		Random r = new Random();

		return r.nextInt(301) + 500;

	}

}
