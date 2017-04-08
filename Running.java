//Wrote by Beier Nie, this class is to define running sports.
import java.util.ArrayList;
import java.util.Random;

public class Running extends Sports {

	public Running(String sportsID, Officials officials, ArrayList<MatchResult> competeResults) {
		super(sportsID, officials, competeResults);
	}
	@Override
	public int compete() {

		Random r = new Random();

		return r.nextInt(11) + 10;
	}
}
