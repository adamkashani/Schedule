package core.two.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.*;
import java.util.HashMap;

import core.two.bean.MyDate;
import core.two.file.FileService;

public class DateScren extends Application {

	private FileService fileService;

	private HashMap<MyDate, String> dataMap;

	private HashMap<MyDate, String> newDataMap = new HashMap<>();

	private VBox boxSearch = new VBox();

	private VBox boxHead = new VBox();
//	private EventHandler<ActionEvent> eventSeeMap = new EventHandler<ActionEvent>() {
//
//		@Override
//		public void handle(ActionEvent event) {
//			// נצטרף להחליף מסף שיופיעו בוא כול הרשימות
//		}
//	};

	// launch the application
	public void start(Stage window) {
		CheckBox checkBoxAppend = new CheckBox("append");

		// create a tile pane
//		BorderPane tilePane = new BorderPane();
		TilePane tilePane = new TilePane();
		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(16));
		tilePane.setHgap(16);
		tilePane.setVgap(8);
		StackPane layoutToPath = new StackPane();
		Text textDataCheck = new Text("select data to check");
		textDataCheck.setStyle("-fx-font: normal bold 20px 'serif' ");

		DatePicker dateSerchPicker = new DatePicker();

		Text textToView = new Text();
		textDataCheck.setStyle("-fx-font: normal bold 20px 'serif' ");
		Text haedText = new Text();
		haedText.setText("first insert data and select date to add");
		haedText.setStyle("-fx-font: normal bold 20px 'serif' ");
		dateSerchPicker.setOnAction(event -> {
			if (!boxSearch.getChildren().contains(textDataCheck)) {
				boxSearch.getChildren().add(textDataCheck);
			}
			// get the date picker value
			LocalDate localDate = dateSerchPicker.getValue();

			// get the selected date
			textDataCheck.setText("The Date selected :" + localDate);
			LocalDate getDate = dateSerchPicker.getValue();
			MyDate myDate = new MyDate();
			myDate.setDate(getDate.getYear(), getDate.getMonth().getValue(), getDate.getDayOfMonth());
			if (dataMap.get(myDate) == null) {
				newDataMap.get(myDate);
				textToView.setText("The Value : " + newDataMap.get(myDate));
			} else {
				textToView.setText("The Value : " + dataMap.get(myDate));
			}
			if (!boxSearch.getChildren().contains(textToView)) {
				boxSearch.getChildren().add(textToView);
			}
		});

		Button saveMap = new Button("save map");
		saveMap.setOnAction(event -> {
			boolean append = checkBoxAppend.isSelected();
			if(append) {
				newDataMap.putAll(dataMap);
			}
			fileService.saveMap(newDataMap , append);
		});

		Button seeMap = new Button("see map");
		TextField valueToMap = new TextField("content of the message");

		// set title for the stage
		window.setTitle("Schedule");

		// label to show the date check

		// create a date picker
		DatePicker datePicker = new DatePicker();

		// action event
		EventHandler<ActionEvent> eventDatePicker = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// get the date picker value
				LocalDate localDate = datePicker.getValue();

				MyDate myDate = new MyDate();
				myDate.setDate(localDate.getYear(), localDate.getMonth().getValue(), localDate.getDayOfMonth());
				System.out.println(localDate.getYear() + " , " + localDate.getMonth().getValue() + " , "
						+ localDate.getDayOfMonth());
				newDataMap.put(myDate, valueToMap.getText());
				System.out.println(myDate);
				System.out.println(valueToMap.getText());
			}
		};

		Button buttonPathToFile = new Button("Choose");
		buttonPathToFile.setMaxSize(100, 20);
		buttonPathToFile.setMinSize(100, 29);
//		buttonPathToFile.

		buttonPathToFile.setOnAction(event -> {
			FileChooser chooser = new FileChooser();
			File file = chooser.showOpenDialog(window);
			if (file != null) {
//                String fileAsString = file.toString();
				this.fileService = new FileService(file);
				dataMap = fileService.readMap();
				System.out.println(dataMap);
				// remove button path to file
				// box for head and to add
				tilePane.getChildren().remove(buttonPathToFile);
				
				boxHead.getChildren().add(haedText);
				boxHead.getChildren().add(valueToMap);
				boxHead.getChildren().add(datePicker);
				tilePane.getChildren().add(boxHead);

				// box for search
				Label labelDateSearch = new Label();
				labelDateSearch.setText("select date to serch ");
				boxSearch.getChildren().add(labelDateSearch);
				boxSearch.getChildren().add(dateSerchPicker);
				tilePane.getChildren().add(boxSearch);

				// box for save
				VBox boxSave = new VBox();
				boxSave.getChildren().add(checkBoxAppend);
				boxSave.getChildren().add(saveMap);
				tilePane.getChildren().add(boxSave);
				// create a scene
				Scene scene = new Scene(tilePane, 400, 320);

				// set the scene
				window.setScene(scene);
			}
		});

		// show week numbers
		datePicker.setShowWeekNumbers(true);

		// when datePicker is pressed
		datePicker.setOnAction(eventDatePicker);

		// add button choose path to file
		layoutToPath.getChildren().add(buttonPathToFile);

		// create a scene
		Scene scene = new Scene(layoutToPath, 400, 320);

		// set the scene
		window.setScene(scene);

		window.show();

	}

	public static void main(String args[]) {
		// launch the application
		launch(args);
	}

}