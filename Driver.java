//Wrote by Huayu Sun, Beier Nie, this class is driver class that archive functionalities of the whole program.
import java.util.*;

public class Driver {

	private Scanner sc = new Scanner(System.in);
	private ArrayList<Sports> Games = new ArrayList<Sports>();
	private ArrayList<Athlete> allAthletes = new ArrayList<Athlete>();

	public Driver() {
		setAllAthletes();
	}

	public void setAllAthletes() {
		this.allAthletes.addAll(GameData.createAllAthletes());
	}

	public static void mainMenu() {
		System.out.println("                 Welcome");
		System.out.println("=================================================");
		System.out.println("1.Choose a game");
		System.out.println("2.Predict winner of the game");
		System.out.println("3.Start the game");
		System.out.println("4.Display the final results of this games");
		System.out.println("5.Display points");
		System.out.println("6.Exit");
		System.out.println("=================================================");
		System.out.println("Enter an option: ");
	}

	public static void subMenu() {
		System.out.println("1.Swimming");
		System.out.println("2.Cycling");
		System.out.println("3.Running");
		System.out.println("=================================================");
		System.out.println("Please choose:  ");

	}

	public static void back() {
		System.out.println();
		System.out.println("1.Main Menu.");
		System.out.println();

	}

	public void start() {
		int choice;
		try {
			mainMenu();
			choice = sc.nextInt();
			if (choice == 1) {
				selectGame();
			} else if (choice == 2) {
				predictWinner();
			} else if (choice == 3) {
				startGame();
			} else if (choice == 4) {
				displayResults();
			} else if (choice == 5) {
				displayPoints();
			} else if (choice == 6) {
				System.out.println("Game over!");
			}
		} catch (Exception e) {
			System.out.println("Please enter a valid option!");
			start();
		}

	}

	public void selectGame() {
		int choice;
		do {
			subMenu();

			choice = sc.nextInt();
			if (Games.size() > 0) {
				if (Games.get(Games.size() - 1).compete() == 0) {
					Games.remove(Games.size() - 1);
				}
			}
			if (choice == 1) {
				GameData.runGame("Swimming", Games);
				System.out.println("Please predict winner.");
				start();
			}
			if (choice == 2) {
				GameData.runGame("Cycling", Games);
				System.out.println("Please predict winner.");
				start();
			}
			if (choice == 3) {
				GameData.runGame("Running", Games);
				System.out.println("Please predict winner.");
				start();
			} else {
				System.out.println("Please enter valid option!");
			}
		} while (!(choice == 1 || choice == 2 || choice == 3));
	}

	public void predictWinner() {
		int athleteAmount = Games.get(Games.size() - 1).getMatchResults().size();
		System.out.println("GameID: " + Games.get(Games.size() - 1).getSportsID());
		System.out.println("Official: " + Games.get(Games.size() - 1).getOfficials().getID() + "   " + Games.get(Games.size() - 1).getOfficials().getName());
		ArrayList<String> amount = new ArrayList<String>();
		for (int i = 1; i <= athleteAmount; i++) {
			Athlete athlete = Games.get(Games.size() - 1).getMatchResults().get(i - 1).getAthlete();
			System.out.println(i + "." + "ID: " + athlete.getID() + "   " + "Name: " + athlete.getName());
			Integer inter = new Integer(i);
			amount.add(inter.toString());
		}
		String pre;
		int predictionNum = 0;
		do {
			System.out.println("Please select an athlete: ");
			pre = sc.next();
			if (amount.contains(pre)) {
				Integer inte = new Integer(pre);
				predictionNum = inte.intValue();
				Games.get(Games.size() - 1).setPredictedWinner(
						Games.get(Games.size() - 1).getMatchResults().get(predictionNum - 1).getAthlete());
				System.out.println("Enter 3 if you confirm this game or you can choose game again.");
				start();
			} else {
				System.out.println("Please enter valid option!");
			}
		} while (!amount.contains(pre));

	}

	public void startGame() {
		ArrayList<MatchResult> matchResults = GameData.presentMatchResults(Games.get(Games.size() - 1));

		allAthletes = GameData.givePoints(allAthletes, matchResults);

		System.out.println("SportID: " + Games.get(Games.size() - 1).getSportsID());
		Iterator<MatchResult> iter = matchResults.iterator();
		MatchResult matchResult;
		do {
			matchResult = iter.next();
			System.out.println("Rank: " + matchResult.getRank() + "  ID: " + matchResult.getAthlete().getID()
					+ "  Name: " + matchResult.getAthlete().getName() + "  Time: " + matchResult.getTime());
		} while (iter.hasNext());
		if (Games.get(Games.size() - 1).getPredWinner() == null) {
			System.out.println("Please predict winner!");
		} else {
			System.out.println("Your prediction winner is: ");
			System.out.println("ID: " + Games.get(Games.size() - 1).getPredWinner().getID() + "   Name :" + Games.get(Games.size() - 1).getPredWinner().getName());

			ArrayList<String> rankFirstAthletesIDs = new ArrayList<String>();

			for (int i = 0; i < matchResults.size(); i++) {
				if (matchResults.get(i).getRank() == 1) {
					rankFirstAthletesIDs.add(matchResults.get(i).getAthlete().getID());
				}
			}

			if (rankFirstAthletesIDs.contains(Games.get(Games.size() - 1).getPredWinner().getID())) {
				System.out.println("Congratulations!");
			}
		}

		String back;
		do {
			back();

			back = sc.next();
			if (back.equals("1")) {
				start();
			} else {
				System.out.println("Please choose a valid option");
			}

		} while (!back.equals("1"));
	}
	
	public void displayPoints() {
		Athlete athletes;
		Iterator<Athlete> iter = allAthletes.iterator();
		do {
			athletes = iter.next();
			System.out.println("ID: " + athletes.getID() + "   Name: " + athletes.getName() + "   Points: "
					+ athletes.getPoints());
		} while (iter.hasNext());

		int back;
		do {
			back();
			back = sc.nextInt();
			if (back == 1) {
				start();
			} else {
				System.out.println("Please choose valid option");
			}

		} while (!(back == 1));
	}

	public void displayResults() {
		Iterator<Sports> iterator = Games.iterator();
		do {
			Sports sport = iterator.next();
			System.out.println("SportID: " + sport.getSportsID());
			System.out.println("Official: " + sport.getOfficials().getName());
			MatchResult matchResult;
			Iterator<MatchResult> iter = sport.getMatchResults().iterator();
			do {
				matchResult = iter.next();
				System.out.println("Rank: " + matchResult.getRank() + "  ID: " + matchResult.getAthlete().getID()
						+ "  Name: " + matchResult.getAthlete().getName() + "  Time: " + matchResult.getTime());
			} while (iter.hasNext());

		} while (iterator.hasNext());

		String back;
		do {
			back();
			back = sc.next();
			if (back.equals("1")) {
				start();
			} else {
				System.out.println("Please choose valid option");
			}

		} while (!back.equals("1"));
	}

}