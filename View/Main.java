//Wrote by Huayu Sun, this class is for GUI.
package View;

import Controller.Driver;
import Models.*;
import javafx.application.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.util.*;

public class Main extends Application {
	Scene scene1, scene2, scene3, scene4, scene5;
	ArrayList<Athlete> SelectedAthlete = new ArrayList<>();
	Official SelectedOfficial = null;
	String gameType;
	int TooFewAthleteException = 4;
	int GameFullException = 8;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Driver driver = new Driver();
		driver.DBCheck();
		driver.txtCheck();
		driver.getData();
		driver.generateData();
		Stage stage;
		stage = primaryStage;

		// scene 1
		AnchorPane ap1 = new AnchorPane();
		Button btnNewGame = new Button("Play!");
		Button btnGameHistory = new Button("View Game History");
		Button btnAthletePoints = new Button("Athletes\' Points");
		Image image2 = new Image("file:image/indexview.jpg");
		ImageView iv2 = new ImageView(image2);
		// layout
		iv2.setLayoutX(220);
		iv2.setLayoutY(80);
		iv2.setFitHeight(400);
		iv2.setFitWidth(350);

		btnNewGame.setPrefSize(120, 50);
		btnNewGame.setLayoutX(140);
		btnNewGame.setLayoutY(500);

		btnGameHistory.setPrefSize(160, 50);
		btnGameHistory.setLayoutX(315);
		btnGameHistory.setLayoutY(500);

		btnAthletePoints.setPrefSize(120, 50);
		btnAthletePoints.setLayoutX(540);
		btnAthletePoints.setLayoutY(500);

		ap1.setStyle("-fx-background-color:white");
		// add items
		ap1.getChildren().addAll(btnNewGame, btnGameHistory, btnAthletePoints, iv2);

		// Scene 2
		AnchorPane ap2 = new AnchorPane();

		Button btnMain = new Button("Main Menu");
		Button btnStartGame = new Button("Start Game");
		Text Notification = new Text();
		Label gameTypeLB = new Label("Please Select a game type");
		Label athleteLB = new Label("Please Select athletes(4~8)");
		Label officialLB = new Label("Please Select official(1 only)");
		Image imageCycle = new Image("file:image/cycle.jpg");
		Image imageSwim = new Image("file:image/swim.jpg");
		Image imageRun = new Image("file:image/run.jpg");
		ImageView iv3 = new ImageView(imageCycle);
		ImageView iv4 = new ImageView(imageSwim);
		ImageView iv5 = new ImageView(imageRun);
		ListView<String> gameList = new ListView<String>(
				FXCollections.observableArrayList(Driver.SWIM, Driver.CYCLE, Driver.RUN));
		ListView<Participants> athletesList = new ListView<>();
		ListView<Participants> officialList = new ListView<>();

		ArrayList<Participants> p1 = new ArrayList<>();
		ArrayList<Participants> p2 = new ArrayList<>();
		ArrayList<Athlete> AthletesList = driver.getAthleteList();
		ArrayList<Official> OfficialList = driver.getOfficialList();
		p1.addAll(AthletesList);
		p1.addAll(OfficialList);
		p2.addAll(OfficialList);

		ObservableList<Participants> PObservableList1 = FXCollections.observableArrayList(p1);
		ObservableList<Participants> PObservableList2 = FXCollections.observableArrayList(p2);
		athletesList.setItems(PObservableList1);
		athletesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		officialList.setItems(PObservableList2);
		// layout
		gameTypeLB.setPrefSize(350, 30);
		athleteLB.setPrefSize(350, 30);
		officialLB.setPrefSize(350, 30);
		gameList.setPrefWidth(400);
		gameList.setPrefHeight(200);
		athletesList.setPrefWidth(400);
		athletesList.setPrefHeight(200);
		officialList.setPrefWidth(400);
		officialList.setPrefHeight(200);
		btnMain.setPrefSize(120, 50);
		btnMain.setLayoutX(200);btnMain.setLayoutY(500);
		btnStartGame.setPrefSize(120, 50);
		btnStartGame.setLayoutX(400);btnStartGame.setLayoutY(500);
		Notification.setLayoutY(450);

