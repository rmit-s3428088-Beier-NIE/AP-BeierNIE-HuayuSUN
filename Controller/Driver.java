//Wrote by Huayu Sun
package Controller;

import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import Models.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Driver {
	private int gameNum = 0;
	private String gameID;
	private Game game = null;
	private List<String> gameResult;
	private List<Integer> scoreList;
	private ArrayList<String> result = new ArrayList<>();
	private ArrayList<String> gamesHistory = new ArrayList<>();
	private ArrayList<String> athletePoint = new ArrayList<>();
	private ArrayList<Athlete> athleteList = new ArrayList<>();
	private ArrayList<Official> officialList = new ArrayList<>();
	private Set<String> itemSet = new TreeSet<>();
	public static final String SWIM = "Swimming";
	public static final String CYCLE = "Cycling";
	public static final String RUN = "Running";


	public boolean DBCheck() {
		File participants = new File("participants.db");
		if (participants.exists()) {
			return true;
		} else
			return false;
	}

	public boolean txtCheck() {
		File participants = new File("participants.txt");
		if (participants.exists()) {
			return true;
		} else
			return false;
	}

	public void getData() {
		if (DBCheck())
			getFromDB();
		else
			getFromTxt();
	}

	private void getFromDB() {
		try {
			FileOutputStream writer = new FileOutputStream("gameResults.txt");
			writer.write(("").getBytes());
			writer.close();
			Class.forName("org.sqlite.JDBC");
			Connection participants = DriverManager.getConnection("jdbc:sqlite:participants.db");
			Statement stmt = participants.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM participants;");
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				String line = "";
				for (int i = 1; i <= columnCount; i++) {
					if (i < columnCount)
						line += rs.getString(i) + ", ";
					else
						line += rs.getString(i);
				}
				itemSet.add(line);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private void getFromTxt() {
		BufferedReader br = null;
		try {
			FileOutputStream writer = new FileOutputStream("gameResults.txt");
			writer.write(("").getBytes());
			writer.close();
			br = new BufferedReader(new FileReader("participants.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				itemSet.add(line);
			}
		} catch (FileNotFoundException e1) {
			e1.getMessage();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e3) {
				throw new RuntimeException("Fail to Close File ");
			}
		}
	}

	public void generateData() {
		createDB();
		for (String s : itemSet) {
			String[] items = s.split(",\\s*");
			if (!validData(items)) {
				continue;
			}
			String ID = items[0];
			String type = items[1];
			String name = items[2];
			int age = Integer.parseInt(items[3]);
			String state = items[4];
			if (type.equals("Cyclist")) {
				athleteList.add(new Cyclist(ID, type, name, age, state));
			} else if (type.equals("Swimmer")) {
				athleteList.add(new Swimmer(ID, type, name, age, state));
			} else if (type.equals("Sprinter")) {
				athleteList.add(new Runner(ID, type, name, age, state));
			} else if (type.equals("SuperAthlete")) {
				athleteList.add(new SuperAthlete(ID, type, name, age, state));
			} else if (type.equals("Officer")) {
				officialList.add(new Official(ID, type, name, age, state));
			}
		}
	}

	public boolean validData(String[] data) {
		for (String s : data) {
			if ("".equals(s) || "null".equals(s))
				return false;
		}
		return true;
	}

	private void createDB() {
		String drop = "Drop table if exists result";
		String sql = " Create table result(" + "GameID text," + "OfficialID text," + "AthleteID text,"
				+ "Result integer," + "Score integer" + ");";

		try {
			Class.forName("org.sqlite.JDBC");
			Connection gameResults = DriverManager.getConnection("jdbc:sqlite:gameResults.db");
			Statement stmt = gameResults.createStatement();
			stmt.execute(drop);
			stmt.execute(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

	public void startgame(String gameType, ArrayList<Athlete> athletes, Official official) {
		String gameID = gameType.charAt(0) + (gameNum < 10 ? "0" : "") + gameNum;
		if(gameType == SWIM) {
			game = new Swimming(gameID, gameType, athletes, official);}
		else if(gameType == CYCLE){
			game = new Cycling(gameID, gameType, athletes, official);}
		else if(gameType == RUN){
			game = new Running(gameID, gameType, athletes, official);
		}

		game.start();
		printGameResult(game, official);
		gameResultDB();
		printgameHistory();
		gameNum++;
	}

	private void printGameResult(Game game, Official official) {
		result = new ArrayList<>();
		gameID = game.getID();
		Calendar cal = Calendar.getInstance();
		DateFormat dateFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		gameResult = game.getPrintResult();
		scoreList = official.getscoreList();
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			bw = new BufferedWriter(new FileWriter("gameResults.txt", true));
			out = new PrintWriter(bw);
			for (int i = 0; i < gameResult.size(); i++) {
				if (i == 0) {
					result.add(gameID + " ," + gameResult.get(i) + " ," + dateFromat.format(cal.getTime()));
					out.println(gameID + " ," + gameResult.get(i) + " ," + dateFromat.format(cal.getTime()));
				} else if (i == 1) {
					result.add(gameResult.get(i) + " ," + scoreList.get(i) + " , 5");
					out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 5");
				} else if (i == 2) {
					result.add(gameResult.get(i) + " ," + scoreList.get(i) + " , 2");
					out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 2");
				} else if (i == 3) {
					result.add(gameResult.get(i) + " ," + scoreList.get(i) + " , 1");
					out.println(gameResult.get(i) + " ," + scoreList.get(i) + " , 1");
				}
			}
		} catch (FileNotFoundException e1) {
			e1.getMessage();
		} catch (IOException e2) {
			e2.printStackTrace();
		} 

	}

	private void gameResultDB() {
		Integer[] rank = {0,5,2,1};
		try {
			Class.forName("org.sqlite.JDBC");
			Connection gameResults = DriverManager.getConnection("jdbc:sqlite:gameResults.db");
			for (int i = 1; i < gameResult.size(); i++) {
				PreparedStatement pstmt = gameResults.prepareStatement("insert into result values(?,?,?,?,?)");
				pstmt.setString(1, gameID);
				pstmt.setString(2, gameResult.get(0));
				pstmt.setString(3, gameResult.get(i));
				pstmt.setInt(4, scoreList.get(i));
				pstmt.setInt(5, rank[i]);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

	private List<Athlete> sortAthletes(List<Athlete> athletes) {
		List<Athlete> sortList = new ArrayList<Athlete>(athletes);
		Collections.sort(sortList, new Comparator<Athlete>() {
			@Override
			public int compare(Athlete a1, Athlete a2) {
				return a2.getPoints() - a1.getPoints();
			}
		});
		return sortList;
	}

	public void printSortAthletes() {
		athletePoint = new ArrayList<>();
		List<Athlete> sortList = sortAthletes(athleteList);
		for (int i = 0; i < sortList.size(); i++) {
			printAthlete(sortList.get(i));
		}
	}

	private void printAthlete(Athlete athlete) {
		athletePoint.add("Points: " + athlete.getPoints() + ", " + athlete.toString());
	}

	private void printgameHistory() {
		gamesHistory = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("gameResults.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				gamesHistory.add(line);
			}
		} catch (IOException e) {

		} finally {
			try {
				if (reader != null)
					reader.close();

			} catch (IOException e) {

			}
		}
	}

	public ArrayList<Athlete> getAthleteList() {
		return athleteList;
	}

	public ArrayList<Official> getOfficialList() {
		return officialList;
	}

	public ArrayList<String> getresult() {
		return result;
	}

	public ArrayList<String> getgamesHistory() {
		return gamesHistory;
	}

	public ArrayList<String> getAthletePoint() {
		return athletePoint;
	}
}
