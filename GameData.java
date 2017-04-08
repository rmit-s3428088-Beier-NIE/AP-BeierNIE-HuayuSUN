//Wrote by Huayu Sun, this class is to create all the data.
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class GameData {
	public static int cyclingGameTimes = 1;
	public static int swmimmingGameTimes = 1;
	public static int runningGameTimes = 1;

	public static ArrayList<Athlete> createAllAthletes() {
		ArrayList<Athlete> allAthletes = new ArrayList<Athlete>();
		allAthletes.add(new Athlete("S01", "Ackerley ", 25, "VIC", "Swimming"));
		allAthletes.add(new Athlete("S02", "Acton    ", 30, "QLD", "Swimming"));
		allAthletes.add(new Athlete("S03", "Adamaris ", 22, "TAS", "Swimming"));
		allAthletes.add(new Athlete("S04", "Addison  ", 20, "NSW", "Swimming"));
		allAthletes.add(new Athlete("S05", "Adney    ", 19, "VIC", "Swimming"));
		allAthletes.add(new Athlete("S06", "Adolf    ", 18, "QLD", "Swimming"));
		allAthletes.add(new Athlete("R01", "Aethelred", 30, "TAS", "Running"));
		allAthletes.add(new Athlete("R02", "Aiken    ", 20, "NSW", "Running"));
		allAthletes.add(new Athlete("R03", "Ainsley  ", 23, "VIC", "Running"));
		allAthletes.add(new Athlete("R04", "Albany   ", 23, "QLD", "Running"));
		allAthletes.add(new Athlete("R05", "Alcott   ", 24, "TAS", "Running"));
		allAthletes.add(new Athlete("R06", "Alden    ", 25, "NSW", "Running"));
		allAthletes.add(new Athlete("C01", "Aldway   ", 26, "VIC", "Cycling"));
		allAthletes.add(new Athlete("C02", "Aldwin   ", 27, "QLD", "Cycling"));
		allAthletes.add(new Athlete("C03", "Alexavier", 28, "TAS", "Cycling"));
		allAthletes.add(new Athlete("C04", "Alfred   ", 29, "NSW", "Cycling"));
		allAthletes.add(new Athlete("C05", "Alger    ", 20, "VIC", "Cycling"));
		allAthletes.add(new Athlete("C06", "Alma     ", 21, "QLD", "Cycling"));
		allAthletes.add(new Athlete("SA01", "Almond   ", 22, "TAS", "SuperAthlete"));
		allAthletes.add(new Athlete("SA02", "Aloysius ", 23, "SA ", "SuperAthlete"));
		allAthletes.add(new Athlete("SA03", "Alston   ", 24, "VIC", "SuperAthlete"));
		allAthletes.add(new Athlete("SA04", "Alvin    ", 25, "TAS", "SuperAthlete"));
		allAthletes.add(new Athlete("SA05", "Amanda   ", 26, "QLD", "SuperAthlete"));
		allAthletes.add(new Athlete("SA06", "Andrea   ", 7, "NSW", "SuperAthlete"));
		return allAthletes;
	}

	public static ArrayList<Athlete> AddAthletes(String type) {

		ArrayList<Athlete> allAthletes = createAllAthletes();
		Random r = new Random();
		int athletesAmount = r.nextInt(5) + 4;
		ArrayList<Athlete> athletes = new ArrayList<Athlete>();
		while (athletes.size() <= athletesAmount) {
			int athleteNum = r.nextInt(allAthletes.size());
			if (allAthletes.get(athleteNum).getType().equals(type) || allAthletes.get(athleteNum).getType().equals("SuperAthlete")) {
				athletes.add(allAthletes.get(athleteNum));
				allAthletes.remove(athleteNum);
			}
		}
		return athletes;
	}

	public static Officials AddOfficials() {
		Officials off = new Officials("O01", "Alex", 23, "VIC");
		return off;
	}

	public static ArrayList<MatchResult> prepareMatchResult(ArrayList<Athlete> athletes) {
		Iterator<Athlete> i = athletes.iterator();
		ArrayList<MatchResult> matchResult = new ArrayList<MatchResult>();
		do {
			matchResult.add(new MatchResult(i.next()));
		} while (i.hasNext());

		return matchResult;

	}

	public static Swimming createSwimming(ArrayList<MatchResult> matchResult) {
		String swimmingID = "S" + swmimmingGameTimes;
		Swimming swimming = new Swimming(swimmingID, AddOfficials(), matchResult);

		swmimmingGameTimes++;
		return swimming;

	}

	public static Cycling createCycling(ArrayList<MatchResult> matchResult) {
		String cyclingID = "C" + cyclingGameTimes;

		Cycling cycling = new Cycling(cyclingID, AddOfficials(), matchResult);

		cyclingGameTimes++;
		return cycling;

	}

	public static Running createRunning(ArrayList<MatchResult> matchResult) {
		String runningID = "R" + runningGameTimes;
		Running running = new Running(runningID, AddOfficials(), matchResult);

		runningGameTimes++;
		return running;

	}

	public static void runGame(String sportType, ArrayList<Sports> allGames) {
		ArrayList<Athlete> athletes = GameData.AddAthletes(sportType);
		ArrayList<MatchResult> MatchResult = GameData.prepareMatchResult(athletes);
		if (sportType.equals("Swimming"))
			allGames.add(GameData.createSwimming(MatchResult));
		if (sportType.equals("Cycling"))
			allGames.add(GameData.createCycling(MatchResult));
		if (sportType.equals("Running"))
			allGames.add(GameData.createRunning(MatchResult));
	}

	public static ArrayList<MatchResult> presentMatchResults(Sports sport) {
		Iterator<MatchResult> iter = sport.getMatchResults().iterator();
		do {
			iter.next().setTime(sport.compete());
		} while (iter.hasNext());

		sport.getMatchResults().sort(new TimeSorted());

		for (int i = 1; i <= sport.getMatchResults().size(); i++) {
			sport.getMatchResults().get(i - 1).setRank(i);
		}
		return sport.getMatchResults();

	}

	public static ArrayList<Athlete> givePoints(ArrayList<Athlete> allAthlete, ArrayList<MatchResult> matchResult) {
		ArrayList<String> AthleteIDs = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			AthleteIDs.add(matchResult.get(i).getAthlete().getID());
		}
		for (int j = 0; j < allAthlete.size(); j++) {
			if (AthleteIDs.get(0).equals(allAthlete.get(j).getID()))
				allAthlete.get(j).setPoints(5);
			if (AthleteIDs.get(1).equals(allAthlete.get(j).getID()))
				allAthlete.get(j).setPoints(2);
			if (AthleteIDs.get(2).equals(allAthlete.get(j).getID()))
				allAthlete.get(j).setPoints(1);
		}

		return allAthlete;
	}
}