		Notification.setFont(Font.font("Wawati SC", 20));
		Notification.setFill(Color.RED);
		gameTypeLB.setFont(Font.font("Wawati SC", 16));
		athleteLB.setFont(Font.font("Wawati SC", 16));
		officialLB.setFont(Font.font("Wawati SC", 16));

		ap2.setStyle("-fx-background-color:white");
		// Add items
		VBox hb1 = new VBox();
		hb1.getChildren().addAll(athleteLB, athletesList);
		VBox hb2 = new VBox();
		hb2.getChildren().addAll(officialLB, officialList);
		VBox hb3 = new VBox();
		hb3.getChildren().addAll(hb1, hb2);
		VBox hb4 = new VBox();
		hb4.getChildren().addAll(gameTypeLB, gameList);
		HBox vb1 = new HBox();
		vb1.getChildren().addAll(hb4, hb3);
		HBox vb2 = new HBox();
		vb2.getChildren().addAll(iv3, iv4, iv5);
		vb2.setLayoutX(50);
		vb2.setLayoutY(250);
		ap2.getChildren().addAll(vb1, vb2, Notification, btnMain, btnStartGame);

		// Scene3
		AnchorPane ap3 = new AnchorPane();
		Button btnMain2 = new Button("Main Menu");
		ListView<String> histroyList = new ListView<>();
		// layout
		btnMain2.setPrefSize(120, 50);
		histroyList.setPrefWidth(800);
		btnMain2.setLayoutX(325);
		btnMain2.setLayoutY(450);

		ap3.getChildren().addAll(histroyList, btnMain2);

		// Scene4
		AnchorPane ap4 = new AnchorPane();
		Button btnMain3 = new Button("Main Menu");
		ListView<String> athletePointList = new ListView<>();
		// layout
		btnMain3.setPrefSize(120, 50);
		athletePointList.setPrefWidth(800);
		btnMain3.setLayoutX(325);
		btnMain3.setLayoutY(450);

		ap4.getChildren().addAll(athletePointList, btnMain3);
		
		// Scene5
		AnchorPane ap5 = new AnchorPane();
		Button btnMain4 = new Button("Main Menu");
		Button btnRestart = new Button("Start New Game");
		ListView<String> resultList = new ListView<>();
        //layout
		btnMain4.setPrefSize(120, 50);
		btnRestart.setPrefSize(120, 50);
		resultList.setPrefWidth(800);
        btnMain4.setLayoutX(250);btnMain4.setLayoutY(450);
		btnRestart.setLayoutX(450);btnRestart.setLayoutY(450);

		ap5.getChildren().addAll(resultList, btnMain4, btnRestart);

		// create interface
		scene1 = new Scene(ap1, 800, 600);
		scene2 = new Scene(ap2, 800, 600);
		scene3 = new Scene(ap3, 800, 600);
		scene4 = new Scene(ap4, 800, 600);
		scene5 = new Scene(ap5, 800, 600);
		stage.setTitle("Ozlympic Game");
		stage.setScene(scene1);
		stage.show();

		// Action Binding
		// scene1
		btnNewGame.setOnAction(e -> {
			stage.setScene(scene2);
			stage.setTitle("Main Screen");
		});

		btnGameHistory.setOnAction(e -> {
			ArrayList<String> gameHistory = driver.getgamesHistory();
			ObservableList<String> gameHistroyObservableList = FXCollections.observableArrayList(gameHistory);
			histroyList.setItems(gameHistroyObservableList);
			stage.setTitle("Game Records");
			stage.setScene(scene3);
		});

		btnAthletePoints.setOnAction(e -> {
			driver.printSortAthletes();
			ArrayList<String> athletePoint = driver.getAthletePoint();
			ObservableList<String> athletePointObservableList = FXCollections.observableArrayList(athletePoint);
			athletePointList.setItems(athletePointObservableList);
			stage.setTitle("Athlete Points");
			stage.setScene(scene4);
		});

