//Wrote by Beier Nie, this class is to define swimming sports.
import java.util.ArrayList;
import java.util.Random;

public class Swimming extends Sports {

	public Swimming(String sportsID, Officials Officials, ArrayList<MatchResult> matchResults) {
		super(sportsID, Officials, matchResults);
	}

	@Override
	public int compete() {
		Random r = new Random();

		return r.nextInt(101) + 100;

	}

}
