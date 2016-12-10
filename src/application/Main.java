package application;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameManager;
import ui.GameScreen;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 800, 600);
		GameScreen gsc = new GameScreen(800,600);
		GameManager gm = new GameManager();
		root.getChildren().add(gsc);
		gsc.paintComponents();
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bobo Game");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}