		// Scene2
		btnMain.setOnAction(e -> {
			stage.setTitle("Ozlympic Game");
			stage.setScene(scene1);
		});

		MultipleSelectionModel<Participants> selectionModel = athletesList.getSelectionModel();
		selectionModel.clearSelection();
		athletesList.setCellFactory(lv -> {
			ListCell<Participants> cells = new ListCell<Participants>() {
				@Override
				public void updateItem(Participants item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null) {
						setText(null);
					} else {
						setText(item.toString());
					}
				}
			};
			cells.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
				athletesList.requestFocus();
				if (!cells.isEmpty()) {
					int index = cells.getIndex();
					if (selectionModel.getSelectedIndices().contains(index)) {
						selectionModel.clearSelection(index);
					} else {
						selectionModel.select(index);
					}
					event.consume();
				}
			});
			return cells;
		});

		gameList.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
					gameType = new_val;
				});

		btnStartGame.setOnAction(e -> {

			ObservableList<Participants> ASelectedAthlete = athletesList.getSelectionModel().getSelectedItems();
			ObservableList<Participants> ASelectedOfficial = officialList.getSelectionModel().getSelectedItems();

			try {
				ArrayList<Athlete> SelectedAthlete = new ArrayList<>();
				SelectedOfficial = null;
				for (Participants athlete : ASelectedAthlete) {
					switch (gameType) {
					case Driver.CYCLE:
						if (athlete instanceof Cyclist || athlete instanceof SuperAthlete)
							SelectedAthlete.add((Athlete) athlete);
						else
							throw new WrongTypeException();
						break;
					case Driver.SWIM:
						if (athlete instanceof Swimmer || athlete instanceof SuperAthlete)
							SelectedAthlete.add((Athlete) athlete);
						else
							throw new WrongTypeException();
						break;

					case Driver.RUN:
						if (athlete instanceof Runner || athlete instanceof SuperAthlete)
							SelectedAthlete.add((Athlete) athlete);
						else
							throw new WrongTypeException();
						break;
					}
				}
				for (Participants official : ASelectedOfficial) {
					if (official instanceof Official) {
						SelectedOfficial = ((Official) official);
					} else
						throw new WrongTypeException();
				}
				if (SelectedAthlete.size() < TooFewAthleteException)
					throw new TooFewAthleteException();
				else if (SelectedAthlete.size() > GameFullException)
					throw new GameFullException();
				else if (SelectedOfficial == null)
					throw new NoRefereeException();

				driver.startgame(gameType, SelectedAthlete, SelectedOfficial);
				stage.setTitle("Game Result");
				stage.setScene(scene5);
				gameList.getSelectionModel().clearSelection();
				athletesList.getSelectionModel().clearSelection();
				officialList.getSelectionModel().clearSelection();

			} catch (GameFullException e1) {
				Notification.setText("GameFullException");
			} catch (WrongTypeException e2) {
				Notification.setText("WrongTypeException");
			} catch (TooFewAthleteException e3) {
				Notification.setText("TooFewAthleteException");
			} catch (NoRefereeException e4) {
				Notification.setText("NoRefereeException");
			} catch (Exception e5) {
				Notification.setText("Please select a game type!");
			}

			ArrayList<String> result = driver.getresult();
			ObservableList<String> resultObservableList = FXCollections.observableArrayList(result);
			resultList.setItems(resultObservableList);
		});
		// Scene3
		btnMain2.setOnAction(e -> {
			stage.setTitle("Ozlympic Game");
			stage.setScene(scene1);

		});

		// Scene4
		btnMain3.setOnAction(e -> {
			stage.setTitle("Ozlympic Game");
			stage.setScene(scene1);

		});

		// Scene5
		btnMain4.setOnAction(e -> {
			stage.setTitle("Ozlympic Game");
			stage.setScene(scene1);

		});
		btnRestart.setOnAction(e -> {
			stage.setTitle("Main Screen");
			stage.setScene(scene2);

		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
